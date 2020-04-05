package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.Coupon;
import com.bw.dentaldoor.entity.CouponImage;
import com.bw.enums.GenericStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface CouponImageRepository extends JpaRepository<CouponImage, Long> {
    @Query("select ci from CouponImage ci where ci.coupon = ?1 and ci.status='ACTIVE'")
    List<CouponImage> findActiveByCoupon(Coupon coupon);

    Long countByCouponAndStatus(Coupon coupon, GenericStatusConstant status);
}