package com.bw.modelfactories;
import com.bw.dentaldoor.enums.BillingCycleConstant;
import java.util.Date;
import com.bw.dentaldoor.entity.Subscription;
import com.bw.enums.GenericStatusConstant;
import com.bw.dentaldoor.entity.RevenueItem;

import com.bw.dentaldoor.entity.SubscriptionCycle;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

public class SubscriptionCycleFactory  implements FactoryHelper<SubscriptionCycle> {
    @Override
    public Class<SubscriptionCycle> getEntity() {
        return SubscriptionCycle.class;
    }

    @Override
    public SubscriptionCycle apply(Faker faker, ModelFactory factory) {
        SubscriptionCycle cycle = new SubscriptionCycle();
        cycle.setDateCreated(new Date());
        cycle.setStatus(GenericStatusConstant.ACTIVE);
        cycle.setBillingCycle(BillingCycleConstant.MONTHLY);
        cycle.setSubscription(factory.create(Subscription.class));
        cycle.setRevenueItem(factory.create(RevenueItem.class));
        return cycle;
    }
}