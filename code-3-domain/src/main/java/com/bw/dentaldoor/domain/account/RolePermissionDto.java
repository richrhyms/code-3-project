package com.bw.dentaldoor.domain.account;

import com.bw.dentaldoor.enums.PermissionTypeConstant;

import java.util.List;

public class RolePermissionDto {
    private String role;
    private List<PermissionTypeConstant> permissions;

    public String getRole() {
        return role;
    }

    public RolePermissionDto setRole(String role) {
        this.role = role;
        return this;
    }

    public List<PermissionTypeConstant> getPermissions() {
        return permissions;
    }

    public RolePermissionDto setPermissions(List<PermissionTypeConstant> permissions) {
        this.permissions = permissions;
        return this;
    }
}
