package com.bw.modelfactories;

import com.bw.dentaldoor.entity.DentalProfessional;
import com.bw.dentaldoor.entity.DentalProfessionalLicence;
import com.bw.dentaldoor.entity.State;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class DentalProfessionalLicenceFactory implements FactoryHelper<DentalProfessionalLicence> {
    @Override
    public Class<DentalProfessionalLicence> getEntity() {
        return DentalProfessionalLicence.class;
    }

    @Override
    public DentalProfessionalLicence apply(Faker faker, ModelFactory factory) {
        DentalProfessionalLicence dentalProfessionalLicence = new DentalProfessionalLicence();
        dentalProfessionalLicence.setName(faker.name().name());
        dentalProfessionalLicence.setLicenceNumber(faker.idNumber().valid());
        dentalProfessionalLicence.setDateIssued(faker.date().birthday());
//      TODO  dentalProfessionalLicence.setExpiryDate(faker.date().birthday());
        dentalProfessionalLicence.setStateOfIssuance(factory.create(State.class));
        dentalProfessionalLicence.setStatus(GenericStatusConstant.ACTIVE);
        dentalProfessionalLicence.setDentalProfessional(factory.create(DentalProfessional.class));
        dentalProfessionalLicence.setDateCreated(new Date());
        dentalProfessionalLicence.setLastUpdated(new Date());
        return dentalProfessionalLicence;
    }
}
