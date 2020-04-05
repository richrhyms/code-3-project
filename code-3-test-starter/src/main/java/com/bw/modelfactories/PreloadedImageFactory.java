package com.bw.modelfactories;
import com.bw.entity.BwFile;

import com.bw.dentaldoor.entity.ImageTag;
import com.bw.dentaldoor.entity.PreloadedImage;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class PreloadedImageFactory implements FactoryHelper<PreloadedImage> {
    @Override
    public Class<PreloadedImage> getEntity() {
        return PreloadedImage.class;
    }

    @Override
    public PreloadedImage apply(Faker faker, ModelFactory factory) {
        PreloadedImage preloadedImage = new PreloadedImage();
        preloadedImage.setDateCreated(new Date());
        preloadedImage.setStatus(GenericStatusConstant.ACTIVE);
        preloadedImage.setBwFile(factory.create(BwFile.class));
        return preloadedImage;
    }
}
