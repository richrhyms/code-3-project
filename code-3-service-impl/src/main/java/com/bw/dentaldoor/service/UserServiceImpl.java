package com.bw.dentaldoor.service;

import com.bw.TimeUtil;
import com.bw.dentaldoor.dao.PortalUserRepository;
import com.bw.dentaldoor.domain.PortalUserUpdateDto;
import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.principal.RequestPrincipal;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import javax.inject.Provider;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Jabir Minjibir <jminjibir@byteworks.com.ng>
 */
@Named
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PortalUserRepository portalUserRepository;
    private final Provider<RequestPrincipal> requestPrincipalProvider;
    private final TimeUtil timeUtil;

    @Override
    @Transactional
    public PortalUser updateUser(PortalUserUpdateDto userUpdateDto) {
        PortalUser user = requestPrincipalProvider.get().getPortalUser();

        user.setFirstName(userUpdateDto.getFirstName());
        user.setLastName((userUpdateDto.getLastName()));
        user.setGender(userUpdateDto.getGender());
        user.setDateOfBirth(Optional.ofNullable(userUpdateDto.getDateOfBirth())
                .map(timeUtil::toDate)
                .orElse(null));
        user.setIsInstructor(userUpdateDto.getIsInstructor());

        user.setDisplayName(String.format("%s %s", userUpdateDto.getFirstName(), userUpdateDto.getLastName()));

//        if (userUpdateDto.getInsuranceProviders() != null) {
//            setInsuranceProviders(user, insuranceCompanyRepository.findActiveByCodes(userUpdateDto.getInsuranceProviders()));
//        }

        return portalUserRepository.save(user);
    }


}
