package com.bw.modelfactories;

import com.bw.dentaldoor.entity.*;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class DentalProfessionalAddressFactory implements FactoryHelper<DentalProfessionalAddress> {
    @Override
    public Class<DentalProfessionalAddress> getEntity() {
        return DentalProfessionalAddress.class;
    }

    @Override
    public DentalProfessionalAddress apply(Faker faker, ModelFactory factory) {
        DentalProfessionalAddress dentalProfessionalLanguage = new DentalProfessionalAddress();
        dentalProfessionalLanguage.setDentalProfessional(factory.create(DentalProfessional.class));
        dentalProfessionalLanguage.setAddress(factory.create(Address.class));
        dentalProfessionalLanguage.setDateCreated(new Date());
        dentalProfessionalLanguage.setStatus(GenericStatusConstant.ACTIVE);
        return dentalProfessionalLanguage;
    }
}
