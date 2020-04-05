package com.bw.dentaldoor.dao.account;

import com.bw.dentaldoor.entity.PortalAccountMemberRole;
import com.bw.dentaldoor.entity.PortalAccountMembership;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.enums.GenericStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PortalAccountMemberRoleRepository extends JpaRepository<PortalAccountMemberRole, Long> {

    @Query("select r FROM PortalAccountMemberRole r" +
            " JOIN FETCH r.role" +
            " JOIN FETCH r.membership m" +
            " JOIN FETCH m.portalAccount" +
            " WHERE m.portalUser=?1 AND r.status=?2 AND m.status=?2")
    List<PortalAccountMemberRole> findRoles(PortalUser portalUser, GenericStatusConstant statusConstant);

    List<PortalAccountMemberRole> findAllByMembershipAndStatus(PortalAccountMembership membership, GenericStatusConstant statusConstant);
}
