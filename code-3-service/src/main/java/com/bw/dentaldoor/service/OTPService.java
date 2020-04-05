package com.bw.dentaldoor.service;


import com.bw.dentaldoor.entity.OneTimePassword;
import com.bw.dentaldoor.enums.OtpDeliveryModeConstant;
import com.bw.dentaldoor.enums.OtpRequestTypeConstant;

public interface OTPService {

    OneTimePassword requestPhoneOtp(String phoneNumber, OtpRequestTypeConstant type, OtpDeliveryModeConstant mode);

    boolean confirmPhoneOtp(String phoneNumber, OtpRequestTypeConstant type, String pin) throws IllegalArgumentException;
}
