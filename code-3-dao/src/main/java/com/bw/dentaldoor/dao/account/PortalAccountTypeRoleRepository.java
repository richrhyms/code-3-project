package com.bw.dentaldoor.dao.account;

import com.bw.dentaldoor.entity.PortalAccountTypeRole;
import com.bw.dentaldoor.enums.PortalAccountTypeConstant;
import com.bw.enums.GenericStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Repository
public interface PortalAccountTypeRoleRepository extends JpaRepository<PortalAccountTypeRole, Long>, QuerydslPredicateExecutor<PortalAccountTypeRole> {

    @Query("SELECT r FROM PortalAccountTypeRole r" +
            " WHERE r.status='ACTIVE' " +
            " AND r.accountType = ?1" +
            " AND r.name=?2")
    Optional<PortalAccountTypeRole> findActiveByPortalAccountTypeAndName(PortalAccountTypeConstant portalAccountType, String name);

    @Query("SELECT DISTINCT r.name FROM PortalAccountTypeRole r" +
            " WHERE r.status='ACTIVE'" +
            " AND r.accountType=?1" +
            " AND r.status='ACTIVE'" +
            " ORDER BY r.displayName")
    List<String> findAllRoles(PortalAccountTypeConstant accountType);

    @Query("SELECT DISTINCT r FROM PortalAccountTypeRole r" +
            " WHERE r.status='ACTIVE' AND r.accountType IN ?1" +
            " ORDER BY r.displayName")
    List<PortalAccountTypeRole> findAllRoles(List<PortalAccountTypeConstant> portalAccountTypes);

    @Query("select r from PortalAccountTypeRole r where r.id in (select p.role.id from PortalAccountMemberRole p where p.membership.portalUser.userId=?1 and p.status='ACTIVE') and r.status=?2")
    List<PortalAccountTypeRole> findAllByPortalUser_UserIdAndStatus(String userId, GenericStatusConstant status);

    @Query("SELECT r FROM PortalAccountTypeRole r" +
            " WHERE r.status='ACTIVE' " +
            " AND r.accountType = ?1" +
            " AND r.name in ?2")
    List<PortalAccountTypeRole> findActiveByPortalAccountTypeAndNameIn(PortalAccountTypeConstant portalAccountType, Set<String> name);
}
