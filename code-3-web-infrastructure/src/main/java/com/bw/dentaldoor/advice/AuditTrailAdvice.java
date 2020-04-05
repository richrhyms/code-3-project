/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bw.dentaldoor.advice;

import com.bw.dentaldoor.principal.RequestPrincipal;
import com.bw.entity.AuditTrail;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.proxy.HibernateProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.context.request.RequestContextHolder;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Aspect
public class AuditTrailAdvice implements Ordered {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Provider<RequestPrincipal> requestPrincipal;

    @Inject
    private Provider<HttpServletRequest> requestProvider;

    @Inject
    private ObjectMapper objectMapper;

    @Pointcut("execution(* javax.persistence.EntityManager.persist(Object))")
    public void onSave() {
    }

    @Pointcut("execution(* javax.persistence.EntityManager.merge(Object))")
    public void onUpdate() {
    }

    @Pointcut("execution(* javax.persistence.EntityManager.remove(Object))")
    public void onRemove() {
    }

    @Around("onSave()")
    public Object adviceEntityCreation(ProceedingJoinPoint jp) throws Throwable {
        Object result = jp.proceed(jp.getArgs());
        Object entity = jp.getArgs()[0];

        if (!(entity instanceof AuditTrail)) {
            AuditTrail auditTrail = prepareAuditTrail(entity);
            auditTrail.setAction("CREATE");
            auditTrail.setCurrentState(objectMapper.writeValueAsString(entity));
            entityManager.persist(auditTrail);
        }

        return result;
    }

    private AuditTrail prepareAuditTrail(Object entity) {
        AuditTrail auditTrail = new AuditTrail();
        auditTrail.setEntityName(entityManagerFactory.getMetamodel().entity(getType(entity)).getName());
        auditTrail.setRecordId(entityManagerFactory.getPersistenceUnitUtil().getIdentifier(entity).toString());
        if (RequestContextHolder.getRequestAttributes() != null && requestPrincipal.get() != null) {
            auditTrail.setActor(requestPrincipal.get().getUserId());
        }
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest httpServletRequest = requestProvider.get();
            if (httpServletRequest != null) {
                auditTrail.setRemoteAddress(StringUtils.defaultIfBlank(httpServletRequest.getHeader("X-FORWARDED-FOR"), httpServletRequest.getRemoteAddr()));
            }
        }
        auditTrail.setDateCreated(Timestamp.from(Instant.now()));
        return auditTrail;
    }

    @Around("onUpdate()")
    public Object adviceEntityUpdate(ProceedingJoinPoint jp) throws Throwable {
        Object entity = jp.getArgs()[0];
        if (!(entity instanceof AuditTrail)) {
            return jp.proceed(jp.getArgs());
        }
        Object reference = entityManager.find(getType(entity), entityManagerFactory.getPersistenceUnitUtil().getIdentifier(entity));
        String previousState = objectMapper.writeValueAsString(reference);

        Object result = jp.proceed(jp.getArgs());

        AuditTrail auditTrail = prepareAuditTrail(entity);
        auditTrail.setAction("UPDATE");
        auditTrail.setPreviousState(previousState);
        auditTrail.setCurrentState(objectMapper.writeValueAsString(entity));
        entityManager.persist(auditTrail);

        return result;
    }

    @Around("onRemove()")
    public Object adviceEntityDelete(ProceedingJoinPoint jp) throws Throwable {
        Object entity = jp.getArgs()[0];
        if (!(entity instanceof AuditTrail)) {
            return jp.proceed(jp.getArgs());
        }

        Object reference = entityManager.find(getType(entity), entityManagerFactory.getPersistenceUnitUtil().getIdentifier(entity));
        String previousState = objectMapper.writeValueAsString(reference);

        Object result = jp.proceed(jp.getArgs());

        AuditTrail auditTrail = prepareAuditTrail(entity);
        auditTrail.setAction("DELETE");
        auditTrail.setPreviousState(previousState);
        auditTrail.setCurrentState(objectMapper.writeValueAsString(entity));
        entityManager.persist(auditTrail);

        return result;
    }

    private Class<?> getType(Object e) {
        if (e instanceof HibernateProxy) {
            return ((HibernateProxy) e).getHibernateLazyInitializer().getPersistentClass();
        }
        return e.getClass();
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE + 1;
    }
}
