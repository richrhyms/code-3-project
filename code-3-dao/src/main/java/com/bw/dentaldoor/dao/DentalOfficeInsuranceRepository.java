package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.DentalOffice;
import com.bw.dentaldoor.entity.DentalOfficeInsurance;
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
public interface DentalOfficeInsuranceRepository extends JpaRepository<DentalOfficeInsurance, Long> {
    List<DentalOfficeInsurance> findAllByDentalOfficeAndStatus(DentalOffice dentalOffice, GenericStatusConstant status);

    Long countAllByDentalOfficeAndStatus(DentalOffice dentalOffice, GenericStatusConstant status);

    @Query("select d from DentalOfficeInsurance d join fetch d.insuranceCompany " +
            "where d.dentalOffice in ?1 and d.status = 'ACTIVE'")
    List<DentalOfficeInsurance> findActiveByDentalOffices(List<DentalOffice> dentalOffices);
}