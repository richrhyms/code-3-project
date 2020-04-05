package com.bw.modelfactories;

import com.bw.dentaldoor.entity.Coupon;
import com.bw.dentaldoor.entity.DentalOffice;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.UUID;

public class CouponFactory  implements FactoryHelper<Coupon> {
    @Override
    public Class<Coupon> getEntity() {
        return Coupon.class;
    }

    @Override
    public Coupon apply(Faker faker, ModelFactory factory) {
        Coupon coupon = new Coupon();
        coupon.setCode(UUID.randomUUID().toString());
        coupon.setDescription(faker.lorem().sentence());
        coupon.setCreatedBy(factory.create(PortalUser.class));
        coupon.setDateCreated(new Date());
        coupon.setMaxUsageCount(3);
        coupon.setStartDate(new Date());
        coupon.setEndDate(new Date());
        coupon.setStatus(GenericStatusConstant.ACTIVE);
        coupon.setDentalOffice(factory.create(DentalOffice.class));
        return coupon;
    }
}
