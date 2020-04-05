package com.bw.dentaldoor.service;

public interface PhoneNumberService {

    String formatPhoneNumber(String phoneNumber);

    boolean isValid(String value);
}
