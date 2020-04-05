package com.bw.integration.notification;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public interface SmsNotificationBuilder {

    SmsNotificationBuilder text(String senderName);

    SmsNotificationBuilder to(String... phoneNumbers);

    SmsNotificationBuilder from(String senderName);

    void send();
}
