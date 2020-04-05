package com.bw.dentaldoor.resolvers;

import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.principal.RequestPrincipal;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.inject.Provider;
import java.util.Optional;

/**
 * @author Gibah Joseph
 * Email: gibahjoe@gmail.com
 * Oct, 2019
 **/
public class PortalUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private Provider<RequestPrincipal> requestPrincipalProvider;
    @Autowired
    private Logger logger;

    public PortalUserArgumentResolver(ApplicationContext applicationContext) {
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.isOptional()) {
            return parameter.getNestedParameterType().equals(PortalUser.class);
        }
        return parameter.getParameterType().equals(PortalUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        RequestPrincipal requestPrincipal = requestPrincipalProvider.get();
        if (parameter.isOptional() || parameter.getParameterType().equals(Optional.class)) {
            return Optional.ofNullable(requestPrincipal.getPortalUser());
        }
        return requestPrincipal.getPortalUser();
    }
}
