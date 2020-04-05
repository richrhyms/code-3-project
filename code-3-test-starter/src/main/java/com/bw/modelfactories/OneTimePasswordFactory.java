package com.bw.modelfactories;

import com.bw.dentaldoor.entity.OneTimePassword;
import com.bw.dentaldoor.entity.TwoFactorRequest;
import com.bw.dentaldoor.enums.OtpRequestTypeConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * @author Gibah Joseph
 * Email: gibahjoe@gmail.com
 * Oct, 2019
 **/
public class OneTimePasswordFactory implements FactoryHelper<OneTimePassword> {

    @Override
    public Class<OneTimePassword> getEntity() {
        return OneTimePassword.class;
    }

    @Override
    public OneTimePassword apply(Faker faker, ModelFactory factory) {
        OneTimePassword twoFactorRequest = new OneTimePassword();
        twoFactorRequest.setTwoFactorRequest(factory.create(TwoFactorRequest.class));
        twoFactorRequest.setPin(faker.random().hex());
        twoFactorRequest.setType(OtpRequestTypeConstant.USER_REGISTRATION);
        twoFactorRequest.setExpiryDate(DateUtils.addMinutes(new Date(), 1));
        twoFactorRequest.setDateCreated(new Date());
        return twoFactorRequest;
    }
}
