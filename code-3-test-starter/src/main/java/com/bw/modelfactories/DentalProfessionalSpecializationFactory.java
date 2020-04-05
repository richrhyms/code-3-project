package com.bw.modelfactories;

import com.bw.dentaldoor.entity.DentalProfessional;
import com.bw.dentaldoor.entity.DentalProfessionalSpecialization;
import com.bw.dentaldoor.entity.Specialization;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class DentalProfessionalSpecializationFactory implements FactoryHelper<DentalProfessionalSpecialization> {
    @Override
    public Class<DentalProfessionalSpecialization> getEntity() {
        return DentalProfessionalSpecialization.class;
    }

    @Override
    public DentalProfessionalSpecialization apply(Faker faker, ModelFactory factory) {
        DentalProfessionalSpecialization dentalProfessionalSpecialization = new DentalProfessionalSpecialization();
        dentalProfessionalSpecialization.setDentalProfessional(factory.create(DentalProfessional.class));
        dentalProfessionalSpecialization.setSpecialization(factory.create(Specialization.class));
        dentalProfessionalSpecialization.setStatus(GenericStatusConstant.ACTIVE);
        dentalProfessionalSpecialization.setDateCreated(new Date());
        return dentalProfessionalSpecialization;
    }
}
