package fon.enrollmentsystembackend.validator;

import fon.enrollmentsystembackend.util.DateRange;
import fon.enrollmentsystembackend.validator.annotation.ValidDateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, DateRange> {

    @Override
    public boolean isValid(DateRange dateRange, ConstraintValidatorContext context) {
        if (dateRange == null)
            return true; // Handle null separately with @NotNull

        LocalDate startDate = dateRange.getStartDate();
        LocalDate endDate = dateRange.getEndDate();

        if (startDate == null || endDate == null)
            return true; // Let @NotNull handle null cases

        // Check that startDate is before or equal to endDate
        return startDate.isBefore(endDate);
    }
}
