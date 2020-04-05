/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bw.dentaldoor.configuration;

//import com.bw.dentaldoor.constant.TimeFormatConstants;
//import com.bw.dentaldoor.converter.DateConverter;
//import com.bw.dentaldoor.converter.LocalDateConverter;
//import com.bw.dentaldoor.interceptors.AccessConstraintHandlerInterceptor;
//import com.bw.dentaldoor.interceptors.RemoteAddressConstraintHandlerInterceptor;
//import com.bw.dentaldoor.interceptors.RequestPrincipalHandlerInterceptor;
import com.bw.dentaldoor.principal.RequestPrincipal;
//import com.bw.dentaldoor.resolvers.PortalUserArgumentResolver;
import com.bw.entity.BwFile;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Configuration
@ComponentScan({
        "com.bw.security",
        "com.bw.service",
        "com.bw.integration",
//        "com.bw.util",
//        "com.bw.excel",
        "com.bw.dentaldoor.etc",
        "com.bw.dentaldoor.dao",
        "com.bw.dentaldoor.validator",
        "com.bw.dentaldoor.service",
        "com.bw.dentaldoor.integration"
})
public class WebConfiguration implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    private final ConstraintValidatorFactory constraintValidatorFactory;

    public WebConfiguration(ApplicationContext applicationContext, ConstraintValidatorFactory constraintValidatorFactory) {
        this.applicationContext = applicationContext;
        this.constraintValidatorFactory = constraintValidatorFactory;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new RequestPrincipalHandlerInterceptor(applicationContext));
//        registry.addInterceptor(new AccessConstraintHandlerInterceptor(applicationContext));
//        registry.addInterceptor(new RemoteAddressConstraintHandlerInterceptor(applicationContext));
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(new PortalUserArgumentResolver(applicationContext));
//        argumentResolvers.add(getApplicationContext().getAutowireCapableBeanFactory().createBean(PathVariableResolver.class));
//        argumentResolvers.add(getApplicationContext().getAutowireCapableBeanFactory().createBean(EntityArgumentResolver.class));
//        argumentResolvers.add(getApplicationContext().getAutowireCapableBeanFactory().createBean(RequestParamPayloadResolver.class));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new LocalDateConverter("yyyy-MM-dd"));
//        registry.addConverter(new DateConverter("yyyy-MM-dd"));
//        registry.addConverter(new LocalDateTimeConverter("yyyy-MM-dd'T'HH:mm:ss.SSS"));
    }

    @Bean
    @Override
    public LocalValidatorFactoryBean getValidator() {
        final LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean
                .setConstraintValidatorFactory(constraintValidatorFactory);
        return localValidatorFactoryBean;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(ValidatorFactory validatorFactory) {
        final MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidatorFactory(validatorFactory);
        return methodValidationPostProcessor;
    }

    @Bean
    public FactoryBean<RequestPrincipal> requestPrincipal() {
//        return RequestPrincipalHandlerInterceptor.requestPrincipal();
        return null;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(new StdSerializer<JavassistLazyInitializer>(JavassistLazyInitializer.class) {

            @Override
            public void serialize(JavassistLazyInitializer value, JsonGenerator gen, SerializerProvider provider) throws IOException {
                gen.writeNull();
            }
        });
        simpleModule.addSerializer(new StdSerializer<HibernateProxy>(HibernateProxy.class) {

            @Override
            public void serialize(HibernateProxy value, JsonGenerator gen, SerializerProvider provider) throws IOException {
                gen.writeObject(Collections.singletonMap("id", value.getHibernateLazyInitializer().getIdentifier()));
//                gen.writeNull();
            }
        });
        simpleModule.addSerializer(new StdSerializer<BwFile>(BwFile.class) {
            @Override
            public void serialize(BwFile value, JsonGenerator gen, SerializerProvider provider) throws IOException {
                if (value == null) {
                    gen.writeNull();
                    return;
                }
                Map<String, Object> map = new HashMap<>();
                for (Field field : BwFile.class.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (byte[].class.isAssignableFrom(field.getType())) {
                        continue;
                    }
                    try {
                        Object fieldValue = field.get(value);
                        map.put(field.getName(), fieldValue);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                gen.writeObject(map);
            }
        });
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.setDateFormat(new SimpleDateFormat(TimeFormatConstants.DEFAULT_DATE_TIME_FORMAT));
        objectMapper.registerModule(simpleModule);

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new Jdk8Module());

        return objectMapper;
    }

    @Bean
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(objectMapper());
        return jsonConverter;
    }

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}
