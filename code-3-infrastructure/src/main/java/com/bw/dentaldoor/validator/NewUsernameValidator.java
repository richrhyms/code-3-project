package com.bw.dentaldoor.validator;

import com.bw.dentaldoor.constraint.NewUsername;

import javax.inject.Named;
import javax.validation.ConstraintValidatorContext;

//import com.bw.docscanner.dao.account.PortalUserRoleRepository;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Named
public class NewUsernameValidator implements NewUsername.Validator {

//    @Inject
//    private AuthApiClient authApiClient;

//    @Inject
//    private PortalUserRoleRepository membershipRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
//        try {
//            if (value == null) {
//                return true;
//            }
//            if (StringUtils.isBlank(value)) {
//                return false;
//            }
//            ApiResourcePortalUser user = bwAuthApiClient.getUser(value);
//            return membershipRepository.findActiveRolesByUserId(user.getUserId()).isEmpty();
//        } catch (ApiCallException e) {
//            if (e.getCode() == 404) {
//                return true;
//            }
//            throw e;
//        }
        return false;
    }
}
