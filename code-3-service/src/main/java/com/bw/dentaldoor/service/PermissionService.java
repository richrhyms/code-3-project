package com.bw.dentaldoor.service;


import com.bw.dentaldoor.entity.PortalAccountTypeRole;
import com.bw.dentaldoor.entity.RolePermission;
import com.bw.dentaldoor.enums.PermissionTypeConstant;


public interface PermissionService {
    RolePermission addPermission(PortalAccountTypeRole role, PermissionTypeConstant permissionConstant);
}
