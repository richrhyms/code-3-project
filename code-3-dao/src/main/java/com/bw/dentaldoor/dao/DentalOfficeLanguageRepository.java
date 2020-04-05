package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.*;
import com.bw.enums.GenericStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface DentalOfficeLanguageRepository extends JpaRepository<DentalOfficeLanguage, Long> {
    List<DentalOfficeLanguage> findAllByDentalOfficeAndStatus(DentalOffice dentalOffice, GenericStatusConstant status);

    Long countAllByDentalOfficeAndStatus(DentalOffice dentalOffice, GenericStatusConstant status);

    @Query("SELECT dpl from DentalOfficeLanguage dpl JOIN FETCH dpl.language where dpl.status = 'ACTIVE' and dpl.dentalOffice IN ?1")
    List<DentalOfficeLanguage> findActiveByDentalOffices(List<DentalOffice> dentalOffices);
}