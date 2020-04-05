package com.bw.modelfactories;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.bw.dentaldoor.enums.RevenueItemTypeConstant;
import com.bw.enums.GenericStatusConstant;

import com.bw.dentaldoor.entity.RevenueItem;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

public class RevenueItemFactory implements FactoryHelper<RevenueItem> {
    @Override
    public Class<RevenueItem> getEntity() {
        return RevenueItem.class;
    }

    @Override
    public RevenueItem apply(Faker faker, ModelFactory factory) {
        RevenueItem item = new RevenueItem();
        item.setName(faker.lorem().word());
        item.setCode(UUID.randomUUID().toString());
        item.setAmount(new BigDecimal("100"));
        item.setDescription(faker.lorem().sentence());
        item.setDateCreated(new Date());
        item.setStatus(GenericStatusConstant.ACTIVE);
        item.setType(RevenueItemTypeConstant.SUBSCRIPTION);

        return item;
    }
}