package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("select s from Subscription s where s.status = 'ACTIVE' and lower(s.code) = lower(?1) ")
    Optional<Subscription> findActiveByCode(String code);
}