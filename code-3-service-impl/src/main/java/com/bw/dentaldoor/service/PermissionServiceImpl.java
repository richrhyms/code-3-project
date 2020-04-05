package com.bw.dentaldoor.service;

import com.bw.dentaldoor.dao.AppRepository;
import com.bw.dentaldoor.entity.PortalAccountTypeRole;
import com.bw.dentaldoor.entity.RolePermission;
import com.bw.dentaldoor.enums.PermissionTypeConstant;
import com.bw.enums.GenericStatusConstant;

import javax.inject.Named;
import java.time.Instant;
import java.util.Date;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Named
public class PermissionServiceImpl implements PermissionService {
    private final AppRepository appRepository;

    public PermissionServiceImpl(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    public RolePermission addPermission(PortalAccountTypeRole role, PermissionTypeConstant permissionConstant) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPortalAccountTypeRole(role);
        rolePermission.setName(permissionConstant);
        rolePermission.setDateCreated(Date.from(Instant.now()));
        rolePermission.setStatus(GenericStatusConstant.ACTIVE);
        appRepository.persist(rolePermission);
        return rolePermission;
    }
}