package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.DentalOffice;
import com.bw.dentaldoor.entity.DentalOfficeSubscription;
import com.bw.dentaldoor.entity.SubscriptionCycle;
import com.bw.dentaldoor.enums.SubscriptionStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface DentalOfficeSubscriptionRepository extends JpaRepository<DentalOfficeSubscription, Long> {
    @Query("select dos from DentalOfficeSubscription dos where dos.status='ACTIVE' " +
            "and dos.dentalOffice = ?1 and dos.subscriptionCycle = ?2 and dos.subscriptionStatus = ?3 ")
    Optional<DentalOfficeSubscription> findActiveByDentalOfficeAndCycleAndSubscriptionStatus(DentalOffice dentalOffice,
                                                                                             SubscriptionCycle cycle,
                                                                                             SubscriptionStatusConstant subscriptionStatus);
}