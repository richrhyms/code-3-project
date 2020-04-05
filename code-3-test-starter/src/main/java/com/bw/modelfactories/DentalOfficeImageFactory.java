package com.bw.modelfactories;

import com.bw.dentaldoor.entity.DentalOffice;
import com.bw.dentaldoor.entity.DentalOfficeImage;
import com.bw.entity.BwFile;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class DentalOfficeImageFactory implements FactoryHelper<DentalOfficeImage> {

    @Override
    public Class<DentalOfficeImage> getEntity() {
        return DentalOfficeImage.class;
    }

    @Override
    public DentalOfficeImage apply(Faker faker, ModelFactory factory) {
        DentalOfficeImage image = new DentalOfficeImage();
        image.setDateCreated(new Date());
        image.setStatus(GenericStatusConstant.ACTIVE);
        image.setBwFile(factory.create(BwFile.class));;
        image.setDentalOffice(factory.create(DentalOffice.class));
        return image;
    }
}
