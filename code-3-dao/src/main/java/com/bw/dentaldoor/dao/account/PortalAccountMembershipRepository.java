package com.bw.dentaldoor.dao.account;

import com.bw.dentaldoor.entity.PortalAccount;
import com.bw.dentaldoor.entity.PortalAccountMembership;
import com.bw.dentaldoor.entity.PortalAccountTypeRole;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.enums.GenericStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PortalAccountMembershipRepository extends JpaRepository<PortalAccountMembership, Long> {

    @Query("select m FROM PortalAccountMembership m WHERE m.portalAccount=?1 AND m.portalUser=?2 AND m.status=?3")
    Optional<PortalAccountMembership> findMembership(PortalAccount portalAccount, PortalUser portalUser, GenericStatusConstant statusConstant);

    @Query("select m FROM PortalAccountMembership m WHERE m.portalUser=?1 AND m.status=?2")
    List<PortalAccountMembership> findMembership(PortalUser portalUser, GenericStatusConstant statusConstant);

    @Query("select m FROM PortalAccountMembership m WHERE m.portalUser=?1 AND m.status=?2")
    List<PortalAccountMembership> findMemberships(PortalUser portalUser, GenericStatusConstant statusConstant);

    @Query("select COUNT(m) FROM PortalAccountMembership m WHERE m.portalAccount=?1 AND m.status='ACTIVE'")
    int countActiveMemberships(PortalAccount portalAccount);

    @Query("select COUNT(DISTINCT mr.membership) FROM PortalAccountMemberRole mr" +
            " WHERE mr.membership.portalAccount=?1 AND mr.role=?2" +
            " AND mr.status='ACTIVE' AND mr.membership.status='ACTIVE'")
    int countActiveMemberships(PortalAccount portalAccount, PortalAccountTypeRole role);

    Optional<PortalAccountMembership> findByPortalAccountAndPortalUserAndStatus(PortalAccount portalAccount, PortalUser portalUser, GenericStatusConstant status);
}
