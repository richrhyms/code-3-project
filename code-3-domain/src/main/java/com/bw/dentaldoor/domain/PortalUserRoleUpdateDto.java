package com.bw.dentaldoor.domain;

import com.bw.dentaldoor.entity.PortalAccount;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class PortalUserRoleUpdateDto {
    @NotBlank
    private String accountCode;
    @NotNull
    @Size(min = 1)
    private Set<String> roles;
}
