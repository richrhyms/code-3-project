package com.bw.dentaldoor.domain.account;

import com.bw.dentaldoor.enums.PermissionTypeConstant;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Otaru Richard richotaru@gmail.com
 */
public enum C3AdminRole implements RolePermissionHolder {

    ADMIN("ADMIN",
            PermissionTypeConstant.VERIFY_DENTAL_OFFICE
    );
    private String displayName;
    private PermissionTypeConstant[] permissionConstant;

    C3AdminRole(PermissionTypeConstant... permissionConstant) {
        this.permissionConstant = permissionConstant;
    }

    C3AdminRole(String name, PermissionTypeConstant... permissionConstant) {
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
