package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.DentalProfessional;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.enums.GenericStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Emmanuel Evuazeze <eevuazeze@byteworks.com.ng>
 */
@Repository
public interface DentalProfessionalRepository extends JpaRepository<DentalProfessional, Long> {

    @Query("select dp from DentalProfessional dp join fetch dp.portalUser where dp.portalUser = ?1")
    Optional<DentalProfessional> findByPortalUser(PortalUser portalUser);

    @Query("SELECT dp FROM DentalProfessional dp WHERE dp.status='ACTIVE' AND (lower(dp.portalUser.email)=lower(?1) OR dp.portalUser.phoneNumber=?2)")
    Optional<DentalProfessional> findByEmailOrPhoneNumber(String email, String phoneNumber);

    @Query("select dp from DentalProfessional dp join fetch dp.portalUser where dp.id = ?1 and dp.status = ?2")
    Optional<DentalProfessional> findByIdAndStatus(Long id, GenericStatusConstant status);

    @Query("select d from DentalProfessional d where d.id = ?1 and d.status = 'ACTIVE'")
    Optional<DentalProfessional> findActiveById(Long id);
}
