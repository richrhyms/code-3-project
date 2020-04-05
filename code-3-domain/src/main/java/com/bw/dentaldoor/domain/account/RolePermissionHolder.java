package com.bw.dentaldoor.domain.account;


import com.bw.dentaldoor.enums.PermissionTypeConstant;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public interface RolePermissionHolder {
    String roleName();

    PermissionTypeConstant[] permissions();
}
