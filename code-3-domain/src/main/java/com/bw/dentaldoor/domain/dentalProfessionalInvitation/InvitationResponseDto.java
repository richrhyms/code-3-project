package com.bw.dentaldoor.domain.dentalProfessionalInvitation;

import com.bw.dentaldoor.domain.enumeration.ResponseConstant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class InvitationResponseDto {
    @NotNull
    private ResponseConstant responseConstant;
    @NotNull
    private Long inviteId;
}
