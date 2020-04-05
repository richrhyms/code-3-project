package com.bw.modelfactories;

import com.bw.dentaldoor.entity.TwoFactorRequest;
import com.bw.dentaldoor.enums.OtpDeliveryModeConstant;
import com.bw.dentaldoor.enums.OtpRequestStatusConstant;
import com.github.heywhy.springentityfactory.contracts.FactoryHelper;
import com.github.heywhy.springentityfactory.contracts.ModelFactory;
import com.github.javafaker.Faker;

import java.util.Date;

/**
 * @author Gibah Joseph
 * Email: gibahjoe@gmail.com
 * Oct, 2019
 **/
public class TwoFactorRequestFactory implements FactoryHelper<TwoFactorRequest> {

    @Override
    public Class<TwoFactorRequest> getEntity() {
        return TwoFactorRequest.class;
    }

    @Override
    public TwoFactorRequest apply(Faker faker, ModelFactory factory) {
        TwoFactorRequest twoFactorRequest = new TwoFactorRequest();
        twoFactorRequest.setPhoneNumber(faker.phoneNumber().phoneNumber());
        twoFactorRequest.setDeliveryMode(OtpDeliveryModeConstant.SMS);
        twoFactorRequest.setStatus(OtpRequestStatusConstant.PENDING);
        twoFactorRequest.setDateCreated(new Date());
        return twoFactorRequest;
    }
}
