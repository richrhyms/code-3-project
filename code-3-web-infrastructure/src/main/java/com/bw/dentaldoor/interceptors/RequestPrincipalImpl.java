package com.bw.dentaldoor.interceptors;

import com.bw.dentaldoor.dao.AppRepository;
import com.bw.dentaldoor.dao.PortalUserRepository;
import com.bw.dentaldoor.dao.account.PortalAccountTypeRoleRepository;
import com.bw.dentaldoor.domain.pojo.AccountMembershipPojo;
import com.bw.dentaldoor.entity.PortalAccountTypeRole;
import com.bw.dentaldoor.entity.PortalUser;
//import com.bw.dentaldoor.domain.pojo.AccountMembershipPojo;
import com.bw.dentaldoor.principal.RequestPrincipal;
//import com.bw.dentaldoor.dao.AppRepository;
//import com.bw.dentaldoor.dao.PortalUserRepository;
//import com.bw.dentaldoor.dao.account.PortalAccountTypeRoleRepository;
//import com.bw.dentaldoor.service.PortalAccountMembershipService;
import com.bw.dentaldoor.service.PortalAccountMembershipService;
import com.bw.enums.GenericStatusConstant;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class RequestPrincipalImpl implements RequestPrincipal {

    private final String userId;
    private final String ipAddress;
    private Optional<PortalUser> portalUser;

    @Inject
    private TransactionTemplate transactionTemplate;

    @Inject
    private AppRepository appRepository;
    @Inject
    private PortalAccountTypeRoleRepository portalAccountTypeRoleRepository;

    @Inject
    private PortalAccountMembershipService membershipService;
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private PortalUserRepository workspaceUserRepository;

    public RequestPrincipalImpl(String userId, String ipAddress) {
        this.userId = userId;
        this.ipAddress = ipAddress;
    }

    @Override
    public List<PortalAccountTypeRole> getRoles() {
        return portalAccountTypeRoleRepository.findAllByPortalUser_UserIdAndStatus(userId, GenericStatusConstant.ACTIVE);
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }


    @Override
    public PortalUser getPortalUser() {
        if (portalUser == null || !portalUser.isPresent()) {
            portalUser = workspaceUserRepository.findByUserId(userId);
        }
        return portalUser.orElse(null);
    }

    @Override
    public List<AccountMembershipPojo> getAccountPermissions() {
        PortalUser portalUser = getPortalUser();
        return membershipService.getAllMemberships(portalUser);
    }
}
