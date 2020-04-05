package com.bw.dentaldoor.domain.account;


import com.bw.dentaldoor.enums.PortalAccountTypeConstant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class PortalAccountDto {

    @NotBlank
    private String name;

    @NotNull
    private PortalAccountTypeConstant type;

    private String email;

    private String phoneNumber;

    private String altPhoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PortalAccountTypeConstant getType() {
        return type;
    }

    public void setType(PortalAccountTypeConstant type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PortalAccountDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getAltPhoneNumber() {
        return altPhoneNumber;
    }

    public PortalAccountDto setAltPhoneNumber(String altPhoneNumber) {
        this.altPhoneNumber = altPhoneNumber;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
