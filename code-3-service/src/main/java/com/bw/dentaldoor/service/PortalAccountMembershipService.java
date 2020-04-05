package com.bw.dentaldoor.service;

import com.bw.dentaldoor.domain.pojo.AccountMembershipPojo;
import com.bw.dentaldoor.entity.PortalAccount;
import com.bw.dentaldoor.entity.PortalAccountMembership;
import com.bw.dentaldoor.entity.PortalUser;

import java.util.List;

public interface PortalAccountMembershipService {

    PortalAccountMembership createMembership(PortalAccount portalAccount, PortalUser user);

    List<AccountMembershipPojo> getAllMemberships(PortalUser portalUser);

    PortalAccountMembership removeMembership(PortalAccountMembership portalAccountMembership);
}
