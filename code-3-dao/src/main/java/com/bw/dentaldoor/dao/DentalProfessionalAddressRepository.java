package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.DentalProfessional;
import com.bw.dentaldoor.entity.DentalProfessionalAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Emmanuel Evuazeze <eevuazeze@byteworks.com.ng>
 */
@Repository
public interface DentalProfessionalAddressRepository extends JpaRepository<DentalProfessionalAddress, Long> {

    @Query("SELECT dpl from DentalProfessionalAddress dpl JOIN FETCH dpl.address where dpl.status = 'ACTIVE' and dpl.dentalProfessional = ?1")
    List<DentalProfessionalAddress> findActiveByDentalProfessional(DentalProfessional dentalProfessional);

    @Query("SELECT dpl from DentalProfessionalAddress dpl" +
            " JOIN FETCH dpl.address a" +
            " LEFT JOIN FETCH a.gpsCoordinate" +
            " JOIN FETCH a.city c" +
            " JOIN FETCH c.state where dpl.status = 'ACTIVE' and dpl.dentalProfessional IN ?1")
    List<DentalProfessionalAddress> findActiveByDentalProfessionals(List<DentalProfessional> dentalProfessional);
}
