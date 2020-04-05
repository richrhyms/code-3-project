/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bw.dentaldoor.adapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.hibernate.proxy.HibernateProxy;

import java.io.IOException;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class HibernateProxyTypeAdapter extends TypeAdapter<HibernateProxy> {

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @Override
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            return (HibernateProxy.class.isAssignableFrom(type.getRawType())
                    ? (TypeAdapter<T>) new HibernateProxyTypeAdapter()
                    : null);
        }
    };

    private HibernateProxyTypeAdapter() {
    }

    @Override
    public HibernateProxy read(JsonReader in) {
        throw new UnsupportedOperationException("Not supported");
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void write(JsonWriter out, HibernateProxy value) throws IOException {
        out.beginObject()
                .name("id")
                .value((Long) value.getHibernateLazyInitializer().getIdentifier())
                .endObject();
    }
}
