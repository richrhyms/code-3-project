package com.bw.dentaldoor.test.etc;

import com.github.heywhy.springentityfactory.contracts.EntityFactoryBuilder;
import com.github.heywhy.springentityfactory.contracts.FactoryFunction;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Gibah Joseph
 * Email: gibahjoe@gmail.com
 * Feb, 2020
 **/
public class PojoFactoryImpl implements ModelFactory {

    private final Map<String, FactoryFunction> states = new HashMap<>();
    private final Map<Class, FactoryFunction> definitions = new HashMap<>();
    private Faker faker;

    public PojoFactoryImpl(Faker faker) {
        this.faker = faker;
    }

    @Override
    public <T> void register(FactoryHelper<T> factoryHelper) {
        define(factoryHelper.getEntity(), faker1 -> factoryHelper.apply(faker1, this));
    }

    @Override
    public <T> void register(Class<? extends FactoryHelper<T>> factoryHelper) {
        try {
            register(factoryHelper.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            String msg = "Unable to create instance of [" + factoryHelper.getSimpleName() + "].";
            throw new IllegalArgumentException(msg, e);
        }
    }

    @Override
    public ModelFactory define(Class model, FactoryFunction builder) {
        definitions.put(model, builder);
        return this;
    }

    @Override
    public <T> T create(Class<T> model) {
        return (T) of(model).create();
    }

    @Override
    public <T> List<T> create(Class<T> model, int count) {
        return (List<T>) of(model).create(count);
    }

    @Override
    public <T> T make(Class<T> model) {
        return (T) of(model).make();
    }

    @Override
    public <T> List<T> make(Class<T> model, int count) {
        return of(model).make(count);
    }

    @Override
    public <T> EntityFactoryBuilder<T> pipe(Class<T> model) {
        return of(model);
    }

    private String getStateName(Class model, String name) {
        return model.getSimpleName() + '@' + name;
    }

    private <T> EntityFactoryBuilder of(Class<T> model) {
        return of(model, null);
    }

    private <T> EntityFactoryBuilder of(Class<T> model, Function<T, T> operator) {
        return new PojoFactoryBuilderImpl(model, definitions, faker, operator);
    }
}
