package com.bw.dentaldoor.domain.account;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DpCompleteProfileDto {

    @NotBlank
    private String stateCodeWhereLicensed;

    @NotBlank
    private String licenseNumber;

    private String dentalOfficeCode;

    @NotBlank
    private String stateCode;

    @NotBlank
    private String cityCode;

    @NotBlank
    private String zipCode;

    private String website;

    @NotBlank
    private String dpSpecializationCode;
}
