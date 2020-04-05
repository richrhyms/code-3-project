package com.bw.modelfactories;

import com.bw.dentaldoor.entity.PortalAccountTypeRole;
import com.bw.dentaldoor.enums.PortalAccountTypeConstant;
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
public class PortalAccountTypeRoleFactory implements FactoryHelper<PortalAccountTypeRole> {

    @Override
    public Class<PortalAccountTypeRole> getEntity() {
        return PortalAccountTypeRole.class;
    }

    @Override
    public PortalAccountTypeRole apply(Faker faker, ModelFactory factory) {
        PortalAccountTypeRole role = new PortalAccountTypeRole();
        role.setName(faker.idNumber().valid());
        role.setDisplayName(role.getName());
        role.setStatus(GenericStatusConstant.ACTIVE);
        role.setAccountType(PortalAccountTypeConstant.DENTAL_DOOR_ADMIN);
        Date now = new Date();
        role.setDateCreated(now);
        return role;
    }
}
