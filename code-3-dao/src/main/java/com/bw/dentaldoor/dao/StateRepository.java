package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public
interface StateRepository extends JpaRepository<State, Long>, QuerydslPredicateExecutor<State> {

    @Query("select s from State s where s.status='ACTIVE' and lower(s.name) = lower(?1) ")
    Optional<State> findActiveByName(String name);

    @Query("SELECT s from State s where s.status = 'ACTIVE' and lower(s.code) = lower(?1) ")
    Optional<State> findByCode(String code);
}
