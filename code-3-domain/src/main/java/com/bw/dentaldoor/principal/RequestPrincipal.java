package com.bw.dentaldoor.principal;


import com.bw.dentaldoor.domain.pojo.AccountMembershipPojo;
import com.bw.dentaldoor.entity.PortalAccountMembership;
import com.bw.dentaldoor.entity.PortalAccountTypeRole;
import com.bw.dentaldoor.entity.PortalUser;

import java.util.Collections;
import java.util.List;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public interface RequestPrincipal {

    public String AUTH_TOKEN_NAME = "authToken";

    String getUserId();

    default String getUserName() {
        return null;
    }

    default String getDisplayName() {
        return null;
    }

    default String getIpAddress() {
        return null;
    }

    default List<PortalAccountTypeRole> getRoles() {
        return Collections.emptyList();
    }

    default List<PortalAccountMembership> getMemberships() {
        return Collections.emptyList();
    }

    default PortalUser getPortalUser() {
        return null;
    }

    default ActorTypeConstant getType() {
        return ActorTypeConstant.PORTAL_USER;
    }

//    ActivityLogBuilder activity(SystemActivity action);

//    default PortalUser getPortalUserRef() {
//        PortalUser portalUser = new PortalUser();
//        portalUser.setId(this.getUserId());
//        return portalUser;
//    }
//
//    default boolean isPrincipal(PortalUser portalUser) {
//        return portalUser == null ? false : this.getUserId().equals(portalUser.getId());
//    }
    List<AccountMembershipPojo> getAccountPermissions();
}
