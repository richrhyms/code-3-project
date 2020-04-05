package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Emmanuel Evuazeze <eevuazeze@byteworks.com.ng>
 */
@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    @Query("SELECT s from Specialization s where s.status = 'ACTIVE' and lower(s.code) = lower(?1)")
    Optional<Specialization> findByCode(String code);

    @Query("SELECT s from Specialization s where s.status = 'ACTIVE' and s.code in ?1")
    List<Specialization> findByCodes(List<String> codes);
}
