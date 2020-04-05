package com.bw.integration.mail;

import com.bw.integration.mail.MailGunEmail;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;

import java.util.Map;

public interface MailService {
    void send(Email email);

    String getMessageFromTemplate(String template, Map<String, Object> bindings);

    String getSender();

    void sendViaMailgunApi(MailGunEmail mailGunEmail);
}
