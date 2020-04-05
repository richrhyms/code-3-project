package com.bw.dentaldoor.domain.dentalProfessionalInvitation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvitationListDTO {

    @NotNull
    @NotEmpty
    private List<@Valid @NotNull InvitationDTO> invitations;

}
