package com.bw.modelfactories;

import com.bw.dentaldoor.entity.*;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class DentalOfficeProfessionalFactory implements FactoryHelper<DentalOfficeProfessional> {

    @Override
    public Class<DentalOfficeProfessional> getEntity() {
        return DentalOfficeProfessional.class;
    }

    @Override
    public DentalOfficeProfessional apply(Faker faker, ModelFactory factory) {
        DentalOfficeProfessional dentalProfessionalSpecialization = new DentalOfficeProfessional();
        dentalProfessionalSpecialization.setDentalProfessional(factory.create(DentalProfessional.class));
        dentalProfessionalSpecialization.setDentalOffice(factory.create(DentalOffice.class));
        dentalProfessionalSpecialization.setStatus(GenericStatusConstant.ACTIVE);
        dentalProfessionalSpecialization.setDateCreated(new Date());
        return dentalProfessionalSpecialization;
    }
}
