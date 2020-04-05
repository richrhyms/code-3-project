package com.bw.dentaldoor.service;


import com.bw.dentaldoor.entity.PortalAccountMemberRole;
import com.bw.dentaldoor.entity.PortalAccountMembership;
import com.bw.dentaldoor.entity.PortalAccountTypeRole;
import com.bw.dentaldoor.enums.PortalAccountTypeConstant;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public interface RoleService {

    PortalAccountTypeRole createRole(PortalAccountTypeConstant portalAccount, String name, String displayName);

    PortalAccountMemberRole addRole(PortalAccountMembership portalUser, PortalAccountTypeRole role);
}
