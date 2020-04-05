package com.bw.dentaldoor.etc;

import com.bw.dentaldoor.service.PhoneNumberService;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Named;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Named
public class PhoneNumberServiceImpl implements PhoneNumberService {

    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    public String formatPhoneNumber(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber)) {
            return null;
        }
        try {
            Phonenumber.PhoneNumber number = phoneNumberUtil.parse(phoneNumber, "US");
            return phoneNumberUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.E164);
        } catch (NumberParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isValid(String value) {

        if (value == null) {
            return true;
        }

        Phonenumber.PhoneNumber swissNumberProto;
        try {
            swissNumberProto = phoneNumberUtil.parse(value.trim(), "US");
        } catch (NumberParseException e) {
            return false;
        }

        return phoneNumberUtil.isValidNumber(swissNumberProto);
    }
}
