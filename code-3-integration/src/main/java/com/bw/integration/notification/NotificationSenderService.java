package com.bw.integration.notification;

import org.apache.commons.mail.Email;


public interface NotificationSenderService {

    SmsNotificationBuilder sms(String message);

    void send(Email email);
}
