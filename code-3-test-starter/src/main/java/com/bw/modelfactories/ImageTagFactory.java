package com.bw.modelfactories;
import java.util.Date;

import com.bw.dentaldoor.entity.Country;
import com.bw.dentaldoor.entity.ImageTag;
import com.bw.dentaldoor.entity.State;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.UUID;

public class ImageTagFactory implements FactoryHelper<ImageTag> {
    @Override
    public Class<ImageTag> getEntity() {
        return ImageTag.class;
    }

    @Override
    public ImageTag apply(Faker faker, ModelFactory factory) {
        ImageTag imageTag = new ImageTag();
        imageTag.setDateCreated(new Date());
        imageTag.setStatus(GenericStatusConstant.ACTIVE);
        imageTag.setName(faker.name().username());
        imageTag.setCode(UUID.randomUUID().toString());
        return imageTag;
    }
}
