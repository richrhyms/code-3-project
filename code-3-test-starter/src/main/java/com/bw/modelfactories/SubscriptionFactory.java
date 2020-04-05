package com.bw.modelfactories;
import java.util.Date;
import java.util.UUID;

import com.bw.enums.GenericStatusConstant;

import com.bw.dentaldoor.entity.Subscription;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

public class SubscriptionFactory  implements FactoryHelper<Subscription> {
    @Override
    public Class<Subscription> getEntity() {
        return Subscription.class;
    }

    @Override
    public Subscription apply(Faker faker, ModelFactory factory) {
        Subscription subscription = new Subscription();
        subscription.setName(faker.lorem().word());
        subscription.setCode(UUID.randomUUID().toString());
        subscription.setDateCreated(new Date());
        subscription.setStatus(GenericStatusConstant.ACTIVE);
        return subscription;
    }
}