package com.bw.dentaldoor.integration;


import com.bw.SettingService;
import com.bw.dentaldoor.etc.Constant;
import com.bw.integration.mail.MailService;
import com.bw.integration.TemplateEngine;
import com.bw.integration.mail.MailGunEmail;
import com.bw.integration.retrofit.MailGunApi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Named
public class MailServiceImpl implements MailService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Inject
    private SettingService settingDao;

    @Inject
    private TemplateEngine templateEngine;

    @Inject
    private SettingService settingService;
    @Value("${EXTERNAL_EMAIL_SENDER_URL:}")
    private String externalMailSenderUrl;

    @Async
    public void send(Email email) {
        String emailAddress = settingDao.getString(Constant.NOTIFICATION_EMAIL_ADDRESS, "postmaster@mg.irs.en.gov.ng");
        String emailPassword = settingDao.getString(Constant.NOTIFICATION_EMAIL_PASSWORD, "238d374d21a2984350d853caf8220c09");
        Integer smtpPort = settingDao.getInteger("SMTP_PORT", 465);
        String smtpHost = settingDao.getString("SMTP_HOST", "smtp.mailgun.org");
        String fromEmail = settingDao.getString("EMAIL_SENDER_FROM_EMAIL", "noreply@dentaldoor.ng");
        String fromName = settingDao.getString("EMAIL_SENDER_FROM_NAME", "DentalDoor");

        try {
            email.setHostName(smtpHost);
            email.setSmtpPort(smtpPort);
            email.setAuthenticator(new DefaultAuthenticator(emailAddress, emailPassword));

            email.setFrom(fromEmail, fromName);

            email.setSSLOnConnect(true);
            email.setStartTLSEnabled(true);
            email.send();
        } catch (EmailException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getMessageFromTemplate(String template, Map<String, Object> bindings) {
        return templateEngine.getAsString(template, bindings);
    }

    @Override
    public String getSender() {
        return settingService.getString(Constant.SENDER_NAME)
                .orElse("Byteworks");
    }



    @Override
    public void sendViaMailgunApi(MailGunEmail mailGunEmail) {
        try {
            Response<ResponseBody> response = getMailgunApi().sendMail(mailGunEmail.getRecipientEmails(), mailGunEmail.getHtmlMessage(), mailGunEmail.getSubject()).execute();
            if (response.isSuccessful()) {
                logger.info("===> mail sent to :: {}", String.join(", ", mailGunEmail.getRecipientEmails()));
            } else {
                logger.info("===> Mail sending to {} failed with code {} and message {} ", String.join(", ", mailGunEmail.getRecipientEmails()), response.code(), response.errorBody() != null ? response.errorBody().string() : "null");
            }
        } catch (IOException e) {
            logger.info("===> Mail sending to {} failed with message {} ", String.join(", ", mailGunEmail.getRecipientEmails()), e.getMessage());
        }
    }


    private MailGunApi getMailgunApi() {
        String mailgunUrl = settingService.getString("MAIL_GUN_MESSAGES_URL", "https://ddstaging.byteproducts.com/");
        if (!mailgunUrl.endsWith("/")) {
            mailgunUrl += "/";
        }
        String mailgunApiKey = settingService.getString("MAIL_GUN_MESSAGES_API_KEY", "eyJhbGciOiJIUzI1NiJ9");

        String fromEmail = settingService.getString("EMAIL_SENDER_FROM_EMAIL", "noreply@dentaldoor.com");
        String fromName = settingService.getString("EMAIL_SENDER_FROM_NAME", "DentalDoor Team");

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request.Builder newRequestBuilder = chain.request().newBuilder();
                    newRequestBuilder.addHeader("Authorization", "Bearer " + Base64.getEncoder().encodeToString(("api:" + mailgunApiKey).getBytes()));
                    newRequestBuilder.url(chain.request().url().newBuilder().addQueryParameter("from", String.format("%s <%s>", fromName, fromEmail)).build());
                    return chain.proceed(newRequestBuilder.build());
                })
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mailgunUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(MailGunApi.class);
    }



}
