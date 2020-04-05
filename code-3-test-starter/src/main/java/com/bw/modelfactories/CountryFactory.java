package com.bw.modelfactories;

import com.bw.dentaldoor.entity.Country;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

public class CountryFactory implements FactoryHelper<Country> {
    @Override
    public Class<Country> getEntity() {
        return Country.class;
    }

    @Override
    public Country apply(Faker faker, ModelFactory factory) {
        Country country = new Country();
        country.setAlpha3(faker.country().countryCode3());
        country.setAlpha2(faker.country().countryCode2());
        country.setName(faker.country().name());
        country.setInternationalDialingCode(faker.phoneNumber().phoneNumber());
        return country;
    }
}
