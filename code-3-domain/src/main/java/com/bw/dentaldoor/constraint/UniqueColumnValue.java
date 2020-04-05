package com.bw.dentaldoor.constraint;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Oluwatobi Adenekan
 * email:  tadenekan@byteworks.com.ng
 * date:    22/04/2019
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE_USE})
@Constraint(validatedBy = {UniqueColumnValue.Validator.class})
public @interface UniqueColumnValue {
    String message() default "{com.bw.ics.constraints.column.name.does.not.exist}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> entity();

    String column();

    String value() default "";

    interface Validator extends ConstraintValidator<UniqueColumnValue, String> {
    }
}
