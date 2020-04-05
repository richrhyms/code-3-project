package com.bw.dentaldoor.dao;

import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.entity.PortalUserImage;
import com.bw.enums.GenericStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jabir Minjibir <jminjibir@byteworks.com.ng>
 */

@Repository
public interface PortalUserImageRepository extends JpaRepository<PortalUserImage, Long> {

    List<PortalUserImage> findByPortalUserAndStatus(PortalUser portalUser, GenericStatusConstant statusConstant);

    @Query("select pi from PortalUserImage pi where pi.portalUser in ?1 and pi.status='ACTIVE'")
    List<PortalUserImage> findActiveByPortalUsers(List<PortalUser> portalUser);

    Optional<PortalUserImage> findFirstByPortalUserAndStatus(PortalUser portalUser, GenericStatusConstant statusConstant);

    List<PortalUserImage> findByPortalUser(PortalUser user);
}
