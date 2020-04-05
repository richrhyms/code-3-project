package com.bw.dentaldoor.service;

import com.bw.dentaldoor.domain.account.PortalAccountDto;
import com.bw.dentaldoor.entity.PortalAccount;

public interface PortalAccountService {

    PortalAccount createPortalAccount(PortalAccountDto portalAccountDto);
}
