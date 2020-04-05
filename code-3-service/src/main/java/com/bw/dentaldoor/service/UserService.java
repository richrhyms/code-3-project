package com.bw.dentaldoor.service;

import com.bw.dentaldoor.domain.PortalUserUpdateDto;
import com.bw.dentaldoor.entity.*;

import java.util.List;

/**
 * @author Jabir Minjibir <jminjibir@byteworks.com.ng>
 */

public interface UserService {

    PortalUser updateUser(PortalUserUpdateDto userUpdateDto);

}
