/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bw.dentaldoor.adapter;


import com.bw.dentaldoor.dao.StateRepository;
import com.bw.dentaldoor.entity.State;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.io.IOException;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class StateTypeAdapter extends TypeAdapter<State> {

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {

        @Override
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            return (State.class.isAssignableFrom(type.getRawType())
                    ? (TypeAdapter<T>) new StateTypeAdapter()
                    : null);
        }
    };

    @Inject
    private StateRepository locationDao;

    @Override
    public State read(JsonReader in) throws IOException {
        String value = in.nextString().trim();
        return StringUtils.isBlank(value) ? null : locationDao.findActiveByName(value).orElseThrow(() -> new IllegalArgumentException("State " + value + "cannot be found"));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void write(JsonWriter out, State value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.value(value.getName());
    }
}
