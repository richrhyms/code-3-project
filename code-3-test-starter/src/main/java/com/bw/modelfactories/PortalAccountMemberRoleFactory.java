package com.bw.modelfactories;

import com.bw.dentaldoor.entity.PortalAccountMemberRole;
import com.bw.dentaldoor.entity.PortalAccountMembership;
import com.bw.dentaldoor.entity.PortalAccountTypeRole;
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
public class PortalAccountMemberRoleFactory implements FactoryHelper<PortalAccountMemberRole> {

    @Override
    public Class<PortalAccountMemberRole> getEntity() {
        return PortalAccountMemberRole.class;
    }

    @Override
    public PortalAccountMemberRole apply(Faker faker, ModelFactory factory) {
        PortalAccountMemberRole workspaceUser = new PortalAccountMemberRole();
        workspaceUser.setMembership(factory.create(PortalAccountMembership.class));
        workspaceUser.setRole(factory.create(PortalAccountTypeRole.class));
        workspaceUser.setDateCreated(new Date());
        workspaceUser.setStatus(GenericStatusConstant.ACTIVE);
        return workspaceUser;
    }
}
