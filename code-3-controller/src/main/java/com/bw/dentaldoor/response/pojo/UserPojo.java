//package com.bw.dentaldoor.response.pojo;
//
//import com.bw.TimeUtil;
//import com.bw.auth.ApiResourcePortalUser;
//import com.bw.dentaldoor.entity.InsuranceCompany;
//import com.bw.dentaldoor.entity.PortalUser;
//import com.bw.enums.GenderConstant;
//import com.bw.enums.GenericStatusConstant;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//public class UserPojo {
//
//    private static TimeUtil timeUtil = new TimeUtil();
//
//    private String displayName;
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String mobileNumber;
//    private String username;
//    private String userId;
//    private GenericStatusConstant status;
//    private Date dateCreated;
//    private Date lastUpdated;
//
//    private String createdBy;
//    private String lastUpdatedBy;
//
//    private Long displayPictureId;
//
//    private boolean requiresPasswordUpdate;
//    private String authToken;
//    private Boolean isStudent;
//
//    private LocalDate dateOfBirth;
//    private GenderConstant gender;
//    private Boolean isInstructor;
//    private Boolean setupComplete;
//
//
//    private List<InsuranceCompany> insuranceProviders;
//    private Long numberOfInvitationsSent;
//
//    static {
//        timeUtil.init();
//    }
//
//    public UserPojo(PortalUser portalUser, ApiResourcePortalUser apiResourcePortalUser) {
//        this(portalUser);
//        requiresPasswordUpdate = apiResourcePortalUser.isPasswordResetRequired();
//    }
//
//    public UserPojo(PortalUser portalUser) {
//        firstName = portalUser.getFirstName();
//        lastName = portalUser.getLastName();
//        status = portalUser.getStatus();
//        email = portalUser.getEmail();
//        username = portalUser.getUsername();
//        userId = portalUser.getUserId();
//        mobileNumber = portalUser.getPhoneNumber();
//        dateCreated = portalUser.getDateCreated();
//        lastUpdated = portalUser.getLastUpdated();
//        isStudent = portalUser.getIsStudent();
//        isInstructor = portalUser.getIsInstructor();
//        if (portalUser.getDateOfBirth() != null) {
//            dateOfBirth = timeUtil.toLocalDate(portalUser.getDateOfBirth());
//        }
//        gender = portalUser.getGender();
//    }
//}
