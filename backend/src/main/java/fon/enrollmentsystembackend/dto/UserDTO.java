package fon.enrollmentsystembackend.dto;

import com.fasterxml.jackson.annotation.JsonView;
import fon.enrollmentsystembackend.view.ApplicationFormView;
import fon.enrollmentsystembackend.view.ExamEventView;
import fon.enrollmentsystembackend.view.UserView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserDTO {

    @JsonView({UserView.Read.class, ApplicationFormView.Read.class, ExamEventView.ReadWithApplicationForms.class})
    private int id;

    @JsonView({UserView.Basic.class, ApplicationFormView.Read.class, ExamEventView.ReadWithApplicationForms.class})
    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, message = "First name should not be shorter than two letters")
    private String firstName;

    @JsonView({UserView.Basic.class, ApplicationFormView.Read.class, ExamEventView.ReadWithApplicationForms.class})
    @NotBlank(message = "Middle name is mandatory")
    @Size(min = 2, message = "Middle name should not be shorter than two letters")
    private String middleName;

    @JsonView({UserView.Basic.class, ApplicationFormView.Read.class, ExamEventView.ReadWithApplicationForms.class})
    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, message = "Last name should not be shorter than two letters")
    private String lastName;

    @JsonView(UserView.Basic.class)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @JsonView(UserView.Create.class)
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
    private String password;

    @JsonView({UserView.ReadDetail.class, UserView.ReadWithApplicationForms.class})
    private int price;

    @JsonView(UserView.ReadWithApplicationForms.class)
    private List<ApplicationFormDTO> applicationForms;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<ApplicationFormDTO> getApplicationForms() {
        return applicationForms;
    }

    public void setApplicationForms(List<ApplicationFormDTO> applicationForms) {
        this.applicationForms = applicationForms;
    }
}
