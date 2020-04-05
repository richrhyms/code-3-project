package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.DentalProfessional;
import com.bw.dentaldoor.entity.DentalProfessionalLicence;
import com.bw.dentaldoor.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Emmanuel Evuazeze <eevuazeze@byteworks.com.ng>
 */
@Repository
public interface DentalProfessionalLicenceRepository extends JpaRepository<DentalProfessionalLicence, Long> {

    @Query("SELECT dpli from DentalProfessionalLicence dpli where dpli.status = 'ACTIVE' and dpli.dentalProfessional = ?1 " +
            " and dpli.stateOfIssuance = ?2 and lower(dpli.licenceNumber) = lower(?3) ")
    Optional<DentalProfessionalLicence> findActiveByDentalProfessionalAndStateOfIssuanceAndLicenceNumber(DentalProfessional dentalProfessional,
                                                                                                         State state,
                                                                                                         String licenceNumber);

    @Query("SELECT dpli from DentalProfessionalLicence dpli where dpli.status = 'ACTIVE' and dpli.dentalProfessional = ?1")
    List<DentalProfessionalLicence> findActiveByDentalProfessional(DentalProfessional dentalProfessional);

    @Query("SELECT dpli from DentalProfessionalLicence dpli where dpli.status = 'ACTIVE' and dpli.dentalProfessional IN ?1")
    List<DentalProfessionalLicence> findActiveByDentalProfessionals(List<DentalProfessional> dentalProfessionals);
}
