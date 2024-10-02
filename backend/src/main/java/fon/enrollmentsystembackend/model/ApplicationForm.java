package fon.enrollmentsystembackend.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@IdClass(ApplicationForm.PK.class)
@Table(name = "application_form")
@EntityListeners(AuditingEntityListener.class)
public class ApplicationForm {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "exam_event_id")
//    @JsonIgnoreProperties("applicationForms")
    private ExamEvent examEvent;

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
//    @JsonIgnoreProperties("applicationForms")
    private User user;

    @Column(name = "application_number")
    private int applicationNumber;

    @CreatedDate
//    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public ApplicationForm() {
    }

    public ApplicationForm(ExamEvent examEvent, User user, int applicationNumber) {
        this.examEvent = examEvent;
        this.user = user;
        this.applicationNumber = applicationNumber;
    }

    public ExamEvent getExamEvent() {
        return examEvent;
    }

    public void setExamEvent(ExamEvent examEvent) {
        this.examEvent = examEvent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(int applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ApplicationForm{" +
                "examEventId=" + examEvent +
                ", userId=" + user +
                ", applicationNumber=" + applicationNumber +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class PK implements Serializable {

        private ExamEvent examEvent;

        private User user;

        public ExamEvent getExamEvent() {
            return examEvent;
        }

        public void setExamEvent(ExamEvent examEvent) {
            this.examEvent = examEvent;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PK pk = (PK) o;
            return Objects.equals(examEvent, pk.examEvent) && Objects.equals(user, pk.user);
        }

        @Override
        public int hashCode() {
            return Objects.hash(examEvent, user);
        }
    }
}
