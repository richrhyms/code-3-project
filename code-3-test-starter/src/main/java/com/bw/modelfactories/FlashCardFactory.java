package com.bw.modelfactories;

import com.bw.dentaldoor.entity.FlashCardDeck;
import com.bw.dentaldoor.entity.FlashCardItem;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class FlashCardFactory implements FactoryHelper<FlashCardItem> {

    @Override
    public Class<FlashCardItem> getEntity() {
        return FlashCardItem.class;
    }

    @Override
    public FlashCardItem apply(Faker faker, ModelFactory factory) {
        FlashCardItem flashCard = new FlashCardItem();
        flashCard.setDateCreated(new Date());
        flashCard.setStatus(GenericStatusConstant.ACTIVE);
        flashCard.setQuestion(faker.lorem().sentence());
        flashCard.setAnswer(faker.lorem().sentence());
        flashCard.setHint(faker.lorem().sentence(25));
        flashCard.setFlashCardDeck(factory.create(FlashCardDeck.class));
        return flashCard;
    }
}