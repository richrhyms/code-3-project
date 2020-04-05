package com.bw.dentaldoor.service;

import com.bw.dentaldoor.domain.PortalUserRoleUpdateDto;
import com.bw.dentaldoor.entity.PortalAccountMemberRole;
import com.bw.dentaldoor.entity.PortalAccountMembership;
import com.bw.dentaldoor.entity.PortalAccountTypeRole;

import java.util.List;

public interface PortalAccountMemberRoleService {
    List<PortalAccountMemberRole> updateUserRoles(PortalAccountMembership membership, PortalUserRoleUpdateDto dto, List<PortalAccountTypeRole> typeRoles);
}