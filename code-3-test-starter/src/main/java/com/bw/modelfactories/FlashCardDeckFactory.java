package com.bw.modelfactories;

import com.bw.dentaldoor.entity.FlashCardDeck;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class FlashCardDeckFactory implements FactoryHelper<FlashCardDeck> {

    @Override
    public Class<FlashCardDeck> getEntity() {
        return FlashCardDeck.class;
    }

    @Override
    public FlashCardDeck apply(Faker faker, ModelFactory factory) {
        FlashCardDeck flashCardSet = new FlashCardDeck();
        flashCardSet.setDateCreated(new Date());
        flashCardSet.setStatus(GenericStatusConstant.ACTIVE);
        flashCardSet.setDescription(faker.lorem().sentence());
        flashCardSet.setIsPrivate(false);
        flashCardSet.setTitle(faker.lorem().sentence(15));
        flashCardSet.setCreatedBy(factory.create(PortalUser.class));
        return flashCardSet;
    }
}