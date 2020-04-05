package com.bw.modelfactories;

import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.entity.PortalUserImage;
import com.bw.entity.BwFile;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class PortalUserImageFactory implements FactoryHelper<PortalUserImage> {

    @Override
    public Class<PortalUserImage> getEntity() {
        return PortalUserImage.class;
    }

    @Override
    public PortalUserImage apply(Faker faker, ModelFactory factory) {
        PortalUserImage portalUserImage = new PortalUserImage();
        portalUserImage.setStatus(GenericStatusConstant.ACTIVE);
        portalUserImage.setPortalUser(factory.create(PortalUser.class));
        portalUserImage.setBwFile(factory.create(BwFile.class));
        portalUserImage.setDateCreated(new Date());
        return portalUserImage;
    }

}