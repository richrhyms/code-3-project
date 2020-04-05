package com.bw.dentaldoor.service;


import com.bw.SettingService;
import com.bw.dentaldoor.notification.SMSSenderImpl;
import com.bw.dentaldoor.notification.VansoSMSProvider;
import com.bw.integration.notification.NotificationSenderService;
import com.bw.integration.notification.SmsNotificationBuilder;
import com.bw.utils.sms.SMSSender;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import javax.inject.Named;

@Profile("!test")
@Named
public class NotificationSenderServiceImpl implements NotificationSenderService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String fromEmail;
    private final String fromName;

    public static final String NOTIFICATION_EMAIL_ADDRESS = "NOTIFICATION_EMAIL_ADDRESS";
    public static final String NOTIFICATION_EMAIL_PASSWORD = "NOTIFICATION_EMAIL_PASSWORD";
    public static final String NOTIFICATION_EMAIL_HOST = "NOTIFICATION_EMAIL_HOST";
    public static final String NOTIFICATION_EMAIL_PORT = "NOTIFICATION_EMAIL_PORT";

    public static final String NOTIFICATION_SENDER_EMAIL = "NOTIFICATION_SENDER_EMAIL";
    public static final String NOTIFICATION_SENDER_NAME = "NOTIFICATION_SENDER_NAME";

    private final SettingService settingService;
    private final Environment environment;

    private SMSSender smsSender;

    @Inject
    public NotificationSenderServiceImpl(SettingService settingService, Environment environment) {
        this.settingService = settingService;
        this.environment = environment;

        this.fromEmail = settingService.getString(NOTIFICATION_SENDER_EMAIL, "noreply@byteworks.com.ng");
        this.fromName = settingService.getString(NOTIFICATION_SENDER_NAME, "IMO IRS");
    }

    private SMSSender getSmsSender() {
        if (environment.getProperty("sms.username") == null
                && environment.getProperty("sms.password") == null
                && environment.getProperty("sms.url") == null) {
            throw new RuntimeException("Please provide values for sms.username, sms.password, sms.url");
        }

        if (environment.getProperty("sms.provider", "VANSO").equalsIgnoreCase("VANSO")) {
            return new VansoSMSProvider(environment.getProperty("sms.username"), environment.getProperty("sms.password"),
                    environment.getProperty("sms.url"));
        } else {
            return new SMSSenderImpl(
                    environment.getProperty("sms.url"),
                    environment.getProperty("sms.password"),
                    environment.getProperty("sms.username")
            );
        }
    }

    @Override
    public SmsNotificationBuilder sms(String s) {
        return new SmsNotificationBuilder() {

            String text = s;
            String[] recipients;
            String from;

            @Override
            public SmsNotificationBuilder text(String s) {
                text = s;
                return this;
            }

            @Override
            public SmsNotificationBuilder to(String... phoneNumbers) {
                recipients = new String[phoneNumbers.length];
                try {
                    for (int i = 0; i < recipients.length; i++) {
                        PhoneNumberUtil numberUtil = PhoneNumberUtil.getInstance();
                        Phonenumber.PhoneNumber number;
                        number = numberUtil.parse(phoneNumbers[i], "NG");
                        recipients[i] = numberUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.E164);
                    }
                } catch (NumberParseException e) {
                    e.printStackTrace();
                }
                return this;
            }

            @Override
            public SmsNotificationBuilder from(String s) {
                from = s;
                return this;
            }

            @Override
            public void send() {
                if (smsSender == null) {
                    smsSender = getSmsSender();
                }
                smsSender.sendSms(recipients, text, from);
            }
        };
    }

    public void send(Email email) {
        String smtpHost = settingService.getString(NOTIFICATION_EMAIL_HOST, "smtp.mailgun.org");
        Integer smtpPort = settingService.getInteger(NOTIFICATION_EMAIL_PORT, 25);
        String emailAddress = settingService.getString(NOTIFICATION_EMAIL_ADDRESS, "postmaster@mg.irs.en.gov.ng");
        String emailPassword = settingService.getString(NOTIFICATION_EMAIL_PASSWORD, "238d374d21a2984350d853caf8220c09");

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


}

