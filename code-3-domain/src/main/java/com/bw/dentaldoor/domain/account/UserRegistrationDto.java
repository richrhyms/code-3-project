package com.bw.dentaldoor.domain.account;

import com.bw.dentaldoor.domain.enumeration.UserTypeConstant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRegistrationDto {

    @NotBlank
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String mobileNumber;

    @NotNull
    private UserTypeConstant userType;

//    @NotBlank
    private String password;

    @NotBlank
    private String otpPin;
}
