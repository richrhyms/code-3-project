package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.City;
import com.bw.dentaldoor.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public
interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c from City c where c.code = ?1 and c.status ='ACTIVE'")
    Optional<City> findActiveByCode(String code);

    @Query("select c from City c where c.state = ?1 and c.status ='ACTIVE'")
    List<City> findActiveByState(State state);
}
