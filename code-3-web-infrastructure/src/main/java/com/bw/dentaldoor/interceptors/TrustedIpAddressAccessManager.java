package com.bw.dentaldoor.interceptors;

import com.bw.SettingService;
import com.bw.security.AccessStatus;
import com.bw.security.TrustedIpAddress;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Named
public class TrustedIpAddressAccessManager {

//    @Inject
//    private SettingService settingService;

    @Inject
    private HttpServletRequest request;

    public AccessStatus getStatus(TrustedIpAddress accessConstraint) {
//        Optional<String> value = settingService.getString(accessConstraint.value());

        String ipAddress = StringUtils.defaultIfBlank(
                request.getHeader("X-FORWARDED-FOR"),
                request.getRemoteAddr());
//        if (value.isPresent()) {
//            return Arrays.asList(value.get().split(" *, *")).contains(ipAddress)
//                    ? AccessStatus.allowed()
//                    : AccessStatus.denied(ipAddress);
//        }
        if (accessConstraint.defaultIpAddresses().length > 0) {
            return Arrays.asList(accessConstraint.defaultIpAddresses()).contains(ipAddress)
                    ? AccessStatus.allowed()
                    : AccessStatus.denied(ipAddress);
        }
        return AccessStatus.denied("");
    }
}
