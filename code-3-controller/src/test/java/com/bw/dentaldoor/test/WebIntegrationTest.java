package com.bw.dentaldoor.test;

import com.bw.auth.ApiResourcePortalUser;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@AutoConfigureMockMvc
@Import({WebIntegrationTest.$Configuration.class})
@TestProfile
public class WebIntegrationTest extends IntegrationTest {

    @Autowired
    protected MockMvc mockMvc;


    @Inject
    protected ObjectMapper objectMapper;

    @Configuration
    @ComponentScan({
            "com.bw.util",
            "com.bw.security",
            "com.bw.excel",
            "com.bw.dentaldoor.dao",
            "com.bw.dentaldoor.controller",
            "com.bw.dentaldoor.controlleradvice",
            "com.bw.dentaldoor.search.handler",
            "com.bw.dentaldoor.response.handler"
    })
    public static class $Configuration implements WebMvcConfigurer {
        @Inject
        private ApplicationContext applicationContext;

        final Logger logger = LoggerFactory.getLogger(this.getClass());

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new HandlerInterceptorAdapter() {
                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                    logger.info(Thread.currentThread().getName());
                    return super.preHandle(request, response, handler);
                }
            });
//            registry.addInterceptor(applicationContext.getAutowireCapableBeanFactory().createBean(AccessConstraintHandlerInterceptor.class));
        }

        @Override
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            converters.add(customJackson2HttpMessageConverter());
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
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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

        @Bean
        public ApiResourcePortalUser apiResourcePortalUser() {
            return Mockito.mock(ApiResourcePortalUser.class);
        }
    }
}
