package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.DentalOffice;
import com.bw.dentaldoor.entity.DentalProfessional;
import com.bw.dentaldoor.entity.Invite;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.enums.InvitationTypeConstant;
import com.bw.dentaldoor.pojo.GroupedCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InviteRepository extends JpaRepository<Invite, Long> {

    @Query("select i from Invite i where i.invitedUser=?1 AND i.id in ?2" +
            " and i.invitationStatus = 'PENDING' and i.status='ACTIVE' AND i.dentalOffice.status='ACTIVE'")
    List<Invite> findPendingInvitations(DentalProfessional dentalProfessional, Iterable<Long> ids);

    @Query("select COUNT(i) from Invite i where i.invitedUser=?1" +
            " and i.invitationStatus = 'PENDING' and i.status='ACTIVE' AND i.dentalOffice.status='ACTIVE'")
    int countPendingInvitations(DentalProfessional dentalProfessional);

//    @Query("select i from Invite i where i.dentalOffice = ?1 AND i.invitedUser=?2 " +
//            " and i.invitationStatus='PENDING' and i.status='ACTIVE'")
//    Optional<Invite> findPendingInvitation(DentalOffice dentalOffice, DentalProfessional dentalProfessional);

    @Query("select i from Invite i where i.createdBy = ?1 AND (lower(i.email)=lower(?2)) " +
            " AND i.type='USER' and i.invitationStatus='PENDING' and i.status='ACTIVE'")
    Optional<Invite> findPendingUserInvitation(PortalUser portalUser, String email);

    @Query("select i from Invite i where i.dentalOffice = ?1 AND (lower(i.email)=lower(?2)) " +
            " AND i.type=?3 and i.invitationStatus='PENDING' and i.status='ACTIVE'")
    Optional<Invite> findPendingInvitation(DentalOffice dentalOffice, String email, InvitationTypeConstant invitationTypeConstant);

    @Query("select i from Invite i where i.type=?1 AND (lower(i.email)=lower(?2) OR i.phoneNumber=?3) " +
            " and i.invitationStatus='PENDING' and i.status='ACTIVE' AND i.invitedUser IS NULL")
    List<Invite> findPendingInvitation(InvitationTypeConstant invitationTypeConstant, String email, String phoneNumber);

    @Query("SELECT new com.bw.dentaldoor.pojo.GroupedCount(r.createdBy.id, COUNT(r))" +
            " FROM Invite r" +
            " WHERE r.createdBy IN ?1" +
            " GROUP BY r.createdBy.id"
    )
    List<GroupedCount<Integer>> countByPortalUser(List<PortalUser> portalUsers);

    @Query("SELECT COUNT(r)" +
            " FROM Invite r" +
            " WHERE r.createdBy = ?1")
    long countByPortalUser(PortalUser portalUser);
}
