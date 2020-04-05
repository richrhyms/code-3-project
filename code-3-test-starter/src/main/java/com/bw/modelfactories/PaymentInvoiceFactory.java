package com.bw.modelfactories;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.bw.dentaldoor.enums.PaymentStatusConstant;
import com.bw.enums.GenericStatusConstant;
import com.bw.dentaldoor.entity.PaymentTransaction;
import com.bw.entity.BwFile;

import com.bw.dentaldoor.entity.PaymentInvoice;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

public class PaymentInvoiceFactory implements FactoryHelper<PaymentInvoice> {
    @Override
    public Class<PaymentInvoice> getEntity() {
        return PaymentInvoice.class;
    }

    @Override
    public PaymentInvoice apply(Faker faker, ModelFactory factory) {
        PaymentInvoice paymentInvoice = new PaymentInvoice();
        paymentInvoice.setAmount(new BigDecimal("0"));
        paymentInvoice.setInvoiceNumber(UUID.randomUUID().toString());
        paymentInvoice.setDateCreated(new Date());
        paymentInvoice.setStatus(GenericStatusConstant.ACTIVE);
        paymentInvoice.setCustomerName(faker.name().fullName());
        paymentInvoice.setCustomerEmail(faker.internet().emailAddress());
        paymentInvoice.setCustomerPhoneNumber(faker.phoneNumber().phoneNumber());
        paymentInvoice.setPaymentStatus(PaymentStatusConstant.NOT_PAID);

        return paymentInvoice;
    }
}