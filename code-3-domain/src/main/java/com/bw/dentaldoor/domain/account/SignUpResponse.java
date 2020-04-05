package com.bw.dentaldoor.domain.account;

import com.bw.dentaldoor.entity.PortalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {

    private String userId;

    private String authToken;

    private PortalUser portalUser;
}
