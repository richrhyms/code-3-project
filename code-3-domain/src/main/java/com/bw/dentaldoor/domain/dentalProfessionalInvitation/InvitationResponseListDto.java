package com.bw.dentaldoor.domain.dentalProfessionalInvitation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class InvitationResponseListDto {

    @NotNull
    @NotEmpty
    private List<@Valid InvitationResponseDto> invitationResponses;
}
