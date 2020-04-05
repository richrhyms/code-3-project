package com.bw.security;

import com.bw.dentaldoor.enums.PermissionTypeConstant;
import com.bw.dentaldoor.enums.PortalAccountTypeConstant;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@AccessConstraint(HasPermissionAccessManager.class)
@Target({TYPE, METHOD})
@Retention(RUNTIME)
@Documented
public @interface HasPermission {

//    PortalAccountTypeConstant accountType();

    PermissionTypeConstant[] value();
}
