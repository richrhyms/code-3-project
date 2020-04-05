package com.bw.dentaldoor.integration;

import com.bw.auth.ApiResourcePortalUser;
import com.bw.auth.BwAuthAdminApiClient;
import com.bw.auth.LoginType;
import com.bw.dentaldoor.domain.account.SignUpResponse;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.integration.AuthClient;

import javax.inject.Named;

@Named
public class AuthClientImpl implements AuthClient {

    private final BwAuthAdminApiClient bwAuthAdminApiClient;

    public AuthClientImpl(BwAuthAdminApiClient bwAuthAdminApiClient) {
        this.bwAuthAdminApiClient = bwAuthAdminApiClient;
    }

    @Override
    public SignUpResponse createUser(PortalUser portalUser) {
        ApiResourcePortalUser apiResourcePortalUser = toApiResourcePortalUser(portalUser);
        return getSignUpResponse(apiResourcePortalUser);
    }

    @Override
    public SignUpResponse createUser(PortalUser portalUser, String password) {
        ApiResourcePortalUser apiResourcePortalUser = toApiResourcePortalUser(portalUser);
        apiResourcePortalUser.setPassword(password);
        apiResourcePortalUser.setPasswordResetRequired(false);
        return getSignUpResponse(apiResourcePortalUser);
    }

    private SignUpResponse getSignUpResponse(ApiResourcePortalUser apiResourcePortalUser) {
        ApiResourcePortalUser user = bwAuthAdminApiClient.createUser(apiResourcePortalUser);
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setUserId(user.getUserId());
        signUpResponse.setAuthToken(user.getAuthToken());
        return signUpResponse;
    }

    private ApiResourcePortalUser toApiResourcePortalUser(PortalUser user) {
        ApiResourcePortalUser apiResourcePortalUser = new ApiResourcePortalUser();
        apiResourcePortalUser.setUsername(user.getUsername());
        apiResourcePortalUser.setPassword(user.getGeneratedPassword());
        apiResourcePortalUser.setPasswordResetRequired(true);
        apiResourcePortalUser.setLoginType(LoginType.USERNAME);

        apiResourcePortalUser.setEmail(user.getEmail());
        apiResourcePortalUser.setEmailVerificationRequired(false);

        apiResourcePortalUser.setFirstName(user.getFirstName());
        apiResourcePortalUser.setLastName(user.getLastName());
        return apiResourcePortalUser;
    }
}
