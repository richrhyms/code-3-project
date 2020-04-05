package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.DentalOffice;
import com.bw.dentaldoor.entity.DentalOfficeService;
import com.bw.enums.GenericStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface DentalOfficeServiceRepository extends JpaRepository<DentalOfficeService,Long> {
    @Query("select d from DentalOfficeService d where d.dentalOffice = ?1 and d.status = ?2 and d.dentalService.isAdditionalService = ?3")
    List<DentalOfficeService> findAllByDentalOfficeAndStatus(DentalOffice dentalOffice, GenericStatusConstant status, boolean isAdditionalService);

    @Query("select d from DentalOfficeService d join fetch d.dentalService " +
            "where d.dentalOffice in ?1 and d.status = 'ACTIVE' and d.dentalService.isAdditionalService = false ")
    List<DentalOfficeService> findActiveByDentalOffices(List<DentalOffice> dentalOffices);

    @Query("select d from DentalOfficeService d join fetch d.dentalService " +
            "where d.dentalOffice in ?1 and d.status = 'ACTIVE' and d.dentalService.isAdditionalService = true ")
    List<DentalOfficeService> findActiveAdditionalServiceByDentalOffices(List<DentalOffice> dentalOffices);

    @Query("select count(d) from DentalOfficeService d where d.dentalOffice = ?1 and d.status = ?2 and d.dentalService.isAdditionalService = ?3")
    Long countByDentalOfficeAndStatus(DentalOffice dentalOffice, GenericStatusConstant status, boolean isAdditionalService);
}