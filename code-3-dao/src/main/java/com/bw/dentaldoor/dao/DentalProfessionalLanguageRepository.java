package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.DentalProfessional;
import com.bw.dentaldoor.entity.DentalProfessionalLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Emmanuel Evuazeze <eevuazeze@byteworks.com.ng>
 */
@Repository
public interface DentalProfessionalLanguageRepository extends JpaRepository<DentalProfessionalLanguage, Long> {

    @Query("SELECT dpl from DentalProfessionalLanguage dpl where dpl.status = 'ACTIVE' and dpl.dentalProfessional = ?1")
    List<DentalProfessionalLanguage> findActiveByDentalProfessional(DentalProfessional dentalProfessional);

    @Query("SELECT dpl from DentalProfessionalLanguage dpl JOIN FETCH dpl.language where dpl.status = 'ACTIVE' and dpl.dentalProfessional IN ?1")
    List<DentalProfessionalLanguage> findActiveByDentalProfessionals(List<DentalProfessional> dentalProfessional);
}
