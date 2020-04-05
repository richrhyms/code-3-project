package com.bw.integration.captcha;

public interface CaptchaApiClient {
    GoogleCaptchaResponse validateCaptcha(String captcha);

    boolean isCaptchaValid(String captcha);
}
