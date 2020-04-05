package com.bw.modelfactories;
import com.bw.dentaldoor.entity.DentalService;
import java.util.Date;
import java.util.UUID;

import com.bw.enums.GenericStatusConstant;
import com.bw.dentaldoor.entity.DentalOffice;

import com.bw.dentaldoor.entity.DentalOfficeService;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

public class DentalOfficeServiceFactory implements FactoryHelper<DentalOfficeService> {
    @Override
    public Class<DentalOfficeService> getEntity() {
        return DentalOfficeService.class;
    }

    @Override
    public DentalOfficeService apply(Faker faker, ModelFactory factory) {
        DentalOfficeService service = new DentalOfficeService();
        service.setDateCreated(new Date());
        service.setStatus(GenericStatusConstant.ACTIVE);
        service.setDentalService(factory.create(DentalService.class));
        service.setDentalOffice(factory.create(DentalOffice.class));
        return service;
    }
}