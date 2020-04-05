package com.bw.dentaldoor.test;

import com.bw.auth.BwAuthAdminApiClient;
//import com.bw.dentaldoor.PortalUserRoleDto;
//import com.bw.dentaldoor.domain.pojo.AccountMembershipPojo;
import com.bw.dentaldoor.entity.*;
import com.bw.dentaldoor.enums.PermissionTypeConstant;
import com.bw.dentaldoor.enums.PortalAccountTypeConstant;
//import com.bw.dentaldoor.principal.ActivityLogBuilder;
import com.bw.dentaldoor.principal.RequestPrincipal;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(classes = IntegrationTestApplication.class)
@Import(IntegrationTest.$Configuration.class)
@Transactional
public abstract class IntegrationTest {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    protected RequestPrincipal requestPrincipal;
    @Autowired
    @Qualifier("entityFactory")
    protected ModelFactory modelFactory;
    @Autowired
    @Qualifier("pojoFactory")
    protected ModelFactory pojoFactory;

    @Autowired
    protected Faker faker;
    @Autowired
    protected Gson gson;

    @Autowired
    protected ApplicationContext applicationContext;

    @BeforeEach
    public void before() {
//        ModelFactoryRegistrar.register(modelFactory);
//        Mockito.when(smsNotificationBuilder.from(Mockito.anyString()))
//                .thenReturn(smsNotificationBuilder);
//        Mockito.when(smsNotificationBuilder.to(Mockito.anyString()))
//                .thenReturn(smsNotificationBuilder);
//        Mockito.when(notificationSenderService.sms(Mockito.anyString()))
//                .then(invocation -> smsNotificationBuilder);
    }

    @AfterEach
    public void after() {
        entityManager.flush();
        Mockito.reset(requestPrincipal);
//        Mockito.reset(notificationSenderService);
//        Mockito.reset(smsNotificationBuilder);
    }

    protected <T> T jsonToClass(String jsonString, Class<T> tClass) {
        return gson.fromJson(jsonString, tClass);
    }

    protected PortalUser loggedInUser(PortalUser workspaceUser) {
        workspaceUser = workspaceUser != null ? workspaceUser : modelFactory.create(PortalUser.class);
        Mockito.when(requestPrincipal.getPortalUser())
                .thenReturn(workspaceUser);

//        ActivityLogBuilder activityLogBuilder = Mockito.mock(ActivityLogBuilder.class);
//
//        Mockito.when(activityLogBuilder.setEntityId(Mockito.any()))
//                .thenReturn(activityLogBuilder);
        return workspaceUser;
    }



    protected PortalUser loggedInUser() {
        return loggedInUser(null);
    }

    @Configuration
    @ComponentScan({
            "com.bw.dentaldoor.dao",
            "com.bw.dentaldoor.validator",
            "com.bw.service",
            "com.bw.dentaldoor.service",
            "com.bw.dentaldoor.sequence"
//            "com.bw.dentaldoor.integration"
    })
    @EnableJpaRepositories({"com.bw.dentaldoor.dao"})
    public static class $Configuration implements WebMvcConfigurer {

        @Bean
        public BwAuthAdminApiClient bwAuthAdminApiClient() {
            return Mockito.mock(BwAuthAdminApiClient.class);
        }
//        @Bean
//        public MailService mailService() {
//            return Mockito.mock(MailService.class);
//        }

//        @Bean(name = "pojoFactory")
//        public ModelFactory getPojoFactory() {
//            return new PojoFactoryImpl(new Faker());
//        }
    }
}
