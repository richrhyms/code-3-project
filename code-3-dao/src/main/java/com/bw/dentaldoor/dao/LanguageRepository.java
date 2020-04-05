package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    @Query("select l from Language l where l.status='ACTIVE' and l.code in ?1")
    List<Language> findActiveByCodes(Iterable<String> codes);

    @Query("SELECT l from Language l where lower(l.name) = lower(?1)")
    Optional<Language> findByName(String name);

    @Query("SELECT l from Language l where lower(l.name) = lower(?1) and lower(l.code) = lower(?2) ")
    Optional<Language> findByNameAndCode(String name, String code);

}
