package com.bw.dentaldoor.service;

import com.bw.dentaldoor.dao.account.PortalAccountMemberRoleRepository;
import com.bw.dentaldoor.dao.account.PortalAccountTypeRoleRepository;
import com.bw.dentaldoor.entity.PortalAccountMemberRole;
import com.bw.dentaldoor.entity.PortalAccountMembership;
import com.bw.dentaldoor.entity.PortalAccountTypeRole;
import com.bw.dentaldoor.enums.PortalAccountTypeConstant;
import com.bw.enums.GenericStatusConstant;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Named
public class RoleServiceImpl implements RoleService {

    private final PortalAccountTypeRoleRepository portalAccountRoleRepository;

    private final PortalAccountMemberRoleRepository portalAccountMemberRoleRepository;

    public RoleServiceImpl(
            PortalAccountTypeRoleRepository portalAccountRoleRepository,
            PortalAccountMemberRoleRepository portalAccountMemberRoleRepository) {
        this.portalAccountRoleRepository = portalAccountRoleRepository;
        this.portalAccountMemberRoleRepository = portalAccountMemberRoleRepository;
    }

    @Transactional
    @Override
    public PortalAccountTypeRole createRole(PortalAccountTypeConstant portalAccount, String name, String displayName) {
        portalAccountRoleRepository.findActiveByPortalAccountTypeAndName(portalAccount, name)
                .ifPresent(portalAccountTypeRole -> {
                    throw new IllegalArgumentException(String.format("Role %s already exists in portal account type %s", name, portalAccount.getValue()));
                });
        PortalAccountTypeRole role = new PortalAccountTypeRole();
        role.setName(name);

        role.setAccountType(portalAccount);
        role.setDisplayName(StringUtils.defaultIfBlank(displayName, name));
        role.setStatus(GenericStatusConstant.ACTIVE);
        role.setDateCreated(Timestamp.from(Instant.now()));
        return portalAccountRoleRepository.save(role);
    }

    @Transactional
    @Override
    public PortalAccountMemberRole addRole(PortalAccountMembership membership, PortalAccountTypeRole role) {
        PortalAccountMemberRole membershipRole = new PortalAccountMemberRole();
        membershipRole.setDateCreated(Timestamp.from(Instant.now()));
        membershipRole.setMembership(membership);
        membershipRole.setRole(role);
        membershipRole.setStatus(GenericStatusConstant.ACTIVE);
        portalAccountMemberRoleRepository.save(membershipRole);
        return membershipRole;
    }
}
