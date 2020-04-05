package com.bw.modelfactories;

import com.bw.dentaldoor.entity.PortalAccount;
import com.bw.dentaldoor.enums.PortalAccountTypeConstant;
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
public class PortalAccountFactory implements FactoryHelper<PortalAccount> {

    @Override
    public Class<PortalAccount> getEntity() {
        return PortalAccount.class;
    }

    @Override
    public PortalAccount apply(Faker faker, ModelFactory factory) {
        PortalAccount portalAccount = new PortalAccount();
        portalAccount.setType(PortalAccountTypeConstant.DENTAL_DOOR_ADMIN);
        portalAccount.setName(faker.name().firstName());
        portalAccount.setCode(UUID.randomUUID().toString());
        portalAccount.setStatus(GenericStatusConstant.ACTIVE);
        Date now = new Date();
        portalAccount.setDateCreated(now);
        portalAccount.setLastUpdated(now);
        return portalAccount;
    }
}
