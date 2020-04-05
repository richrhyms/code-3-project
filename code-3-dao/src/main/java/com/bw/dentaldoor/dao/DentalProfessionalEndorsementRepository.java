package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.DentalProfessional;
import com.bw.dentaldoor.entity.DentalProfessionalEndorsement;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.pojo.GroupedCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jabir Minjibir <jminjibir@byteworks.com.ng>
 */
@Repository
public interface DentalProfessionalEndorsementRepository extends JpaRepository<DentalProfessionalEndorsement, Long> {

    Optional<DentalProfessionalEndorsement> findByIdAndCreatedBy(Long id, PortalUser portalUser);

    @Query("SELECT new com.bw.dentaldoor.pojo.GroupedCount(r.specialization.code, COUNT(r))" +
            " FROM DentalProfessionalEndorsement r" +
            " WHERE  r.dentalProfessional = ?1" +
            " AND r.status='ACTIVE'" +
            " GROUP BY r.specialization.code"
    )
    List<GroupedCount<String>> getEndorsementCount(DentalProfessional dentalProfessional);
}
