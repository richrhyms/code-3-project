package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.Coupon;
import com.bw.dentaldoor.entity.DentalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Jabir Minjibir <jminjibir@byteworks.com.ng>
 */

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCodeAndDentalOffice(String code, DentalOffice dentalOffice);

}
