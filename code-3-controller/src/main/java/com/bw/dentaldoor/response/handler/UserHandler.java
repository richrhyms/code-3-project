//package com.bw.dentaldoor.response.handler;
//
//import com.bw.auth.ApiResourcePortalUser;
//import com.bw.dentaldoor.dao.*;
//import com.bw.dentaldoor.domain.pojo.AccountMembershipPojo;
//import com.bw.dentaldoor.entity.DentalProfessional;
//import com.bw.dentaldoor.entity.PortalUser;
//import com.bw.dentaldoor.enums.PortalAccountTypeConstant;
//import com.bw.dentaldoor.response.pojo.UserPojo;
//import com.bw.dentaldoor.service.PortalAccountMembershipService;
//import com.bw.enums.GenericStatusConstant;
//import lombok.RequiredArgsConstructor;
//
//import javax.inject.Named;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Named
//public class UserHandler {
//
//    private final PortalAccountMembershipService portalAccountMembershipService;
//    private final DentalProfessionalRepository dentalProfessionalRepository;
//    private final DentalProfessionalHandler dentalProfessionalHandler;
//    private final PortalUserImageRepository portalUserImageRepository;
//    private final DentalOfficeRepository dentalOfficeRepository;
//    private final HealthInsuranceProviderRepository portalUserInsuranceProviderRepository;
//    private final InviteRepository inviteRepository;
//
//    public UserPojo getUserPojo(PortalUser portalUser, ApiResourcePortalUser apiResourcePortalUser) {
//
//        UserPojo user = new UserPojo(portalUser, apiResourcePortalUser);
//
//        portalUserImageRepository.findFirstByPortalUserAndStatus(portalUser, GenericStatusConstant.ACTIVE)
//                .ifPresent(it -> user.setDisplayPictureId(it.getBwFile().getId()));
//        user.setAccounts(portalAccountMembershipService.getAllMemberships(portalUser));
//
//        Optional<DentalProfessional> optionalDentalProfessional = dentalProfessionalRepository.findByPortalUser(portalUser);
//        optionalDentalProfessional.ifPresent(dentalProfessional -> user.setDentalProfessional(dentalProfessionalHandler.getDentalProfessionalPojo(dentalProfessional)));
//
//        List<AccountMembershipPojo> dentalOfficeAccounts = user.getAccounts()
//                .stream()
//                .filter(accountMembershipPojo -> accountMembershipPojo.getAccountType() == PortalAccountTypeConstant.DENTAL_OFFICE)
//                .collect(Collectors.toList());
//
//        List<Long> doAccountIds = dentalOfficeAccounts
//                .stream()
//                .map(AccountMembershipPojo::getAccountId)
//                .collect(Collectors.toList());
//
//        if (!doAccountIds.isEmpty()) {
//            dentalOfficeRepository.findAllByPortalAccount_IdIn(doAccountIds)
//                    .forEach(dentalOffice -> dentalOfficeAccounts.stream()
//                            .filter(accountMembershipPojo -> accountMembershipPojo.getAccountId().equals(dentalOffice.getPortalAccount().getId()))
//                            .findFirst()
//                            .ifPresent(accountMembershipPojo -> accountMembershipPojo.setOtherData(dentalOffice)));
//        }
//
//        user.setInsuranceProviders(portalUserInsuranceProviderRepository.getByPortalUser(portalUser));
//        user.setNumberOfInvitationsSent(inviteRepository.countByPortalUser(portalUser));
//
//        return user;
//    }
//}
