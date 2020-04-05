/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bw.dentaldoor.adapter;

import com.bw.dentaldoor.dao.CountryRepository;
import com.bw.dentaldoor.entity.Country;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.io.IOException;

public class CountryTypeAdapter extends TypeAdapter<Country> {

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @Override
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            return (Country.class.isAssignableFrom(type.getRawType())
                    ? (TypeAdapter<T>) new CountryTypeAdapter()
                    : null);
        }
    };

    @Inject
    private CountryRepository locationDao;

    @Override
    public Country read(JsonReader in) throws IOException {
        String value = in.nextString();
        return StringUtils.isBlank(value) ? null : locationDao.findByName(value).get();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void write(JsonWriter out, Country value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.value(value.getName());
    }
}
