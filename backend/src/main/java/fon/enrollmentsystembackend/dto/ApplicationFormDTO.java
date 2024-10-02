package fon.enrollmentsystembackend.dto;

import com.fasterxml.jackson.annotation.JsonView;
import fon.enrollmentsystembackend.view.ApplicationFormView;
import fon.enrollmentsystembackend.view.ExamEventView;
import fon.enrollmentsystembackend.view.UserView;
import jakarta.validation.constraints.Positive;

public class ApplicationFormDTO {

    @JsonView({ApplicationFormView.Read.class, UserView.ReadWithApplicationForms.class})
    private ExamEventDTO examEvent;

    @JsonView(ApplicationFormView.Create.class)
    @Positive(message = "Exam event id should be positive")
    private int examEventId;

    @JsonView({ApplicationFormView.Read.class, ExamEventView.ReadWithApplicationForms.class})
    private UserDTO user;

    @JsonView(ApplicationFormView.Create.class)
    @Positive(message = "User id should be positive")
    private int userId;

    @JsonView({ApplicationFormView.Read.class, ExamEventView.ReadWithApplicationForms.class, UserView.ReadWithApplicationForms.class})
    private int applicationNumber;

    public ApplicationFormDTO() {
    }

    public ExamEventDTO getExamEvent() {
        return examEvent;
    }

    public void setExamEvent(ExamEventDTO examEvent) {
        this.examEvent = examEvent;
    }

    public int getExamEventId() {
        return examEventId;
    }

    public void setExamEventId(int examEventId) {
        this.examEventId = examEventId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(int applicationNumber) {
        this.applicationNumber = applicationNumber;
    }
}
