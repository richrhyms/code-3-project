/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bw.dentaldoor.interceptors;

import com.bw.security.AccessStatus;
import com.bw.security.Localhost;
import com.bw.security.TrustedIpAddress;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class RemoteAddressConstraintHandlerInterceptor extends HandlerInterceptorAdapter {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TrustedIpAddressAccessManager trustedIpAddressAccessManager;

    public RemoteAddressConstraintHandlerInterceptor(ApplicationContext applicationContext) {
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String ipAddress = StringUtils.defaultIfBlank(
                request.getHeader("X-FORWARDED-FOR"),
                request.getRemoteAddr());
        try {
            if (handlerMethod.getMethod().getDeclaringClass().getAnnotationsByType(Localhost.class).length > 0
                    || handlerMethod.getMethod().getAnnotationsByType(Localhost.class).length > 0) {
                AccessStatus accessStatus = ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")
                        ? AccessStatus.allowed()
                        : AccessStatus.denied(""); //"Request not from Localhost: "+ipAddress
                if (!accessStatus.hasAccess()) {
                    logger.warn("Non local IP \"{}\" denied access to {}", ipAddress, request.getServletPath());
                    response.setStatus(403);
                    response.getWriter().append(accessStatus.reason());
                    return false;
                }
            } else {
                List<TrustedIpAddress> accessConstraints = new ArrayList<>(Arrays.asList(handlerMethod.getMethod()
                        .getDeclaringClass().getAnnotationsByType(TrustedIpAddress.class)));
                accessConstraints.addAll(Arrays.asList(handlerMethod.getMethod().getAnnotationsByType(TrustedIpAddress.class)));

                for (TrustedIpAddress annotation : accessConstraints) {
                    AccessStatus accessStatus = trustedIpAddressAccessManager.getStatus(annotation);
                    if (!accessStatus.hasAccess()) {
                        logger.warn("{}: Untrusted IP \"{}\" denied access to {}", annotation.value(),
                                ipAddress, request.getServletPath());
                        response.setStatus(403);
                        response.getWriter().append(accessStatus.reason());
                        return false;
                    }
                }
            }
        } catch (IllegalStateException e) {
            logger.error(e.getMessage(), e);
        }

        return true;
    }
}
