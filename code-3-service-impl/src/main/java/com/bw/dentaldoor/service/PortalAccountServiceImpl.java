package com.bw.dentaldoor.service;

import com.bw.SequenceGenerator;
import com.bw.dentaldoor.dao.account.PortalAccountRepository;
import com.bw.dentaldoor.domain.account.PortalAccountDto;
import com.bw.dentaldoor.entity.PortalAccount;
import com.bw.dentaldoor.principal.RequestPrincipal;
import com.bw.dentaldoor.qualifier.PortalAccountCodeSequence;
import com.bw.enums.GenericStatusConstant;

import javax.inject.Named;
import javax.inject.Provider;
import javax.transaction.Transactional;
import java.util.Date;

@Named
public class PortalAccountServiceImpl implements PortalAccountService {

    private final SequenceGenerator sequenceGenerator;
    private final PortalAccountRepository portalAccountRepository;
    private final Provider<RequestPrincipal> requestPrincipal;
    private final AccountSetupService accountSetupService;

    public PortalAccountServiceImpl(@PortalAccountCodeSequence SequenceGenerator sequenceGenerator, PortalAccountRepository portalAccountRepository, Provider<RequestPrincipal> requestPrincipal, AccountSetupService accountSetupService) {
        this.sequenceGenerator = sequenceGenerator;
        this.portalAccountRepository = portalAccountRepository;
        this.requestPrincipal = requestPrincipal;
        this.accountSetupService = accountSetupService;
    }

    @Transactional
    @Override
    public PortalAccount createPortalAccount(PortalAccountDto portalAccountDto) {
        PortalAccount portalAccount = new PortalAccount();
        portalAccount.setType(portalAccountDto.getType());
        portalAccount.setName(portalAccountDto.getName());
        portalAccount.setCode(sequenceGenerator.getNext());
        portalAccount.setStatus(GenericStatusConstant.ACTIVE);
        portalAccount.setDateCreated(new Date());
        if(requestPrincipal.get() != null && requestPrincipal.get().getPortalUser() != null){
            portalAccount.setCreatedBy(requestPrincipal.get().getPortalUser());
        }
        portalAccountRepository.save(portalAccount);
        accountSetupService.setup(portalAccount);
        return portalAccount;
    }
}
