package com.bw.modelfactories;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.entity.ImageTag;

import com.bw.dentaldoor.entity.PreloadedImage;
import com.bw.dentaldoor.entity.PreloadedImageTag;
import com.bw.entity.BwFile;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class PreloadedImageTagFactory implements FactoryHelper<PreloadedImageTag> {
    @Override
    public Class<PreloadedImageTag> getEntity() {
        return PreloadedImageTag.class;
    }

    @Override
    public PreloadedImageTag apply(Faker faker, ModelFactory factory) {
        PortalUser user = factory.create(PortalUser.class);
        PreloadedImageTag preloadedImageTag = new PreloadedImageTag();
        preloadedImageTag.setDateCreated(new Date());
        preloadedImageTag.setStatus(GenericStatusConstant.ACTIVE);
        preloadedImageTag.setDateDeactivated(new Date());
        preloadedImageTag.setPreloadedImage(factory.create(PreloadedImage.class));
        preloadedImageTag.setImageTag(factory.create(ImageTag.class));
        preloadedImageTag.setDeactivatedBy(user);
        preloadedImageTag.setCreatedBy(user);
        return preloadedImageTag;
    }
}
