package com.bw.dentaldoor.validator;

import com.bw.dentaldoor.constraint.ValidPhoneNumber;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Named;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Named
public class ValidPhoneNumberValidator implements ValidPhoneNumber.Validator {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isBlank(value)) {
            return true;
        }

        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber swissNumberProto = null;
        try {
            swissNumberProto = phoneUtil.parse(value.trim(), "US");
        } catch (NumberParseException e) {
            return false;
        }

        return phoneUtil.isValidNumber(swissNumberProto);
    }

}
