package com.bw.dentaldoor;

import com.bw.conf.ServiceLayerConfiguration;
import com.bw.dentaldoor.configuration.CustomConfiguration;
import com.bw.dentaldoor.configuration.WebConfiguration;
//import com.bw.dentaldoor.startup.PortalAccountSetup;
import com.bw.dentaldoor.startup.PortalAccountSetup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {
        HibernateJpaAutoConfiguration.class,
        ValidationAutoConfiguration.class
})
@EnableScheduling
@Import({ServiceLayerConfiguration.class, WebConfiguration.class, CustomConfiguration.class})

public class Code3Application {

    public static void main(String[] args) {
        SpringApplication.run(Code3Application.class, args);
    }

    @Bean
    public PortalAccountSetup portalAccountSetup(ApplicationContext applicationContext) {
        return applicationContext.getAutowireCapableBeanFactory().createBean(PortalAccountSetup.class);
    }

}
