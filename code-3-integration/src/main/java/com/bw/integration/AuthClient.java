package com.bw.integration;


import com.bw.dentaldoor.domain.account.SignUpResponse;
import com.bw.dentaldoor.entity.PortalUser;

public interface AuthClient {

    SignUpResponse createUser(PortalUser portalUser);

    SignUpResponse createUser(PortalUser portalUser, String password);
}
