package com.bw.dentaldoor.startup;

import com.bw.dentaldoor.dao.account.PortalAccountMembershipRepository;
import com.bw.dentaldoor.dao.account.PortalAccountRepository;
import com.bw.dentaldoor.dao.account.PortalAccountTypeRoleRepository;
import com.bw.dentaldoor.domain.account.C3AdminRole;
import com.bw.dentaldoor.domain.account.NewUserDto;
import com.bw.dentaldoor.domain.account.PortalAccountDto;
import com.bw.dentaldoor.entity.PortalAccount;
import com.bw.dentaldoor.enums.PortalAccountTypeConstant;
import com.bw.dentaldoor.service.RoleService;
import com.bw.dentaldoor.service.UserRegistrationService;
import com.bw.enums.GenericStatusConstant;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import com.bw.dentaldoor.service.PortalAccountService;

import java.util.Collections;


public class PortalAccountSetup {

    private final PortalAccountRepository portalAccountRepository;
    private final PortalAccountService portalAccountService;
    private final RoleService roleService;
    private final PortalAccountTypeRoleRepository portalAccountTypeRoleRepository;
    private final PortalAccountMembershipRepository portalAccountMembershipRepository;
    private final UserRegistrationService userRegistrationService;

    public PortalAccountSetup(
            PortalAccountRepository portalAccountRepository,
            PortalAccountService portalAccountService,
            RoleService roleService,
            PortalAccountTypeRoleRepository portalAccountTypeRoleRepository,
            PortalAccountMembershipRepository portalAccountMembershipRepository,
            UserRegistrationService userRegistrationService
	) {
        this.portalAccountRepository = portalAccountRepository;
        this.portalAccountService = portalAccountService;
        this.roleService = roleService;
        this.portalAccountTypeRoleRepository = portalAccountTypeRoleRepository;
        this.portalAccountMembershipRepository = portalAccountMembershipRepository;
        this.userRegistrationService = userRegistrationService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        for (C3AdminRole ddAdminRole : C3AdminRole.values()) {
            portalAccountTypeRoleRepository.findActiveByPortalAccountTypeAndName(PortalAccountTypeConstant.DENTAL_DOOR_ADMIN, ddAdminRole.name())
                    .orElseGet(() -> roleService.createRole(PortalAccountTypeConstant.DENTAL_DOOR_ADMIN, ddAdminRole.name(), ddAdminRole.roleName()));
        }
        PortalAccount portalAccount = portalAccountRepository.findFirstByTypeAndStatus(PortalAccountTypeConstant.DENTAL_DOOR_ADMIN, GenericStatusConstant.ACTIVE)
                .orElseGet(() -> {
                    PortalAccountDto dto = new PortalAccountDto();
                    dto.setType(PortalAccountTypeConstant.DENTAL_DOOR_ADMIN);
                    dto.setName(PortalAccountTypeConstant.DENTAL_DOOR_ADMIN.name());
                    return portalAccountService.createPortalAccount(dto);
                });
        portalAccountTypeRoleRepository.findActiveByPortalAccountTypeAndName(portalAccount.getType(), C3AdminRole.ADMIN.name())
                .ifPresent(role -> {
                    if (portalAccountMembershipRepository.countActiveMemberships(portalAccount, role) == 0) {
                        NewUserDto newUserDto = new NewUserDto();
                        newUserDto.setUsername("admin.dd");
                        newUserDto.setEmail("admin@dd.byteworks.com.ng");
                        newUserDto.setFirstName("admin");
                        newUserDto.setLastName("dd");
                        newUserDto.setRoles(Collections.singletonMap(portalAccount.getCode(), Collections.singleton(role.getName())));
                        userRegistrationService.registerUserWithRoles(newUserDto);
                    }
                });
    }
}
