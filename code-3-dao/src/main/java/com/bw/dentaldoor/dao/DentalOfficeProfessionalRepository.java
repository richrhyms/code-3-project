package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.DentalOffice;
import com.bw.dentaldoor.entity.DentalOfficeProfessional;
import com.bw.dentaldoor.entity.DentalProfessional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DentalOfficeProfessionalRepository extends JpaRepository<DentalOfficeProfessional, Long> {

    @Query("select dop from DentalOfficeProfessional dop where dop.status='ACTIVE' " +
            "and dop.dentalProfessional = ?1 and dop.dentalOffice = ?2")
    Optional<DentalOfficeProfessional> findActiveByDentalProfessionalAndDentalOffice(DentalProfessional dentalProfessional, DentalOffice dentalOffice);

    @Query("select dop from DentalOfficeProfessional dop JOIN FETCH dop.dentalOffice where dop.status='ACTIVE' and dop.dentalProfessional IN ?1")
    List<DentalOfficeProfessional> findActiveByDentalProfessional(List<DentalProfessional> dentalProfessional);

    @Query("select dop from DentalOfficeProfessional dop where dop.status='ACTIVE' AND dop.dentalOffice=?1" +
            " AND (lower(dop.dentalProfessional.portalUser.email) = lower(?2) OR dop.dentalProfessional.portalUser.phoneNumber=?3)")
    Optional<DentalOfficeProfessional> findActiveDentalProfessional(DentalOffice dentalOffice, String email, String phoneNumber);
}
