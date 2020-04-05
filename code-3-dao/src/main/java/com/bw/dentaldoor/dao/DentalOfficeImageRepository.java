package com.bw.dentaldoor.dao;


import com.bw.dentaldoor.entity.DentalOffice;
import com.bw.dentaldoor.entity.DentalOfficeImage;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.entity.PortalUserImage;
import com.bw.enums.GenericStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DentalOfficeImageRepository extends JpaRepository<DentalOfficeImage, Long> {

    Optional<DentalOfficeImage> findByStatusAndDentalOffice(GenericStatusConstant status, DentalOffice dentalOffice);

    Optional<DentalOfficeImage> findByIdAndStatus(Long id, GenericStatusConstant status);

    @Query("select pi from DentalOfficeImage pi where pi.dentalOffice in ?1 and pi.status='ACTIVE'")
    List<DentalOfficeImage> findActiveByDentalOffices(List<DentalOffice> dentalOffices);
}
