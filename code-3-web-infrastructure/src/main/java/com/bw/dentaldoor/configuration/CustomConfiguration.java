package com.bw.dentaldoor.configuration;

import com.bw.DataSourceUtil;
import com.bw.SettingService;
import com.bw.TimeUtil;
import com.bw.apiclient.ApiClient;
import com.bw.auth.*;
//import com.bw.dentaldoor.advice.AuditTrailAdvice;
//import com.bw.dentaldoor.constant.TimeFormatConstants;
//import com.bw.dentaldoor.entity.Invite;
//import com.bw.dentaldoor.interceptors.RequestPrincipalHandlerInterceptor;
import com.bw.hash.HashGenerator;
import com.bw.hash.HashGeneratorImpl;
//import com.bw.integration.mail.MailSenderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Configuration
@EnableJpaRepositories({"com.bw.dentaldoor.dao"})
@EnableAsync
public class CustomConfiguration {

    @Value("${BW_AUTH_API_BASE_URL:}")
    private String bwAuthApiBaseUrl;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public Gson gson() {
        return new GsonBuilder()
//                .setDateFormat(TimeFormatConstants.DEFAULT_DATE_TIME_FORMAT)
                .create();
    }
    @Bean
    public DataSourceUtil dataSourceUtil() {
        return new DataSourceUtil(gson());
    }

    @Bean
    public HashGenerator hashGenerator() {
        return new HashGeneratorImpl();
    }

    @Bean
    public OkHttpClient httpClient() {
        return new OkHttpClient();
    }

    @Bean
    public TimeUtil timeUtil() {
        return new TimeUtil();
    }
//
//    @Bean
//    public AuditTrailAdvice nodeResponseAdvice() {
//        return new AuditTrailAdvice();
//    }

//    @Bean
//    public FactoryBean<ApiResourcePortalUser> apiResourcePortalUser() {
//        return RequestPrincipalHandlerInterceptor.apiResourcePortalUser();
//    }

    @Bean
    public BwAuthApiClient bwAuthApiClient(DataSourceUtil dataSourceUtil, Gson gson) {
        return new BwAuthApiClientImpl(new ApiClient(new OkHttpClient()) {

            @Override
            public String getBaseUrl() {
                return bwAuthApiBaseUrl;
            }
        }, dataSourceUtil, gson);
    }

    @Bean
    public BwAuthAdmin bwAuthAdmin(BwAuthApiClient bwAuthApiClient, SettingService settingService) {
        return new BwAuthAdmin(bwAuthApiClient, settingService);
    }

    @Bean
    public BwAuthAdminApiClient bwAuthAdminApiClient(BwAuthAdmin bwAuthAdmin, DataSourceUtil dataSourceUtil, Gson gson) {
        return new BwAuthAdminApiClientImpl(new ApiClient(new OkHttpClient()) {

            @Override
            public String getBaseUrl() {

                logger.info("====> bwAuthApiBaseUrl = {}",bwAuthApiBaseUrl);
                return bwAuthApiBaseUrl;
            }
        }, bwAuthAdmin, dataSourceUtil, gson);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Logger getLogger(InjectionPoint injectionPoint) {
        Class<?> classOnWired = injectionPoint.getMember().getDeclaringClass();
        return LoggerFactory.getLogger(classOnWired);
    }

//    @Bean
//    public FactoryBean<MailGunApi> getMailGunApi() {
//        return new FactoryBean<MailGunApi>() {
//
//            @Override
//            public MailGunApi getObject() {
//                if (settingService == null) {
//                    settingService = applicationContext.getBean(SettingService.class);
//                }
//                String mailgunUrl = settingService.getString("MAIL_GUN_MESSAGES_URL", "");
//                if (!mailgunUrl.endsWith("/")) {
//                    mailgunUrl += "/";
//                }
//                String mailgunApiKey = settingService.getString("MAIL_GUN_MESSAGES_API_KEY", "");
//
//                String fromEmail = settingService.getString("EMAIL_SENDER_FROM_EMAIL", "noreply@irs.en.gov.ng");
//                String fromName = settingService.getString("EMAIL_SENDER_FROM_NAME", "Enugu IRS");
//
//                OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                        .addInterceptor(chain -> {
//                            Request.Builder newRequestBuilder = chain.request().newBuilder();
//                            newRequestBuilder.addHeader("Authorization", "Bearer " + Base64.getEncoder().encodeToString(("api:" + mailgunApiKey).getBytes()));
//                            newRequestBuilder.url(chain.request().url().newBuilder().addQueryParameter("from", String.format("%s <%s>", fromName, fromEmail)).build());
//                            return chain.proceed(newRequestBuilder.build());
//                        })
//                        .build();
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl(mailgunUrl)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .client(okHttpClient)
//                        .build();
//                return retrofit.create(MailGunApi.class);
//            }
//
//            @Override
//            public Class<?> getObjectType() {
//                return MailGunApi.class;
//            }
//
//            @Override
//            public boolean isSingleton() {
//                return false;
//            }
//        };
//    }
}
