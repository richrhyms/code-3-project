package com.bw.dentaldoor.test;

import com.bw.DataSourceUtil;
import com.bw.TimeUtil;
import com.bw.conf.ServiceLayerConfiguration;
import com.bw.dentaldoor.etc.PhoneNumberServiceImpl;
import com.bw.dentaldoor.principal.RequestPrincipal;
import com.bw.dentaldoor.service.PhoneNumberService;
import com.bw.hash.HashGenerator;
import com.bw.hash.HashGeneratorImpl;
import com.bw.integration.TemplateEngine;
import com.bw.integration.notification.NotificationSenderService;
import com.github.heywhy.springentityfactory.FactoryConfiguration;
import com.google.gson.Gson;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
@Import({ServiceLayerConfiguration.class, FactoryConfiguration.class})
public class IntegrationTestApplication {

    @Bean
    public TimeUtil timeUtil() {
        return new TimeUtil();
    }

    @Bean
    public DataSourceUtil dataSourceUtil(Gson gson) {
        return new DataSourceUtil(gson);
    }

    @Bean
    public RequestPrincipal requestPrincipal() {
        return Mockito.mock(RequestPrincipal.class);
    }
//    @Bean
//    public MailService mailService() {
//        return Mockito.mock(MailService.class);
//    }

    @Bean
    public TemplateEngine templateEngine() {
        return Mockito.mock(TemplateEngine.class);
    }

    @Bean
    public HashGenerator hashGenerator() {
        return new HashGeneratorImpl();
    }

    @Bean
    public PhoneNumberService phoneNumberService() {
        return new PhoneNumberServiceImpl();
    }

    @Bean
    public NotificationSenderService notificationSenderService() {
        return Mockito.mock(NotificationSenderService.class);
    }

//    @Bean
//    public SmsNotificationBuilder smsNotificationBuilder() {
//        return Mockito.mock(SmsNotificationBuilder.class);
//    }

    @Bean
    @Scope("prototype")
    public Logger getLogger(InjectionPoint injectionPoint) {
        Class<?> classOnWired = injectionPoint.getMember().getDeclaringClass();
        return LoggerFactory.getLogger(classOnWired);
    }

}
