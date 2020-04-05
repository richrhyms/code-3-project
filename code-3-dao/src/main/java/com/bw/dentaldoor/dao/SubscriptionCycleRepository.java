package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.Subscription;
import com.bw.dentaldoor.entity.SubscriptionCycle;
import com.bw.dentaldoor.enums.BillingCycleConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface SubscriptionCycleRepository extends JpaRepository<SubscriptionCycle,Long> {
    @Query("select sc from SubscriptionCycle sc join fetch sc.revenueItem " +
            "where sc.status='ACTIVE' and sc.subscription.status ='ACTIVE'" +
            " and lower(sc.subscription.code) = lower(?1) and sc.billingCycle = ?2 ")
    Optional<SubscriptionCycle> findActiveBySubscriptionCodeAndBillingCycle(String subscriptionCode, BillingCycleConstant billingCycle);
}