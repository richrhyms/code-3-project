package com.bw.dentaldoor.service;

import com.bw.dentaldoor.dao.account.PortalAccountMemberRoleRepository;
import com.bw.dentaldoor.dao.account.PortalAccountMembershipRepository;
import com.bw.dentaldoor.dao.account.RolePermissionRepository;
import com.bw.dentaldoor.domain.pojo.AccountMembershipPojo;
import com.bw.dentaldoor.entity.*;
import com.bw.enums.GenericStatusConstant;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class PortalAccountMembershipServiceImpl implements PortalAccountMembershipService {

    private final PortalAccountMembershipRepository membershipRepository;
    private final PortalAccountMemberRoleRepository portalAccountMemberRoleRepository;
    private final RolePermissionRepository rolePermissionRepository;


    @Transactional
    @Override
    public PortalAccountMembership createMembership(PortalAccount portalAccount, PortalUser user) {
        membershipRepository.findMembership(portalAccount, user, GenericStatusConstant.ACTIVE)
                .ifPresent(portalAccountMembership -> {
                    throw new IllegalArgumentException(String.format("User %s is already a member of account %s",
                            user.getUsername(), portalAccount.getCode()));
                });
        PortalAccountMembership membership = newMembership(portalAccount);
        membership.setPortalUser(user);
        return membershipRepository.save(membership);
    }

    private PortalAccountMembership newMembership(PortalAccount portalAccount) {
        PortalAccountMembership membership = new PortalAccountMembership();
        membership.setDateCreated(Timestamp.from(Instant.now()));
        membership.setPortalAccount(portalAccount);
        membership.setStatus(GenericStatusConstant.ACTIVE);
        return membership;
    }

    @Override
    public List<AccountMembershipPojo> getAllMemberships(PortalUser portalUser) {
        List<PortalAccountMemberRole> memberRoles = portalAccountMemberRoleRepository.findRoles(portalUser, GenericStatusConstant.ACTIVE);

        List<PortalAccountTypeRole> roles = memberRoles.stream().map(PortalAccountMemberRole::getRole).collect(Collectors.toList());
        List<RolePermission> rolePermissions = roles.isEmpty() ? Collections.EMPTY_LIST : rolePermissionRepository.findAllByRoleIn(roles);

//        Set<PortalAccountTypeRole> accountTypeRoles = new TreeSet<>((o1, o2) -> o1.getId().compareTo(o2.getId()));
//        accountTypeRoles.addAll(memberRoles.stream().map(PortalAccountMemberRole::getRole).collect(Collectors.toList()));

        Set<PortalAccountMembership> accountMemberships = new TreeSet<>(Comparator.comparing(PortalAccountMembership::getId));
        accountMemberships.addAll(memberRoles.stream().map(PortalAccountMemberRole::getMembership).collect(Collectors.toList()));

        return accountMemberships.stream().map(membership -> {
            AccountMembershipPojo accountPermission = new AccountMembershipPojo(membership.getPortalAccount());
            List<PortalAccountTypeRole> mRoles = memberRoles.stream()
                    .filter(mRole -> mRole.getMembership().getId().equals(membership.getId()))
                    .map(PortalAccountMemberRole::getRole)
                    .collect(Collectors.toList());

            accountPermission.setRoles(mRoles.stream().map(PortalAccountTypeRole::getName).collect(Collectors.toSet()));
            Collection<Long> roleIds = mRoles.stream().map(PortalAccountTypeRole::getId).collect(Collectors.toSet());
            accountPermission.setPermissions(
                    rolePermissions.stream()
                            .filter(rp -> roleIds.contains(rp.getPortalAccountTypeRole().getId()))
                            .map(RolePermission::getName)
                            .collect(Collectors.toSet())
            );
            return accountPermission;
        }).collect(Collectors.toList());
    }

    @Override
    public PortalAccountMembership removeMembership(PortalAccountMembership portalAccountMembership) {
       portalAccountMembership.setStatus(GenericStatusConstant.DEACTIVATED);
       return membershipRepository.save(portalAccountMembership);
    }
}
