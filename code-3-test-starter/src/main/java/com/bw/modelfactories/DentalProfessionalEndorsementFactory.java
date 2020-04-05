package com.bw.modelfactories;

import com.bw.dentaldoor.entity.DentalProfessional;
import com.bw.dentaldoor.entity.DentalProfessionalEndorsement;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.entity.Specialization;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.UUID;

public class DentalProfessionalEndorsementFactory implements FactoryHelper<DentalProfessionalEndorsement> {

    @Override
    public Class<DentalProfessionalEndorsement> getEntity() {
        return DentalProfessionalEndorsement.class;
    }

    @Override
    public DentalProfessionalEndorsement apply(Faker faker, ModelFactory factory) {
        DentalProfessionalEndorsement dentalProfessionalEndorsement = new DentalProfessionalEndorsement();

        dentalProfessionalEndorsement.setSpecialization(factory.create(Specialization.class));
        dentalProfessionalEndorsement.setDentalProfessional(factory.create(DentalProfessional.class));
        dentalProfessionalEndorsement.setComment(UUID.randomUUID().toString());
        dentalProfessionalEndorsement.setStatus(GenericStatusConstant.ACTIVE);
        dentalProfessionalEndorsement.setCreatedBy(factory.create(PortalUser.class));
        dentalProfessionalEndorsement.setDateCreated(new Date());

        return dentalProfessionalEndorsement;
    }

}
