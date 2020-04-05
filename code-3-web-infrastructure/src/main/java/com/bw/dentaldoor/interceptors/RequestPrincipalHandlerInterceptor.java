/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bw.dentaldoor.interceptors;

import com.bw.apiclient.ApiCallException;
import com.bw.auth.ApiResourcePortalUser;
import com.bw.auth.BwAuthAdminApiClient;
import com.bw.dentaldoor.principal.RequestPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class RequestPrincipalHandlerInterceptor extends HandlerInterceptorAdapter {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ApplicationContext applicationContext;

    private BwAuthAdminApiClient bwAuthAdminApiClient;

    public RequestPrincipalHandlerInterceptor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static FactoryBean<RequestPrincipal> requestPrincipal() {
        return new FactoryBean<RequestPrincipal>() {

            @Override
            public RequestPrincipal getObject() {
                if (RequestContextHolder.getRequestAttributes() == null) {
                    return null;
                }
                return (RequestPrincipal) RequestContextHolder.currentRequestAttributes().getAttribute(RequestPrincipal.class.getName(),
                        RequestAttributes.SCOPE_REQUEST);
            }

            @Override
            public Class<?> getObjectType() {
                return RequestPrincipal.class;
            }

            @Override
            public boolean isSingleton() {
                return false;
            }
        };
    }

    public static FactoryBean<ApiResourcePortalUser> apiResourcePortalUser() {
        return new FactoryBean<ApiResourcePortalUser>() {

            @Override
            public ApiResourcePortalUser getObject() {
                return (ApiResourcePortalUser) RequestContextHolder.currentRequestAttributes().getAttribute(ApiResourcePortalUser.class.getName(),
                        RequestAttributes.SCOPE_REQUEST);
            }

            @Override
            public Class<?> getObjectType() {
                return ApiResourcePortalUser.class;
            }

            @Override
            public boolean isSingleton() {
                return false;
            }
        };
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        logger.info("request.getServletPath(): {}",request.getServletPath());
        if (bwAuthAdminApiClient == null) {
            bwAuthAdminApiClient = applicationContext.getBean(BwAuthAdminApiClient.class);
        }
        try {
            String authHeader = StringUtils.defaultString(request.getHeader(HttpHeaders.AUTHORIZATION), "");
            if (StringUtils.isNotBlank(authHeader)) {
                ApiResourcePortalUser user = bwAuthAdminApiClient.getUserWithAuthorizationHeader(request.getHeader(HttpHeaders.AUTHORIZATION));
                RequestAttributes currentRequestAttributes = RequestContextHolder.currentRequestAttributes();

                String ipAddress = request.getRemoteAddr();
                if (request.getRemoteAddr().equals("127.0.0.1") || request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")) {
                    ipAddress = StringUtils.defaultIfBlank(request.getHeader("X-FORWARDED-FOR"),
                            request.getRemoteAddr());
                }

                RequestPrincipalImpl requestPrincipal = new RequestPrincipalImpl(user.getUserId(), ipAddress) {
                    @Override
                    public String getUserName() {
                        return user.getUsername();
                    }
                };
                applicationContext.getAutowireCapableBeanFactory().autowireBean(requestPrincipal);
                currentRequestAttributes.setAttribute(ApiResourcePortalUser.class.getName(),
                        user,
                        RequestAttributes.SCOPE_REQUEST);
                currentRequestAttributes.setAttribute(RequestPrincipal.class.getName(),
                        requestPrincipal,
                        RequestAttributes.SCOPE_REQUEST);
            }

            try {
                Cookie cookie = new Cookie(RequestPrincipal.AUTH_TOKEN_NAME, authHeader.replace("Bearer ", ""));
                cookie.setMaxAge(60 * 30);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                response.addCookie(cookie);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } catch (ApiCallException e) {
            if (e.getCode() != 401) {
                logger.error(e.getMessage(), e);
            }
        }
        return true;
    }
}
