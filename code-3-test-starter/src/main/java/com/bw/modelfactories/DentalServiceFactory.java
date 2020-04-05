package com.bw.modelfactories;
import java.util.Date;
import java.util.UUID;

import com.bw.enums.GenericStatusConstant;

import com.bw.dentaldoor.entity.DentalService;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.UUID;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

public class DentalServiceFactory implements FactoryHelper<DentalService> {
    @Override
    public Class<DentalService> getEntity() {
        return DentalService.class;
    }

    @Override
    public DentalService apply(Faker faker, ModelFactory factory) {
        DentalService service = new DentalService();
        service.setIsAdditionalService(false);
        service.setName(UUID.randomUUID().toString());
        service.setStatus(GenericStatusConstant.ACTIVE);
        service.setDateCreated(new Date());
        service.setCode(UUID.randomUUID().toString());
        return service;
    }
}
