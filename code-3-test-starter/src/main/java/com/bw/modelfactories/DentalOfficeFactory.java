package com.bw.modelfactories;

import com.bw.dentaldoor.entity.DentalOffice;
import com.bw.dentaldoor.entity.PortalAccount;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.enums.PortalAccountTypeConstant;
import com.bw.enums.GenericStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.UUID;

public class DentalOfficeFactory implements FactoryHelper<DentalOffice> {
    @Override
    public Class<DentalOffice> getEntity() {
        return DentalOffice.class;
    }

    @Override
    public DentalOffice apply(Faker faker, ModelFactory factory) {
        DentalOffice office = new DentalOffice();
        office.setName(UUID.randomUUID().toString());
        office.setPortalAccount(factory.pipe(PortalAccount.class)
                .then(it -> {
                    it.setType(PortalAccountTypeConstant.DENTAL_OFFICE);
                    return it;
                }).create());
        office.setSummary(faker.company().name());
        office.setStatus(GenericStatusConstant.ACTIVE);
        office.setAdditionalInformation(faker.lorem().sentence());
        office.setDateCreated(new Date());
        office.setStatus(GenericStatusConstant.ACTIVE);
        office.setCreatedBy(factory.create(PortalUser.class));
        return office;
    }
}
