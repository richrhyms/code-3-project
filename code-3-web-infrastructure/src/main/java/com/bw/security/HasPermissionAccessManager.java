package com.bw.security;


//import com.bw.dentaldoor.domain.pojo.AccountMembershipPojo;
import com.bw.dentaldoor.domain.pojo.AccountMembershipPojo;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.principal.RequestPrincipal;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Named
public class HasPermissionAccessManager implements AccessStatusSource<HasPermission> {

    @Inject
    private Provider<RequestPrincipal> requestPrincipalProvider;

    @Override
    public AccessStatus getStatus(HasPermission accessConstraint) {
        List<AccountMembershipPojo> accountPermissionStream = requestPrincipalProvider.get().getAccountPermissions();

        accountPermissionStream = accountPermissionStream.stream().filter(it -> it.getPermissions() != null).collect(Collectors.toList());

        return accountPermissionStream.stream().anyMatch(it -> !Collections.disjoint(Arrays.asList(accessConstraint.value()), it.getPermissions()))
                ? AccessStatus.allowed()
                : AccessStatus.denied(String.format("Required permissions missing: %s", Arrays.asList(accessConstraint.value())));
    }
}
