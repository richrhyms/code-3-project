package com.bw.dentaldoor.service;

import com.bw.dentaldoor.dao.PortalUserRepository;
import com.bw.dentaldoor.dao.account.PortalAccountRepository;
import com.bw.dentaldoor.dao.account.PortalAccountTypeRoleRepository;
import com.bw.dentaldoor.domain.account.NewUserDto;
import com.bw.dentaldoor.domain.account.SignUpResponse;
import com.bw.dentaldoor.domain.account.UserRegistrationDto;
import com.bw.dentaldoor.domain.enumeration.UserTypeConstant;
import com.bw.dentaldoor.entity.*;
import com.bw.dentaldoor.enums.InvitationTypeConstant;
import com.bw.enums.GenericStatusConstant;
import com.bw.integration.AuthClient;
import com.bw.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Date;

@RequiredArgsConstructor
@Named
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final PortalUserRepository portalUserRepository;
    private final PasswordService passwordService;
    private final AuthClient authClient;
    private final PortalAccountRepository portalAccountRepository;
    private final RoleService roleService;
    private final PortalAccountTypeRoleRepository portalAccountRoleRepository;
    private final PortalAccountMembershipService portalAccountMembershipService;
    private final PhoneNumberService phoneNumberService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    @Override
    public PortalUser registerUserWithRoles(NewUserDto newUserDto) {
        PortalUser portalUser = doUserRegistration(newUserDto);
        newUserDto.getRoles().entrySet().forEach(entry -> {
            PortalAccount portalAccount = portalAccountRepository.findActiveByCode(entry.getKey())
                    .orElseThrow(() -> new IllegalArgumentException(String.format("No portal account with code %s", entry.getKey())));
            PortalAccountMembership portalAccountMembership = portalAccountMembershipService.createMembership(portalAccount, portalUser);
            entry.getValue().forEach(roleName -> {
                PortalAccountTypeRole portalAccountRole = portalAccountRoleRepository.findActiveByPortalAccountTypeAndName(portalAccount.getType(), roleName)
                        .orElseThrow(() -> new IllegalArgumentException(String.format("Role %s not found for account type %s", roleName, portalAccount.getType().getValue())));
                roleService.addRole(portalAccountMembership, portalAccountRole);
            });
        });
        return portalUser;
    }

    @Transactional
    @Override
    public PortalUser doUserRegistration(NewUserDto newUserDto) {
        portalUserRepository.findByEmailOrUsername(newUserDto.getEmail(), newUserDto.getUsername())
                .ifPresent(portalUser -> {
                    if (newUserDto.getEmail().equals(portalUser.getEmail())) {
                        throw new IllegalArgumentException(String.format("User with email %s already exists", portalUser.getEmail()));
                    }
                    throw new IllegalArgumentException(String.format("User with username %s already exists", portalUser.getUsername()));
                });
        PortalUser portalUser = new PortalUser();
        portalUser.setUsername(newUserDto.getUsername());
        portalUser.setEmail(newUserDto.getEmail());
        portalUser.setFirstName(newUserDto.getFirstName());
        portalUser.setLastName(newUserDto.getLastName());
        portalUser.setDisplayName(String.format("%s %s", newUserDto.getFirstName(), newUserDto.getLastName()));
        portalUser.setStatus(GenericStatusConstant.ACTIVE);
        portalUser.setGeneratedPassword(passwordService.generatePassword());
        Date now = new Date();
        portalUser.setDateCreated(now);
        portalUser.setLastUpdated(now);
        portalUser.setUserId(authClient.createUser(portalUser).getUserId());
        portalUserRepository.save(portalUser);
        return portalUser;
    }

    @Transactional
    @Override
    public SignUpResponse doUserRegistration(UserRegistrationDto userRegistrationDto) {
        String username = userRegistrationDto.getEmail();
        String formattedMobileNumber = phoneNumberService.formatPhoneNumber(userRegistrationDto.getMobileNumber());
        portalUserRepository.findByEmailOrUsernameOrPhoneNumber(userRegistrationDto.getEmail(), username, formattedMobileNumber)
                .ifPresent(portalUser -> {
                    logger.info("===> User already exists {}", portalUser.getUsername());
                    if (userRegistrationDto.getEmail().equals(portalUser.getEmail())) {
                        throw new IllegalArgumentException(String.format("User with email %s already exists", portalUser.getEmail()));
                    }
                    if (formattedMobileNumber.equals(portalUser.getPhoneNumber())) {
                        logger.info("====> phone number already exists {}", formattedMobileNumber);
                        throw new IllegalArgumentException(String.format("User with mobile Number %s already exists", portalUser.getPhoneNumber()));
                    }
                    throw new IllegalArgumentException(String.format("User with username %s already exists", portalUser.getUsername()));
                });
        PortalUser portalUser = new PortalUser();
        portalUser.setUsername(username);
        portalUser.setEmail(userRegistrationDto.getEmail());
        portalUser.setFirstName(userRegistrationDto.getFirstName());
        portalUser.setLastName(userRegistrationDto.getLastName());
        portalUser.setDisplayName(String.format("%s %s", userRegistrationDto.getFirstName(), userRegistrationDto.getLastName()));
        portalUser.setPhoneNumber(formattedMobileNumber);
        portalUser.setStatus(GenericStatusConstant.ACTIVE);
        Date now = new Date();
        portalUser.setDateCreated(now);
        portalUser.setLastUpdated(now);

        SignUpResponse responsePojo = authClient.createUser(portalUser, userRegistrationDto.getPassword());
        responsePojo.setPortalUser(portalUser);
        portalUser.setUserId(responsePojo.getUserId());

        if (userRegistrationDto.getUserType() == UserTypeConstant.STUDENT) {
            portalUser.setIsStudent(true);
        }
        portalUserRepository.save(portalUser);

//        if (userRegistrationDto.getUserType() == UserTypeConstant.DENTAL_PROFESSIONAL) {
//            DentalProfessional dentalProfessional = dentalProfessionalService.createDentalProfessional(portalUser);
//
//            inviteRepository.findPendingInvitation(InvitationTypeConstant.DENTAL_PRACTITIONER, portalUser.getEmail(), portalUser.getPhoneNumber())
//                    .forEach(invite -> {
//                        invite.setInvitedUser(dentalProfessional);
//                        inviteRepository.save(invite);
//                    });
//        }

        return responsePojo;
    }

    @Transactional
    @Override
    public SignUpResponse registerUserWithDefaultPassword(UserRegistrationDto userRegistrationDto) {
        userRegistrationDto.setPassword(passwordService.generatePassword(8));
        SignUpResponse signUpResponse = doUserRegistration(userRegistrationDto);
        PortalUser portalUser = signUpResponse.getPortalUser();
        portalUser.setGeneratedPassword(userRegistrationDto.getPassword());
        portalUserRepository.save(portalUser);
        return signUpResponse;
    }
}
