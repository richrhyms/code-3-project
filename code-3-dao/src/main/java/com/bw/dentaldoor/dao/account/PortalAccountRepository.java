package com.bw.dentaldoor.dao.account;

import com.bw.dentaldoor.entity.PortalAccount;
import com.bw.dentaldoor.enums.PortalAccountTypeConstant;
import com.bw.enums.GenericStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Repository
public interface PortalAccountRepository extends JpaRepository<PortalAccount,Long>, QuerydslPredicateExecutor<PortalAccount>{
    @Query("SELECT a FROM PortalAccount a WHERE a.name=:name AND a.type=:accountType AND a.status='ACTIVE'")
    Optional<PortalAccount> findByNameAndType(@Param("name") String name, @Param("accountType") PortalAccountTypeConstant accountType);

    Optional<PortalAccount> findFirstByTypeAndStatus(PortalAccountTypeConstant accountType, GenericStatusConstant status);

    List<PortalAccount> findByTypeAndStatus(PortalAccountTypeConstant accountType, GenericStatusConstant status);

    @Query("SELECT a FROM PortalAccount a WHERE a.code=:code AND a.status='ACTIVE'")
    Optional<PortalAccount> findActiveByCode(@Param("code") String code);

    @Query("SELECT a FROM PortalAccount a WHERE a.code=:code")
    Optional<PortalAccount> findByCode(@Param("code") String code);

    @Query("SELECT a FROM PortalAccount a WHERE a.code IN :code AND a.status='ACTIVE'")
    List<PortalAccount> findActiveByCode(@Param("code") Collection<String> code);

    @Query("SELECT a FROM PortalAccount a WHERE lower(a.name)=lower(:name) AND a.status='ACTIVE'")
    Optional<PortalAccount> findByName(@Param("name") String name);

}
