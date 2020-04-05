package com.bw.modelfactories;
import com.bw.enums.GenericStatusConstant;
import com.bw.dentaldoor.entity.State;

import com.bw.dentaldoor.entity.City;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.UUID;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

public class CityFactory implements FactoryHelper<City> {
    @Override
    public Class<City> getEntity() {
        return City.class;
    }

    @Override
    public City apply(Faker faker, ModelFactory factory) {
        City city = new City();
        city.setName(faker.address().cityName());
        city.setCode(UUID.randomUUID().toString());
        city.setStatus(GenericStatusConstant.ACTIVE);
        city.setState(factory.create(State.class));
        return city;
    }
}