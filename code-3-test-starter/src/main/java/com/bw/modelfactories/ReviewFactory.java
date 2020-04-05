package com.bw.modelfactories;

import com.bw.dentaldoor.entity.DentalOffice;
import com.bw.dentaldoor.entity.Review;
import com.bw.dentaldoor.enums.ReviewRecordSourceConstant;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class ReviewFactory implements FactoryHelper<Review> {
    @Override
    public Class<Review> getEntity() {
        return Review.class;
    }

    @Override
    public Review apply(Faker faker, ModelFactory factory) {
        Review review = new Review();
        review.setName(faker.name().firstName());
        review.setEmail("info12@byteworks.com.ng");
        review.setPhoneNumber("+23470789478524");
        review.setComment(faker.lorem().sentence());
        review.setRating(1);
        review.setDateCreated(new Date());
        review.setLastUpdated(new Date());
        review.setStatus(GenericStatusConstant.ACTIVE);
        review.setEntityName(DentalOffice.class.getSimpleName());
        review.setEntityId(1L);
        review.setRecordSource(ReviewRecordSourceConstant.DENTAL_DOOR);
        return review;
    }
}