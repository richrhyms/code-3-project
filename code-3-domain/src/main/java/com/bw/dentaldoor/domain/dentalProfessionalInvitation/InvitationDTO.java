package com.bw.dentaldoor.domain.dentalProfessionalInvitation;

import com.bw.dentaldoor.constraint.ValidPhoneNumber;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class InvitationDTO {

    @NotBlank
    @Email
    private String email;

    @ValidPhoneNumber
    private String phoneNumber;

    @NotBlank
    private String name;
}
