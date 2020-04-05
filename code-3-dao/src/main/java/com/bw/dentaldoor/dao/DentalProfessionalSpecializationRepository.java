package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.DentalProfessional;
import com.bw.dentaldoor.entity.DentalProfessionalSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Emmanuel Evuazeze <eevuazeze@byteworks.com.ng>
 */
@Repository
public interface DentalProfessionalSpecializationRepository extends JpaRepository<DentalProfessionalSpecialization, Long> {

    @Query("SELECT dps from DentalProfessionalSpecialization dps where dps.status = 'ACTIVE' and dps.dentalProfessional = ?1")
    List<DentalProfessionalSpecialization> findActiveByDentalProfessional(DentalProfessional dentalProfessional);

    @Query("SELECT dps from DentalProfessionalSpecialization dps JOIN FETCH dps.specialization where dps.status = 'ACTIVE' and dps.dentalProfessional IN ?1")
    List<DentalProfessionalSpecialization> findActiveByDentalProfessionals(List<DentalProfessional> dentalProfessional);
}
