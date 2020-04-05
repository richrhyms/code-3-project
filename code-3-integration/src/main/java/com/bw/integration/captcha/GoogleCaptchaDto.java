package com.bw.integration.captcha;

public class GoogleCaptchaDto {
    private String secret;
    private String response;

    public GoogleCaptchaDto(String secret, String response) {
        this.secret = secret;
        this.response = response;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
