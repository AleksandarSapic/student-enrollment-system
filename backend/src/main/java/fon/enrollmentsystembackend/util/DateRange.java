package fon.enrollmentsystembackend.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import fon.enrollmentsystembackend.validator.annotation.DateAfterNow;
import fon.enrollmentsystembackend.view.ApplicationFormView;
import fon.enrollmentsystembackend.view.ExamEventView;
import fon.enrollmentsystembackend.view.UserView;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class DateRange {

    @JsonView({ExamEventView.Basic.class, ApplicationFormView.Read.class, UserView.ReadWithApplicationForms.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull(message = "Start date cannot be null")
    @DateAfterNow(message = "Application start date must be in the future")
    private LocalDate startDate;

    @JsonView({ExamEventView.Basic.class, ApplicationFormView.Read.class, UserView.ReadWithApplicationForms.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull(message = "End date cannot be null")
    @DateAfterNow(message = "Application end date must be in the future")
    private LocalDate endDate;

    public DateRange() {
    }

    public DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public @NotNull(message = "Start date cannot be null") LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull(message = "Start date cannot be null") LocalDate startDate) {
        this.startDate = startDate;
    }

    public @NotNull(message = "End date cannot be null") LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull(message = "End date cannot be null") LocalDate endDate) {
        this.endDate = endDate;
    }
}