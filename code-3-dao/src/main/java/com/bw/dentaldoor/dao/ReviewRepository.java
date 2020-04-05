package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.Review;
import com.bw.dentaldoor.pojo.GroupedCount;
import com.bw.enums.GenericStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByStatusAndId(GenericStatusConstant status, Long id);

    @Query("SELECT new com.bw.dentaldoor.pojo.GroupedCount(r.rating, COUNT(r))" +
            " FROM Review r" +
            " WHERE  r.entityId = ?1" +
            " AND r.status='ACTIVE'" +
            " AND r.entityName = 'DentalOffice'" +
            " GROUP BY r.rating"
    )
    List<GroupedCount<Integer>> reviewStatisticsForDentalOffice(Long entityId);

    @Query("SELECT new com.bw.dentaldoor.pojo.GroupedCount(r.rating, COUNT(r))" +
            " FROM Review r" +
            " WHERE  r.entityId = ?1" +
            " AND r.status='ACTIVE'" +
            " AND r.entityName = 'DentalProfessional'" +
            " GROUP BY r.rating"
    )
    List<GroupedCount<Integer>> reviewStatisticsForDentalProfessional(Long entityId);

}
