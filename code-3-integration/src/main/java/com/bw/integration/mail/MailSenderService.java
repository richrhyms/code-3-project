package com.bw.integration.mail;


import com.bw.dentaldoor.entity.Invite;
import org.apache.commons.mail.EmailException;

import java.lang.reflect.InvocationHandler;
import java.util.Map;

public interface MailSenderService {

    void sendUserInvite(Invite user) throws Exception;
    void sendDentalPractitionerInvite(Invite user) throws Exception;
    void sendDentalOfficerInvite(Invite user) throws Exception;
    void sendMail(Invite user, String template, Map<String, Object> bindings, String subject) throws Exception;

}
