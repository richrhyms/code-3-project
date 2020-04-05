//package com.bw.dentaldoor.controller;
//
//import com.bw.dentaldoor.dao.InviteRepository;
//import com.bw.dentaldoor.domain.AddressDto;
//import com.bw.dentaldoor.domain.NameAndCodeDto;
//import com.bw.dentaldoor.domain.PortalUserUpdateDto;
//import com.bw.dentaldoor.domain.dentalProfessionalInvitation.InvitationDTO;
//import com.bw.dentaldoor.domain.dentalProfessionalInvitation.InvitationListDTO;
//import com.bw.dentaldoor.domain.dentalprofessional.UpdateDentalProfessionalDto;
//import com.bw.dentaldoor.domain.pojo.AccountMembershipPojo;
//import com.bw.dentaldoor.dto.LanguageListDto;
//import com.bw.dentaldoor.entity.*;
//import com.bw.dentaldoor.enums.InvitationTypeConstant;
//import com.bw.dentaldoor.principal.RequestPrincipal;
//import com.bw.dentaldoor.response.pojo.DentalProfessionalInvitationPojo;
//import com.bw.dentaldoor.response.pojo.DentalProfessionalPojo;
//import com.bw.dentaldoor.response.pojo.QueryResultsPojo;
//import com.bw.dentaldoor.response.pojo.UserPojo;
//import com.bw.dentaldoor.search.filter.InviteSearchFilter;
//import com.bw.dentaldoor.test.WebIntegrationTest;
//import com.bw.enums.GenderConstant;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import javax.inject.Inject;
//import javax.servlet.http.Cookie;
//import java.time.LocalDate;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//class UserControllerTest extends WebIntegrationTest {
//
//    @Inject
//    private InviteRepository inviteRepository;
//
//    @Test
//    void userDetails() throws Exception {
//        PortalAccountMemberRole portalAccountMemberRole = modelFactory.create(PortalAccountMemberRole.class);
//        PortalUser portalUser = portalAccountMemberRole.getMembership().getPortalUser();
//
//        modelFactory.pipe(PortalUserImage.class)
//                .then(portalUserImage -> {
//                    portalUserImage.setPortalUser(portalUser);
//                    return portalUserImage;
//                }).create();
//
//        loggedInUser(portalUser);
//        mockMvc.perform(MockMvcRequestBuilders.get("/me"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(result -> {
//                    UserPojo userPojo = jsonToClass(result.getResponse().getContentAsString(), UserPojo.class);
//                    assertNotNull(userPojo);
//                    assertEquals(portalUser.getUsername(), userPojo.getUsername());
//                    assertEquals(portalUser.getUserId(), userPojo.getUserId());
//                    assertEquals(1, userPojo.getAccounts().size());
//                    AccountMembershipPojo membershipPojo = userPojo.getAccounts().get(0);
//                    assertEquals(portalAccountMemberRole.getRole().getName(), membershipPojo.getRoles().iterator().next());
//                });
//    }
//
//    @Test
//    void userDetailForDp() throws Exception {
//        DentalProfessional dentalProfessional = modelFactory.create(DentalProfessional.class);
//        loggedInUser(dentalProfessional.getPortalUser());
//        mockMvc.perform(MockMvcRequestBuilders.get("/me"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(result -> {
//                    UserPojo userPojo = jsonToClass(result.getResponse().getContentAsString(), UserPojo.class);
//                    assertNotNull(userPojo);
//                    assertNotNull(userPojo.getDentalProfessional());
//                    assertEquals(dentalProfessional.getId(), userPojo.getDentalProfessional().getId());
//                });
//    }
//
//    @Test
//    void logout() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/logout"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(result -> {
//                    Cookie authToken = result.getResponse().getCookie(RequestPrincipal.AUTH_TOKEN_NAME);
//                    assertNotNull(authToken);
//                    assertEquals(0, authToken.getMaxAge());
//                });
//    }
//
//    @Test
//    public void updateDentalProfessional() throws Exception {
//        DentalProfessional dentalProfessional = modelFactory.create(DentalProfessional.class);
//        loggedInUser(dentalProfessional.getPortalUser());
//        UpdateDentalProfessionalDto dto = new UpdateDentalProfessionalDto();
//        dto.setSpecializations(modelFactory.create(Specialization.class, 2).stream().map(Specialization::getCode).collect(Collectors.toList()));
//        dto.setLanguages(modelFactory.create(Language.class, 2).stream().map(Language::getCode).collect(Collectors.toList()));
//
//        City city = modelFactory.create(City.class);
//        AddressDto addressDto = new AddressDto();
//        addressDto.setCity(city.getCode());
//        addressDto.setStreetAddress(faker.address().streetAddress());
//        addressDto.setZipCode(faker.address().zipCode());
//
//        dto.setAddress(addressDto);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.patch("/me/dental-professional")
//                        .content(gson.toJson(dto))
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(result -> {
//                    DentalProfessionalPojo pojo = jsonToClass(result.getResponse().getContentAsString(), DentalProfessionalPojo.class);
//                    assertNotNull(pojo);
//                    assertEquals(pojo.getId(), dentalProfessional.getId());
//                    assertNotNull(pojo.getAddress());
//                    assertEquals(pojo.getAddress().getCity().getCode(), addressDto.getCity());
//                });
//    }
//
//    @Test
//    public void updateDentalProfessionalForPatient() throws Exception {
//        loggedInUser();
//        UpdateDentalProfessionalDto dto = new UpdateDentalProfessionalDto();
//        dto.setSpecializations(modelFactory.create(Specialization.class, 2).stream().map(Specialization::getCode).collect(Collectors.toList()));
//        dto.setLanguages(modelFactory.create(Language.class, 2).stream().map(Language::getCode).collect(Collectors.toList()));
//        mockMvc.perform(
//                MockMvcRequestBuilders.patch("/me/dental-professional")
//                        .content(gson.toJson(dto))
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
//    }
//
//    @Test
//    void searchInvitations() throws Exception {
//        DentalProfessional dentalProfessional = modelFactory.create(DentalProfessional.class);
//        loggedInUser(dentalProfessional.getPortalUser());
//
//        List<Invite> invites = modelFactory.pipe(Invite.class).then(invite1 -> {
//            invite1.setInvitedUser(dentalProfessional);
//            invite1.setType(InvitationTypeConstant.DENTAL_PRACTITIONER);
//            return invite1;
//        }).create(3);
//        modelFactory.pipe(Invite.class).then(invite1 -> {
//            invite1.setType(InvitationTypeConstant.DENTAL_PRACTITIONER);
//            return invite1;
//        }).create(3);
//
//        MockHttpServletRequestBuilder requestBuilder = get("/me/dental-professional/invitations");
////        requestBuilder.param(QInvite.invite.type.getMetadata().getName(), UUID.randomUUID().toString());
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(result -> {
//                    QueryResultsPojo<DentalProfessionalInvitationPojo> resultsPojo = gson.fromJson(result.getResponse().getContentAsString(), QueryResultsPojo.class);
//                    assertEquals(invites.size(), resultsPojo.getTotal());
//                });
//    }
//
//    @Test
//    void searchInvitationsByOfficeName() throws Exception {
//        DentalProfessional dentalProfessional = modelFactory.create(DentalProfessional.class);
//        loggedInUser(dentalProfessional.getPortalUser());
//
//        List<Invite> invites = modelFactory.pipe(Invite.class).then(invite -> {
//            invite.setInvitedUser(dentalProfessional);
//            invite.setType(InvitationTypeConstant.DENTAL_PRACTITIONER);
//            return invite;
//        }).create(3);
//
//        MockHttpServletRequestBuilder requestBuilder = get("/me/dental-professional/invitations");
//        requestBuilder.param(InviteSearchFilter.DENTAL_OFFICE_NAME, invites.iterator().next().getDentalOffice().getName());
////        requestBuilder.param(QInvite.invite.type.getMetadata().getName(), UUID.randomUUID().toString());
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(result -> {
//                    QueryResultsPojo<DentalProfessionalInvitationPojo> resultsPojo = gson.fromJson(result.getResponse().getContentAsString(), QueryResultsPojo.class);
//                    assertEquals(1, resultsPojo.getTotal());
//                });
//    }
//
//    @Test
//    public void setDentalProfessionalLanguages() throws Exception {
//        DentalProfessional dentalProfessional = modelFactory.create(DentalProfessional.class);
//        loggedInUser(dentalProfessional.getPortalUser());
//        Set<String> codes = modelFactory.create(Language.class, 2).stream().map(Language::getCode).collect(Collectors.toSet());
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/me/dental-professional/languages")
//                        .content(gson.toJson(new LanguageListDto(codes)))
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(result -> {
//                    DentalProfessionalPojo response = gson.fromJson(result.getResponse().getContentAsString(), DentalProfessionalPojo.class);
//                    assertNotNull(response);
//                    assertEquals(codes.size(), response.getLanguages().size());
//                    codes.removeAll(response.getLanguages().stream().map(NameAndCodeDto::getCode).collect(Collectors.toList()));
//                    assertTrue(codes.isEmpty());
//                });
//    }
//
//    @Test
//    public void setDentalProfessionalLanguagesWithInvalidCode() throws Exception {
//        DentalProfessional dentalProfessional = modelFactory.create(DentalProfessional.class);
//        loggedInUser(dentalProfessional.getPortalUser());
//        Set<String> codes = modelFactory.create(Language.class, 2).stream().map(Language::getCode).collect(Collectors.toSet());
//        codes.add(UUID.randomUUID().toString());
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/me/dental-professional/languages")
//                        .content(gson.toJson(new LanguageListDto(codes)))
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
//    }
//
//    @Test
//    void updatePortalUser_shouldReturn400_ifInvalidPortalUserUpdateDtoSent() throws Exception {
//        String portalUserUpdateDto = "";
//
//        mockMvc.perform(patch("/me")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(gson.toJson(portalUserUpdateDto)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void updatePortalUser_shouldReturn200_validPortalUserUpdateDtoSent() throws Exception {
//        PortalUser portalUser = loggedInUser();
//
//        PortalUserUpdateDto portalUserUpdateDto = new PortalUserUpdateDto();
//
//        LocalDate dob = LocalDate.now().minusYears(18);
//
//        portalUserUpdateDto.setFirstName(portalUser.getFirstName());
//        portalUserUpdateDto.setLastName(portalUser.getLastName());
//        portalUserUpdateDto.setGender(GenderConstant.MALE);
//        portalUserUpdateDto.setDateOfBirth(dob);
//        portalUserUpdateDto.setIsInstructor(true);
//
//        mockMvc.perform(patch("/me")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(portalUserUpdateDto)))
//                .andExpect(status().isOk())
//                .andExpect(result -> {
//                    UserPojo updated = objectMapper.readValue(result.getResponse().getContentAsString(), UserPojo.class);
//                    assertEquals(GenderConstant.MALE, updated.getGender());
//                    assertEquals(dob, updated.getDateOfBirth());
//
//                    assertEquals(true, updated.getIsInstructor());
//                });
//    }
//
//    @Test
//    void sendFriendInvitations() throws Exception {
//
//        PortalUser portalUser = loggedInUser();
//
//        List<InvitationDTO> invitationDTOS = new ArrayList<>();
//        InvitationDTO dto1 = new InvitationDTO();
//        dto1.setEmail(faker.internet().emailAddress());
//        dto1.setName(faker.funnyName().name());
//        dto1.setPhoneNumber("+2348034120919");
//        invitationDTOS.add(dto1);
//
//        MockHttpServletRequestBuilder requestBuilder =
//                post("/me/friend-invitations")
//                        .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(new InvitationListDTO(invitationDTOS)));
//
//        mockMvc.perform(requestBuilder).andExpect(status().isOk());
//        Optional<Invite> optionalInvite = inviteRepository.findPendingUserInvitation(portalUser, dto1.getEmail());
//        assertTrue(optionalInvite.isPresent());
//    }
//}
