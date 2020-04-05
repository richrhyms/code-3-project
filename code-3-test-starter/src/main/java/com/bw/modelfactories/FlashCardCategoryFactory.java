package com.bw.modelfactories;

import com.bw.dentaldoor.entity.FlashCardCategory;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.UUID;

public class FlashCardCategoryFactory implements FactoryHelper<FlashCardCategory> {

    @Override
    public Class<FlashCardCategory> getEntity() {
        return FlashCardCategory.class;
    }

    @Override
    public FlashCardCategory apply(Faker faker, ModelFactory factory) {
        FlashCardCategory flashCardCategory = new FlashCardCategory();
        flashCardCategory.setDateCreated(new Date());
        flashCardCategory.setStatus(GenericStatusConstant.ACTIVE);
        flashCardCategory.setName(faker.name().firstName());
        flashCardCategory.setCode(UUID.randomUUID().toString());
        return flashCardCategory;
    }
}