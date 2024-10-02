package fon.enrollmentsystembackend.validator.annotation;

import fon.enrollmentsystembackend.validator.DateAfterNowValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateAfterNowValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateAfterNow {
    String message() default "Date must be after the current date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
