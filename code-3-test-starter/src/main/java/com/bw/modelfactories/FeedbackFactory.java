package com.bw.modelfactories;

import com.bw.dentaldoor.entity.Feedback;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class FeedbackFactory implements FactoryHelper<Feedback> {
    @Override
    public Class<Feedback> getEntity() {
        return Feedback.class;
    }

    @Override
    public Feedback apply(Faker faker, ModelFactory factory) {
        Feedback feedback = new Feedback();
        feedback.setName(faker.name().name());
        feedback.setComment(faker.lorem().sentence());
        feedback.setStatus(GenericStatusConstant.ACTIVE);
        feedback.setPhoneNumber(faker.phoneNumber().phoneNumber());
        feedback.setEmail(faker.internet().emailAddress());
        Date now = new Date();
        feedback.setLastUpdated(now);
        feedback.setDateCreated(now);
        return feedback;
    }
}
