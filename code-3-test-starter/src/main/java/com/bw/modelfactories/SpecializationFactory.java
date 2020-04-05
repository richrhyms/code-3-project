package com.bw.modelfactories;

import com.bw.dentaldoor.entity.Specialization;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.UUID;

public class SpecializationFactory implements FactoryHelper<Specialization> {

    @Override
    public Class<Specialization> getEntity() {
        return Specialization.class;
    }

    @Override
    public Specialization apply(Faker faker, ModelFactory factory) {
        Specialization specialization = new Specialization();
        specialization.setCode(UUID.randomUUID().toString());
        specialization.setName(faker.country().name());
        specialization.setStatus(GenericStatusConstant.ACTIVE);
        return specialization;
    }
}
