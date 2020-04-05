package com.bw.dentaldoor.service;


import com.bw.dentaldoor.dao.account.PortalAccountTypeRoleRepository;
import com.bw.dentaldoor.dao.account.RolePermissionRepository;
import com.bw.dentaldoor.domain.account.C3AdminRole;
import com.bw.dentaldoor.domain.account.DentalOfficeRole;
import com.bw.dentaldoor.domain.account.RolePermissionHolder;
import com.bw.dentaldoor.entity.PortalAccount;
import com.bw.dentaldoor.entity.PortalAccountTypeRole;
import com.bw.dentaldoor.enums.PermissionTypeConstant;
import com.bw.enums.GenericStatusConstant;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Named;


@Named
public class AccountSetupServiceImpl implements AccountSetupService {

    private final PortalAccountTypeRoleRepository portalAccountTypeRoleRepository;
    private final RoleService roleService;
    private final PermissionService permissionService;
    private final RolePermissionRepository rolePermissionRepository;

    public AccountSetupServiceImpl(PortalAccountTypeRoleRepository portalAccountTypeRoleRepository, RoleService roleService, PermissionService permissionService, RolePermissionRepository rolePermissionRepository) {
        this.portalAccountTypeRoleRepository = portalAccountTypeRoleRepository;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.rolePermissionRepository = rolePermissionRepository;
    }


//    @PostConstruct
//    public void updateRolePermissions() {
//        updateRolePermissions(PortalAccountTypeConstant.DENTAL_DOOR_ADMIN, C3AdminRole.values());
//        updateRolePermissions(PortalAccountTypeConstant.DENTAL_OFFICE, DentalOfficeRole.values());
//        }

    @Override
    public void setup(PortalAccount portalAccount) {
        switch (portalAccount.getType()) {
            case DENTAL_DOOR_ADMIN:
                prepareRoles(portalAccount, C3AdminRole.values());
                break;
            case DENTAL_OFFICE:
                prepareRoles(portalAccount, DentalOfficeRole.values());
                break;
        }
    }

    @Transactional
    public void prepareRoles(PortalAccount portalAccount, RolePermissionHolder[] accountRoles) {
        for (RolePermissionHolder roleDes: accountRoles){
            PortalAccountTypeRole role = portalAccountTypeRoleRepository.findActiveByPortalAccountTypeAndName(portalAccount.getType(), roleDes.roleName())
                    .orElseGet(() -> roleService.createRole(portalAccount.getType(), roleDes.roleName(), roleDes.roleName()));

            for (PermissionTypeConstant permissionConstant : roleDes.permissions()) {
                rolePermissionRepository.findByRoleAndAccountTypeAndStatus(role,
                        permissionConstant,
                        GenericStatusConstant.ACTIVE)
                        .orElseGet(() -> permissionService.addPermission(role, permissionConstant));
            }
        }
    }


}
