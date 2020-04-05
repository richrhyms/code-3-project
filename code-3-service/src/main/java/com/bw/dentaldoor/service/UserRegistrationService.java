package com.bw.dentaldoor.service;

import com.bw.dentaldoor.domain.account.NewUserDto;
import com.bw.dentaldoor.domain.account.SignUpResponse;
import com.bw.dentaldoor.domain.account.UserRegistrationDto;
import com.bw.dentaldoor.entity.PortalUser;

public interface UserRegistrationService {

    PortalUser doUserRegistration(NewUserDto newUserDto);

    PortalUser registerUserWithRoles(NewUserDto newUserDto);

    SignUpResponse doUserRegistration(UserRegistrationDto userRegistrationDto);

    SignUpResponse registerUserWithDefaultPassword(UserRegistrationDto userRegistrationDto);
}
