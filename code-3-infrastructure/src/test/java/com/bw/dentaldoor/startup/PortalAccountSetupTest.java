//package com.bw.dentaldoor.startup;
//
//import com.bw.auth.ApiResourcePortalUser;
//import com.bw.auth.BwAuthAdminApiClient;
//import com.bw.dentaldoor.dao.account.PortalAccountRepository;
//import com.bw.dentaldoor.entity.PortalAccount;
//import com.bw.dentaldoor.enums.PortalAccountTypeConstant;
//import com.bw.enums.GenericStatusConstant;
//import com.bw.dentaldoor.test.IntegrationTest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.mockito.stubbing.Answer;
//
//import javax.inject.Inject;
//
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PortalAccountSetupTest extends IntegrationTest {
//
//    private PortalAccountSetup portalAccountSetup;
//
//    @Inject
//    private PortalAccountRepository portalAccountRepository;
//
//    @Inject
//    private PortalAccountMembershipRepository portalAccountMembershipRepository;
//
//    @Inject
//    private BwAuthAdminApiClient bwAuthAdminApiClient;
//
//    @BeforeEach
//    public void init() {
//        portalAccountSetup = applicationContext.getAutowireCapableBeanFactory().createBean(PortalAccountSetup.class);
//    }
//
//    @Test
//    public void setupDefaultAccount() {
//        Mockito.when(bwAuthAdminApiClient.createUser(Mockito.any(ApiResourcePortalUser.class)))
//                .then((Answer<ApiResourcePortalUser>) invocation -> {
//                    ApiResourcePortalUser argument = invocation.getArgument(0, ApiResourcePortalUser.class);
//                    argument.setUserId(UUID.randomUUID().toString());
//                    return argument;
//                });
//        portalAccountSetup.init();
//        Optional<PortalAccount> optionalPortalAccount = portalAccountRepository.findFirstByTypeAndStatus(PortalAccountTypeConstant.DENTAL_DOOR_ADMIN, GenericStatusConstant.ACTIVE);
//        assertTrue(optionalPortalAccount.isPresent());
//        assertEquals(1, portalAccountMembershipRepository.countActiveMemberships(optionalPortalAccount.get()));
//    }
//
//}
