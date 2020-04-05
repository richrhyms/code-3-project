package com.bw.dentaldoor.domain.pojo;

import com.bw.dentaldoor.entity.PortalAccount;
import com.bw.dentaldoor.enums.PermissionTypeConstant;
import com.bw.dentaldoor.enums.PortalAccountTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Otaru Richard <richotaru@gmail.com>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public
class AccountMembershipPojo<T> {

    private String accountName;

    private String accountCode;

    private Long accountId;

    private PortalAccountTypeConstant accountType;

    private Set<String> roles;

    private Set<PermissionTypeConstant> permissions;

    private T otherData;

    public AccountMembershipPojo(PortalAccount portalAccount) {
        accountName = portalAccount.getName();
        accountCode = portalAccount.getCode();
        accountType = portalAccount.getType();
        accountId = portalAccount.getId();
    }
}
