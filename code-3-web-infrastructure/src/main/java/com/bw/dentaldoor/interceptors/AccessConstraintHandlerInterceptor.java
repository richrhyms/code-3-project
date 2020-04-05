/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bw.dentaldoor.interceptors;

import com.bw.dentaldoor.principal.RequestPrincipal;
import com.bw.security.AccessConstraint;
import com.bw.security.AccessStatus;
import com.bw.security.AccessStatusSource;
import com.bw.security.Public;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.OpenApiResource;
import org.springdoc.ui.SwaggerWelcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AccessConstraintHandlerInterceptor extends HandlerInterceptorAdapter {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ApplicationContext applicationContext;
    @Autowired
    private Provider<RequestPrincipal> requestPrincipalProvider;
    public AccessConstraintHandlerInterceptor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        try {
            List<Annotation> accessConstraints = collectAccessConstraints(handlerMethod.getMethod().getDeclaringClass().getAnnotations());
            accessConstraints.addAll(collectAccessConstraints(handlerMethod.getMethod().getDeclaredAnnotations()));
            if (requestPrincipalProvider.get() == null) {
                if (accessConstraints.isEmpty() && (
                        handlerMethod.hasMethodAnnotation(Public.class)
                                || handlerMethod.getMethod().getDeclaringClass().isAnnotationPresent(Public.class)
                                || (BasicErrorController.class.isAssignableFrom(handlerMethod.getBeanType()))
                                || (OpenApiResource.class.isAssignableFrom(handlerMethod.getBeanType()))
                                || (SwaggerWelcome.class.isAssignableFrom(handlerMethod.getBeanType()))
                )) {
                    return true;
                }
                response.setStatus(401);
                response.getWriter().append("Unauthorized");
                return false;
            }
            for (Annotation annotation : accessConstraints) {
                AccessStatus accessStatus = getAccessStatus(annotation);
                if (!accessStatus.hasAccess()) {
                    response.setStatus(403);
                    response.getWriter().append(accessStatus.reason());
                    return false;
                }
            }
            return true;
        } catch (IllegalStateException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }
    private List<Annotation> collectAccessConstraints(Annotation[] stream) {
        return Arrays.asList(stream).stream()
                .filter(annotation -> annotation.annotationType().isAnnotationPresent(AccessConstraint.class))
                .collect(Collectors.toList());
    }
    private <A extends Annotation> AccessStatus getAccessStatus(A annotation) {
        Class<? extends AccessStatusSource<A>> aClass = (Class<AccessStatusSource<A>>) annotation.annotationType().getAnnotation(AccessConstraint.class).value();
        AccessStatusSource<A> accessStatusSource = applicationContext.getBean(aClass);
        return accessStatusSource.getStatus(annotation);
    }
}
