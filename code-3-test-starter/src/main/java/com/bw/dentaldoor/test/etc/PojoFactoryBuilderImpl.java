package com.bw.dentaldoor.test.etc;

import com.github.heywhy.springentityfactory.contracts.FactoryFunction;
import com.github.heywhy.springentityfactory.impl.EntityFactoryBuilderImpl;
import com.github.javafaker.Faker;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Gibah Joseph
 * Email: gibahjoe@gmail.com
 * Feb, 2020
 **/
public class PojoFactoryBuilderImpl<T> extends EntityFactoryBuilderImpl<T> {

    public PojoFactoryBuilderImpl(Class<T> tClass, Map<Class, FactoryFunction> definitions, Faker faker, Function<T, T> operator) {
        super(tClass, definitions, faker, null, operator);
        System.out.println("-=====>" + definitions);
    }

    @Override
    public List<T> create(int count) {
        List<T> instances = make(count);

        return instances;
    }
}
