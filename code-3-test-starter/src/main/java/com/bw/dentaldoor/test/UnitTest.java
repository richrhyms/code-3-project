package com.bw.dentaldoor.test;

import com.bw.modelfactories.ModelFactoryRegistrar;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.heywhy.springentityfactory.impl.ModelFactoryImpl;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.util.Random;

public abstract class UnitTest {
    protected ModelFactory modelFactory;
    protected ModelFactory pojoFactory;
    protected Faker faker;


    @BeforeEach
    void setUpModelFactory() {
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        faker = new Faker(new Random());
        modelFactory = new ModelFactoryImpl(faker, entityManager);
        modelFactory = new ModelFactoryImpl(faker);
        ModelFactoryRegistrar.register(modelFactory);
    }

    @AfterEach
    void distroymodelfactory() {
        modelFactory = null;
        pojoFactory = null;
    }
}
