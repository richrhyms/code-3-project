package com.bw.dentaldoor.constraint;


import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*

Account Validator will not check if the string Value is null
or is empty make sure to use @NotBlack on the string prior to the account Validator

*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE,ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
@Constraint(validatedBy = AccountValidator.AccountValidatorConstraint.class )
public @interface AccountValidator {

    String message() default "{com.bw.ics.constraints.AccountValidator.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "";


    interface AccountValidatorConstraint extends ConstraintValidator<AccountValidator,String> {

    }

}
