package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.DentalOffice;
import com.bw.dentaldoor.entity.DentalOfficeAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DentalOfficeAddressRepository extends JpaRepository<DentalOfficeAddress, Long> {

    @Query("SELECT dpl from DentalOfficeAddress dpl" +
            " JOIN FETCH dpl.address a" +
            " LEFT JOIN FETCH a.gpsCoordinate" +
            " JOIN FETCH a.city c" +
            " JOIN FETCH c.state where dpl.status = 'ACTIVE' and dpl.dentalOffice IN ?1")
    List<DentalOfficeAddress> findActiveByDentalOffices(List<DentalOffice> dentalOffices);

    @Query("SELECT dpl from DentalOfficeAddress dpl" +
            " JOIN FETCH dpl.address a" +
            " LEFT JOIN FETCH a.gpsCoordinate" +
            " JOIN FETCH a.city c" +
            " JOIN FETCH c.state where dpl.status = 'ACTIVE' and dpl.dentalOffice.id IN ?1")
    List<DentalOfficeAddress> findActiveByDentalOfficeIds(List<Long> dentalOffices);
}
