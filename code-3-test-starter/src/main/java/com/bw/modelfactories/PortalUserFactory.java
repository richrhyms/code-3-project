package com.bw.modelfactories;

import com.bw.dentaldoor.entity.PortalUser;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.UUID;

/**
 * @author Gibah Joseph
 * Email: gibahjoe@gmail.com
 * Oct, 2019
 **/
public class PortalUserFactory implements FactoryHelper<PortalUser> {
    @Override
    public Class<PortalUser> getEntity() {
        return PortalUser.class;
    }

    @Override
    public PortalUser apply(Faker faker, ModelFactory factory) {
        PortalUser workspaceUser = new PortalUser();
        workspaceUser.setFirstName(UUID.randomUUID().toString());
        workspaceUser.setEmail(faker.internet().emailAddress());
        workspaceUser.setLastName(faker.name().lastName());
        workspaceUser.setDisplayName(String.format("%s %s", workspaceUser.getFirstName(), workspaceUser.getLastName()));
        workspaceUser.setDateCreated(new Date());
        workspaceUser.setStatus(GenericStatusConstant.ACTIVE);
        workspaceUser.setUsername(faker.internet().emailAddress());
        workspaceUser.setPhoneNumber(faker.phoneNumber().phoneNumber());
        workspaceUser.setUserId(UUID.randomUUID().toString());
        workspaceUser.setLastUpdated(new Date());
        return workspaceUser;
    }
}
