package fon.enrollmentsystembackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import fon.enrollmentsystembackend.util.DateRange;
import fon.enrollmentsystembackend.validator.annotation.DateAfterNow;
import fon.enrollmentsystembackend.validator.annotation.ValidDateRange;
import fon.enrollmentsystembackend.view.ApplicationFormView;
import fon.enrollmentsystembackend.view.ExamEventView;
import fon.enrollmentsystembackend.view.UserView;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class ExamEventDTO {

    @JsonView({ExamEventView.Read.class, ApplicationFormView.Read.class, UserView.ReadWithApplicationForms.class})
    private int id;

    @JsonView({ExamEventView.Basic.class, ApplicationFormView.Read.class, UserView.ReadWithApplicationForms.class})
    @NotBlank(message = "Exam event name is mandatory")
    @Size(min = 2, message = "Exam event name should not be shorter than two letters")
    private String examEventName;

    @JsonView({ExamEventView.Basic.class, ApplicationFormView.Read.class, UserView.ReadWithApplicationForms.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull(message = "Maintenance date cannot be null")
    @DateAfterNow(message = "Maintenance date must be in the future")
    private LocalDate maintenanceDate;

    @JsonView({ExamEventView.Basic.class, ApplicationFormView.Read.class, UserView.ReadWithApplicationForms.class})
    @Valid
    @ValidDateRange(message = "Application start date must be before the application end date")
    private DateRange applicationDateRange;

    @JsonView(ExamEventView.Basic.class)
    @PositiveOrZero(message = "Price should not be less than 0")
    private int price;

    @JsonView(ExamEventView.Read.class)
    private int numberOfApplicants;

    @JsonView({ExamEventView.Read.class, ApplicationFormView.Read.class, UserView.ReadWithApplicationForms.class})
    private String typeName;

    @JsonView(ExamEventView.Create.class)
    @Positive(message = "Type id should be positive")
    private int typeId;

    @JsonView(ExamEventView.Create.class)
    @Positive(message = "Creator id should be positive")
    private int createdById;

    @JsonView(ExamEventView.ReadWithApplicationForms.class)
    private List<ApplicationFormDTO> applicationForms;

    public ExamEventDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExamEventName() {
        return examEventName;
    }

    public void setExamEventName(String examEventName) {
        this.examEventName = examEventName;
    }

    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public DateRange getApplicationDateRange() {
        return applicationDateRange;
    }

    public void setApplicationDateRange(DateRange applicationDateRange) {
        this.applicationDateRange = applicationDateRange;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberOfApplicants() {
        return numberOfApplicants;
    }

    public void setNumberOfApplicants(int numberOfApplicants) {
        this.numberOfApplicants = numberOfApplicants;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public List<ApplicationFormDTO> getApplicationForms() {
        return applicationForms;
    }

    public void setApplicationForms(List<ApplicationFormDTO> applicationForms) {
        this.applicationForms = applicationForms;
    }
}
