//package com.bw.dentaldoor.controller;
//
//import com.bw.auth.ApiResourcePortalUser;
//import com.bw.dentaldoor.entity.*;
//import com.bw.dentaldoor.enums.InvitationTypeConstant;
//import com.bw.dentaldoor.principal.RequestPrincipal;
//import com.bw.dentaldoor.service.UserService;
//import com.bw.security.Public;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.*;
//
//import javax.inject.Provider;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@RestController
//@RequiredArgsConstructor
//public class UserController {
//
//    private final Provider<RequestPrincipal> requestPrincipalProvider;
//    private final Provider<ApiResourcePortalUser> apiResourcePortalUserProvider;
//    private final UserService userService;
////
////    @GetMapping("/me")
////    public UserPojo userDetails() {
////        PortalUser portalUser = requestPrincipalProvider.get().getPortalUser();
////        return userHandler.getUserPojo(portalUser, apiResourcePortalUserProvider.get());
////    }
//
//    @Public
//    @PostMapping("/logout")
//    public ResponseEntity logoutUser(HttpServletResponse response) {
//        Cookie cookie = new Cookie(RequestPrincipal.AUTH_TOKEN_NAME, "");
//        cookie.setMaxAge(0);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);
//        return ResponseEntity.ok().build();
//    }
//
//
//
//}
