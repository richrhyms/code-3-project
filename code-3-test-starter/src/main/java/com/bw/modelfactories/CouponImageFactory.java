package com.bw.modelfactories;
import com.bw.dentaldoor.entity.PreloadedImage;
import com.bw.dentaldoor.entity.PortalUser;
import java.util.Date;
import com.bw.dentaldoor.entity.Coupon;
import com.bw.enums.GenericStatusConstant;

import com.bw.dentaldoor.entity.CouponImage;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

public class CouponImageFactory implements FactoryHelper<CouponImage> {
    @Override
    public Class<CouponImage> getEntity() {
        return CouponImage.class;
    }

    @Override
    public CouponImage apply(Faker faker, ModelFactory factory) {
        CouponImage couponImage = new CouponImage();
        couponImage.setDateCreated(new Date());
        couponImage.setStatus(GenericStatusConstant.ACTIVE);
        couponImage.setDateDeactivated(new Date());
        couponImage.setImage(factory.create(PreloadedImage.class));
        couponImage.setCoupon(factory.create(Coupon.class));
        couponImage.setCreatedBy(factory.create(PortalUser.class));
        return couponImage;
    }
}