package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Repository
public interface InsuranceCompanyRepository extends JpaRepository<InsuranceCompany, Long> {
    @Query("SELECT i from InsuranceCompany i where lower(i.name)  = lower(?1)")
    Optional<InsuranceCompany> findByName(String  name);
    
    @Query("select i from InsuranceCompany  i where i.code in ?1 and i.status='ACTIVE'")
    List<InsuranceCompany> findActiveByCodes(List<String> codes);
}

