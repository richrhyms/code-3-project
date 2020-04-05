package com.bw.modelfactories;

import com.bw.dentaldoor.entity.*;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class DentalOfficeAddressFactory implements FactoryHelper<DentalOfficeAddress> {

    @Override
    public Class<DentalOfficeAddress> getEntity() {
        return DentalOfficeAddress.class;
    }

    @Override
    public DentalOfficeAddress apply(Faker faker, ModelFactory factory) {
        DentalOfficeAddress dentalOfficeAddress = new DentalOfficeAddress();
        dentalOfficeAddress.setDentalOffice(factory.create(DentalOffice.class));
        dentalOfficeAddress.setAddress(factory.create(Address.class));
        dentalOfficeAddress.setDateCreated(new Date());
        dentalOfficeAddress.setStatus(GenericStatusConstant.ACTIVE);
        return dentalOfficeAddress;
    }
}
