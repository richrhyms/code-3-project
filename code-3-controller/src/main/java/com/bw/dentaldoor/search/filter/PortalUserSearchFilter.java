//package com.bw.dentaldoor.search.filter;
//
//
//import com.bw.dentaldoor.constraint.ExistsColumnValue;
//import com.bw.dentaldoor.entity.PortalAccount;
//import com.bw.dentaldoor.entity.PortalUser;
//import com.bw.dentaldoor.entity.QPortalUser;
//import io.swagger.v3.oas.annotations.Hidden;
//import lombok.*;
//import lombok.experimental.Delegate;
//import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
//import org.springframework.data.querydsl.binding.QuerydslBindings;
//
//import java.util.Date;
//import java.util.Optional;
//import java.util.Set;
//
//@Getter
//@Setter
//public class PortalUserSearchFilter implements QuerydslBinderCustomizer<QPortalUser> {
//
//    public static final String PORTAL_USER_DISPLAY_NAME = "name";
//
//    @Hidden
//    @Delegate
//    private PageDto pager = new PageDto();
//
//    @Hidden
//    @Delegate(excludes = Exclude.class)
//    private PortalUser portalUser = new PortalUser();
//
//    @ExistsColumnValue(value = PortalAccount.class,columnName = "code")
//    private String accountCode;
//    private Boolean excludeAccount;
//    private Set<String> roles;
//    private Date createdOnOrAfter;
//    private Date createdBefore;
//
//    public Optional<Set<String>> getRoles() {
//        return Optional.ofNullable(roles);
//    }
//
//    public Optional<Date> getCreatedOnOrAfter() {
//        return Optional.ofNullable(createdOnOrAfter);
//    }
//
//    public Optional<Date> getCreatedBefore() {
//        return Optional.ofNullable(createdBefore);
//    }
//
//    @Override
//    public void customize(QuerydslBindings bindings, QPortalUser root) {
//        bindings.bind(root.displayName).as(PORTAL_USER_DISPLAY_NAME).first((path, value) -> path.containsIgnoreCase(value));
//    }
//
//    @Getter
//    @Setter
//    private static class Exclude {
//        private String generatedPassword;
//        private Date dateCreated;
//        private Date lastUpdated;
//        private Date dateOfBirth;
//    }
//}
