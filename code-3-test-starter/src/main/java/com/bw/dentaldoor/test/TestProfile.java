package com.bw.dentaldoor.test;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.*;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Profile("test")
public @interface TestProfile {
}
