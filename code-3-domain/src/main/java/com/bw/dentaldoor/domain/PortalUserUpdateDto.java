package com.bw.dentaldoor.domain;

import com.bw.enums.GenderConstant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public
class PortalUserUpdateDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private GenderConstant gender;
    @Past
    private LocalDate dateOfBirth;
    private Boolean isInstructor;

    @Size(min = 1)
    private List<String> insuranceProviders;
}
