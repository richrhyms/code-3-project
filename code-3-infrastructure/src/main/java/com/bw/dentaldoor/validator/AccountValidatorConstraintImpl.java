package com.bw.dentaldoor.validator;
/**
 * Author: Oluwatobi Adenekan
 * email:  tadenekan@byteworks.com.ng
 * date:    18/12/2018
 **/

import com.bw.dentaldoor.constraint.AccountValidator;
import com.bw.dentaldoor.dao.account.PortalAccountRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintValidatorContext;

@Named
public class AccountValidatorConstraintImpl implements AccountValidator.AccountValidatorConstraint {

    @Inject
    private PortalAccountRepository portalAccountRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;

        }
        return portalAccountRepository.findActiveByCode(value.trim()).isPresent();
    }
}
