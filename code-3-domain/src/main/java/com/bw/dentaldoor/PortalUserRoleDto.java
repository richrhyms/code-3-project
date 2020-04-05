package com.bw.dentaldoor;

import com.bw.dentaldoor.entity.PortalAccount;
import com.bw.dentaldoor.enums.PermissionTypeConstant;
import com.bw.dentaldoor.enums.PortalAccountTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class PortalUserRoleDto {
    private PortalAccountTypeConstant accountType;
    private Set<PermissionTypeConstant> permissions;

}
