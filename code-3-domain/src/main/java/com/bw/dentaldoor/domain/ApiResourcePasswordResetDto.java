package com.bw.dentaldoor.domain;

public class ApiResourcePasswordResetDto {
    private String email;
    private String redirectUrl;
    private boolean sendEmail = true;

    public String getEmail() {
        return email;
    }

    public boolean isSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
