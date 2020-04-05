package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.DentalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DentalOfficeRepository extends JpaRepository<DentalOffice, Long> {

    @Query("select d from DentalOffice d where d.id = ?1 and d.status = 'ACTIVE'")
    Optional<DentalOffice> findActiveById(Long id);

    List<DentalOffice> findAllByPortalAccount_IdIn(List<Long> portalAccount);

    @Query(nativeQuery = true, value = "SELECT dd.id FROM dental_office dd" +
            " INNER JOIN dental_office_address doa ON dd.id = doa.dental_office_fk" +
            " INNER JOIN address addr ON addr.id=doa.address_fk" +
            " INNER JOIN gps_coordinate gc on addr.gps_coordinate_fk = gc.id" +
            " WHERE earth_distance(ll_to_earth( gc.latitude, gc.longitude ), ll_to_earth(:latitude, :longitude)) <= :radiusInMeters" +
            " ORDER BY earth_distance(ll_to_earth( gc.latitude, gc.longitude ), ll_to_earth(:latitude, :longitude))")
    List<Long> getOfficesId(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("radiusInMeters") Double radiusInMeters);
}
