package com.bw.modelfactories;

import com.bw.dentaldoor.entity.Address;
import com.bw.dentaldoor.entity.City;
import com.bw.dentaldoor.entity.Country;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

public class AddressFactory implements FactoryHelper<Address> {

    @Override
    public Class<Address> getEntity() {
        return Address.class;
    }

    @Override
    public Address apply(Faker faker, ModelFactory factory) {
        Address address = new Address();
        address.setStreet(faker.address().streetAddress());
        address.setZipCode(faker.address().zipCode());
        address.setHouseNumber(faker.address().buildingNumber());
        address.setCity(factory.create(City.class));
        address.setDateCreated(new Date());
        return address;
    }
}
