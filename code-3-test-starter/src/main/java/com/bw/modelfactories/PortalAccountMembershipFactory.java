package com.bw.modelfactories;

import com.bw.dentaldoor.entity.PortalAccount;
import com.bw.dentaldoor.entity.PortalAccountMembership;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

/**
 * @author Gibah Joseph
 * Email: gibahjoe@gmail.com
 * Oct, 2019
 **/
public class PortalAccountMembershipFactory implements FactoryHelper<PortalAccountMembership> {

    @Override
    public Class<PortalAccountMembership> getEntity() {
        return PortalAccountMembership.class;
    }

    @Override
    public PortalAccountMembership apply(Faker faker, ModelFactory factory) {
        PortalAccountMembership workspaceUser = new PortalAccountMembership();
        workspaceUser.setPortalUser(factory.create(PortalUser.class));
        workspaceUser.setPortalAccount(factory.create(PortalAccount.class));
        workspaceUser.setStatus(GenericStatusConstant.ACTIVE);
        workspaceUser.setDateCreated(new Date());
        return workspaceUser;
    }
}
