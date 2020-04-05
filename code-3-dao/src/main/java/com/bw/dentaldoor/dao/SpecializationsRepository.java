package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface SpecializationsRepository extends JpaRepository<Specialization, Long> {
    @Query("SELECT s from Specialization s where lower(s.name)  = lower(?1)")
    Optional<Specialization> findByName(String name);
}
