package com.bw.dentaldoor.domain.account;

import com.bw.dentaldoor.enums.PermissionTypeConstant;
import org.apache.commons.lang3.StringUtils;


public enum DentalOfficeRole implements RolePermissionHolder {

    ADMIN("ADMIN",
            PermissionTypeConstant.ADD_ACCOUNT_MEMBER,
            PermissionTypeConstant.REMOVE_ACCOUNT_MEMBER_ROLE,
            PermissionTypeConstant.UPDATE_ACCOUNT_MEMBER_ROLE
    );

    private String displayName;
    private PermissionTypeConstant[] permissionConstant;

    DentalOfficeRole(PermissionTypeConstant... permissionConstant) {
        this.permissionConstant = permissionConstant;
    }

    DentalOfficeRole(String name, PermissionTypeConstant... permissionConstant) {
        this(permissionConstant);
        this.displayName = name;
    }

    @Override
    public String roleName() {
        return StringUtils.defaultString(displayName, name());
    }

    @Override
    public PermissionTypeConstant[] permissions() {
        return permissionConstant;
    }
}
