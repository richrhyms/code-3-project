package com.bw.dentaldoor.dao;

public interface QueryResultTransformer<E, T> {

    T transaform(E e);
}
