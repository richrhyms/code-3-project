package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.InsuranceCompany;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.entity.PortalUserInsuranceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HealthInsuranceProviderRepository extends JpaRepository<PortalUserInsuranceProvider, Long> {

    @Query("SELECT dps from PortalUserInsuranceProvider dps where dps.status = 'ACTIVE' and dps.portalUser = ?1")
    List<PortalUserInsuranceProvider> findActiveByPortalUser(PortalUser dentalProfessional);

    @Query("SELECT dps from PortalUserInsuranceProvider dps JOIN FETCH dps.insuranceCompany where dps.status = 'ACTIVE' and dps.portalUser IN ?1")
    List<PortalUserInsuranceProvider> findActiveByPortalUsers(List<PortalUser> dentalProfessional);

    @Query("SELECT DISTINCT r.insuranceCompany" +
            " FROM PortalUserInsuranceProvider r" +
            " WHERE r.portalUser = ?1" +
            " AND r.status='ACTIVE'" +
            " AND r.insuranceCompany.status = 'ACTIVE'")
    List<InsuranceCompany> getByPortalUser(PortalUser portalUser);
}
