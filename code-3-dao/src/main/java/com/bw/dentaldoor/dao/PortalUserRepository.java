package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.PortalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Repository
public
interface PortalUserRepository extends JpaRepository<PortalUser, Long> {

    @Query("SELECT p FROM PortalUser p WHERE lower(p.username)=lower(?1)")
    Optional<PortalUser> findByUsername(String username);

    @Query("SELECT p FROM PortalUser p WHERE lower(p.email)=lower(?1)")
    Optional<PortalUser> findByEmail(String email);

    @Query("SELECT p FROM PortalUser p WHERE lower(p.email)=lower(?1) and p.status = 'ACTIVE'")
    Optional<PortalUser> findActiveByEmail(String email);

    @Query("SELECT p FROM PortalUser p WHERE lower(p.phoneNumber)=lower(?1)")
    Optional<PortalUser> findByPhoneNumber(String phoneNumber);

    @Query("SELECT p FROM PortalUser p WHERE lower(p.email)=lower(?1) OR lower(p.phoneNumber)=lower(?2)")
    Optional<PortalUser> findByEmailOrPhoneNumber(String email, String phoneNumber);

    @Query("SELECT p FROM PortalUser p WHERE lower(p.email)=lower(?1) OR lower(p.username)=lower(?2) Or lower(p.phoneNumber)=lower(?3)")
    Optional<PortalUser> findByEmailOrUsernameOrPhoneNumber(String email, String username, String phoneNumber);

    @Query("SELECT p FROM PortalUser p WHERE lower(p.email)=lower(?1) OR lower(p.username)=lower(?2)")
    Optional<PortalUser> findByEmailOrUsername(String email, String username);


    @Query("SELECT p FROM PortalUser p where lower(p.userId) = lower(?1) and p.status = 'ACTIVE'")
    Optional<PortalUser> findByUserId(String userId);

    @Query("SELECT p FROM PortalUser p WHERE p.status = 'ACTIVE' and (lower(p.userId)=lower(?1) OR lower(p.username)=lower(?1))")
    Optional<PortalUser> findFirstByUserIdOrUsername(String userId);
}
