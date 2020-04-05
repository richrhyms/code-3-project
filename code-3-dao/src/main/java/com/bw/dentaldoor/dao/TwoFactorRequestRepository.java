package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.TwoFactorRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface TwoFactorRequestRepository extends JpaRepository<TwoFactorRequest, Long> {
}