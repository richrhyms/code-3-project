package com.bw.dentaldoor.startup;

import com.bw.dentaldoor.adapter.CountryTypeAdapter;
import com.bw.dentaldoor.adapter.HibernateProxyTypeAdapter;
import com.bw.dentaldoor.adapter.StateTypeAdapter;
import com.bw.dentaldoor.dao.CityRepository;
import com.bw.dentaldoor.dao.CountryRepository;
import com.bw.dentaldoor.dao.StateRepository;
import com.bw.dentaldoor.entity.City;
import com.bw.dentaldoor.entity.Country;
import com.bw.dentaldoor.entity.State;
import com.bw.enums.GenericStatusConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Component
public class MasterRecordsLoader {
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final TransactionTemplate transactionTemplate;
    private final Gson gson;
    private final AutowireCapableBeanFactory beanFactory;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public MasterRecordsLoader(
            CountryRepository countryRepository, StateRepository stateRepository, CityRepository cityRepository,
            TransactionTemplate transactionTemplate, Gson gson, AutowireCapableBeanFactory beanFactory
    ) {
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.transactionTemplate = transactionTemplate;
        this.gson = gson;
        this.beanFactory = beanFactory;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        transactionTemplate.execute(tx -> {
            try {
                loadData();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            return null;
        });
    }

    private void loadData() throws IOException {
        if (countryRepository.count() == 0) {
            loadCountries();
        }
        logger.info("countries: " + countryRepository.count());

        if (stateRepository.count() == 0) {
            loadStates();
        }
        logger.info("states: " + stateRepository.count());

        if (cityRepository.count() == 0) {
            loadCities();
        }
        logger.info("cities: " + cityRepository.count());
    }

    private void loadCountries() throws IOException {
        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/master_records/country.json"))) {
            Country[] dtoList = gson.fromJson(gson.newJsonReader(reader), Country[].class);
            List<Country> countries = new ArrayList<>();
            for (Country country : dtoList) {
                country.setId(null);
                countries.add(country);
            }
            countryRepository.saveAll(countries);
        }
    }

    private void loadStates() throws IOException {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        b.registerTypeAdapterFactory(new TypeAdapterFactory() {
            @Override
            @SuppressWarnings("unchecked")
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
                return (Country.class.isAssignableFrom(type.getRawType())
                        ? (TypeAdapter<T>) beanFactory.createBean(CountryTypeAdapter.class)
                        : null);
            }
        });
        Gson gson = b.create();

        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/master_records/state.json"))) {
            State[] dtoList = gson.fromJson(gson.newJsonReader(reader), State[].class);
            List<State> newStates = new ArrayList<>();
            for (State state : dtoList) {
                state.setId(null);
                state.setStatus(GenericStatusConstant.ACTIVE);
                newStates.add(state);
            }
            stateRepository.saveAll(newStates);
        }
    }

    private void loadCities() throws IOException {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        b.registerTypeAdapterFactory(new TypeAdapterFactory() {
            @Override
            @SuppressWarnings("unchecked")
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
                return (State.class.isAssignableFrom(type.getRawType())
                        ? (TypeAdapter<T>) beanFactory.createBean(StateTypeAdapter.class)
                        : null);
            }
        });
        Gson gson = b.create();

        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/master_records/city.json"))) {
            City[] dtoList = gson.fromJson(gson.newJsonReader(reader), City[].class);
            List<City> cities = new ArrayList<>();
            for (City city : dtoList) {
                city.setId(null);
                city.setStatus(GenericStatusConstant.ACTIVE);
                cities.add(city);
            }
            cityRepository.saveAll(cities);
        }
    }
}
