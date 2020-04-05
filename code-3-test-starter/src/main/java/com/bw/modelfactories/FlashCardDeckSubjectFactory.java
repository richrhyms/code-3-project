package com.bw.modelfactories;

import com.bw.dentaldoor.entity.*;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class FlashCardDeckSubjectFactory implements FactoryHelper<FlashCardDeckSubject> {

    @Override
    public Class<FlashCardDeckSubject> getEntity() {
        return FlashCardDeckSubject.class;
    }

    @Override
    public FlashCardDeckSubject apply(Faker faker, ModelFactory factory) {
        FlashCardDeckSubject FlashCardDeckSubject = new FlashCardDeckSubject();
        FlashCardDeckSubject.setDateCreated(new Date());
        FlashCardDeckSubject.setStatus(GenericStatusConstant.ACTIVE);
        FlashCardDeckSubject.setName(faker.name().firstName());
        FlashCardDeckSubject.setFlashCardDeck(factory.create(FlashCardDeck.class));
        return FlashCardDeckSubject;
    }
}