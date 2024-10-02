package fon.enrollmentsystembackend.validator;

import fon.enrollmentsystembackend.validator.annotation.DateAfterNow;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateAfterNowValidator implements ConstraintValidator<DateAfterNow, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
//        If the value is null, let  @NotNull handle it seperately
        if (value == null)
            return true;

        return value.isAfter(LocalDate.now());
    }
}
