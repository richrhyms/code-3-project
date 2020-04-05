package com.bw.service;

import com.bw.SettingService;

import javax.annotation.PostConstruct;
import javax.inject.Named;

/**
 * @author Otaru RIchard <richotaru@gmail.com>
 */
@Named
public class PasswordServiceImpl implements PasswordService {

    private final SettingService settingService;
    private RandomPasswordGenerator passwordGenerator;

    @PostConstruct
    public void init() {
        passwordGenerator = new RandomPasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
    }

    public PasswordServiceImpl(SettingService settingService) {
        this.settingService = settingService;
    }

    @Override
    public String generatePassword() {
        return generatePassword(settingService.getInteger("DEFAULT_USER_PASSWORD_LENGTH", 8));
    }

    @Override
    public String generatePassword(int length) {
        return passwordGenerator.generate(length);
    }
}
