package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query("select c from Country c where lower(c.name) = lower(?1) ")
    Optional<Country> findByName(String name);
}
