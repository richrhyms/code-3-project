package com.bw.modelfactories;
import com.bw.dentaldoor.enums.SubscriptionStatusConstant;
import java.util.Date;
import com.bw.dentaldoor.entity.PaymentInvoice;
import com.bw.enums.GenericStatusConstant;
import com.bw.dentaldoor.entity.SubscriptionCycle;
import com.bw.dentaldoor.entity.DentalOffice;

import com.bw.dentaldoor.entity.DentalOfficeSubscription;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

public class DentalOfficeSubscriptionFactory implements FactoryHelper<DentalOfficeSubscription> {
    @Override
    public Class<DentalOfficeSubscription> getEntity() {
        return DentalOfficeSubscription.class;
    }

    @Override
    public DentalOfficeSubscription apply(Faker faker, ModelFactory factory) {
        DentalOfficeSubscription subscription = new DentalOfficeSubscription();
        subscription.setDateCreated(new Date());
        subscription.setStatus(GenericStatusConstant.ACTIVE);
        subscription.setSubscriptionStatus(SubscriptionStatusConstant.PENDING);
        subscription.setSubscriptionCycle(factory.create(SubscriptionCycle.class));
        subscription.setPaymentInvoice(factory.create(PaymentInvoice.class));
        subscription.setDentalOffice(factory.create(DentalOffice.class));

        return subscription;
    }
}