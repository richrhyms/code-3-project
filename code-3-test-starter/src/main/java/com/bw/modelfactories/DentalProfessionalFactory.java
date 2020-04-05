package com.bw.modelfactories;

import com.bw.dentaldoor.entity.DentalProfessional;
import com.bw.dentaldoor.entity.Language;
import com.bw.dentaldoor.entity.PortalAccount;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.UUID;

public class DentalProfessionalFactory implements FactoryHelper<DentalProfessional> {

    @Override
    public Class<DentalProfessional> getEntity() {
        return DentalProfessional.class;
    }

    @Override
    public DentalProfessional apply(Faker faker, ModelFactory factory) {
        DentalProfessional dentalProfessional = new DentalProfessional();
        dentalProfessional.setDateCreated(new Date());
        dentalProfessional.setStatus(GenericStatusConstant.ACTIVE);
        dentalProfessional.setWebsite(faker.lorem().sentence());
        dentalProfessional.setWebsite(faker.internet().url());
        dentalProfessional.setDateCreated(new Date());
        dentalProfessional.setStatus(GenericStatusConstant.ACTIVE);
        dentalProfessional.setPortalUser(factory.create(PortalUser.class));
        return dentalProfessional;
    }
}

