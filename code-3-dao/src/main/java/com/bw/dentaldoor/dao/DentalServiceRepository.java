package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.DentalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface DentalServiceRepository extends JpaRepository<DentalService, Long> {
    @Query("select s from DentalService s where s.status ='ACTIVE' and s.code in ?1 and s.isAdditionalService = false ")
    List<DentalService> findActiveByCodes(List<String> codes);

    @Query("select s from DentalService s where s.status ='ACTIVE' and s.code in ?1 and s.isAdditionalService = true ")
    List<DentalService> findActiveAdditionalServiceByCodes(List<String> codes);
}