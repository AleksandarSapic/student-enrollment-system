package fon.enrollmentsystembackend.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exam_event")
@EntityListeners(AuditingEntityListener.class)
public class ExamEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "maintenance_date")
    private LocalDate maintenanceDate;

    @Column(name = "application_date_start")
    private LocalDate applicationDateStart;

    @Column(name = "application_date_end")
    private LocalDate applicationDateEnd;

    @Column(name = "price")
    private int price;

    @Column(name = "number_of_applicants")
    private int numberOfApplicants;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by")
    private Admin createdBy;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Admin updatedBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "type_id")
//    @JsonIgnoreProperties("examEvents")
    private ExamEventType type;

    @OneToMany(mappedBy = "examEvent", fetch = FetchType.EAGER)
    private List<ApplicationForm> applicationForms;

    public ExamEvent() {
    }

    public ExamEvent(String name, LocalDate maintenanceDate, LocalDate applicationDateStart, LocalDate applicationDateEnd, int price) {
        this.name = name;
        this.maintenanceDate = maintenanceDate;
        this.applicationDateStart = applicationDateStart;
        this.applicationDateEnd = applicationDateEnd;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public LocalDate getApplicationDateStart() {
        return applicationDateStart;
    }

    public void setApplicationDateStart(LocalDate applicationDateStart) {
        this.applicationDateStart = applicationDateStart;
    }

    public LocalDate getApplicationDateEnd() {
        return applicationDateEnd;
    }

    public void setApplicationDateEnd(LocalDate applicationDateEnd) {
        this.applicationDateEnd = applicationDateEnd;
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

    public Admin getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Admin createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Admin getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Admin updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ExamEventType getType() {
        return type;
    }

    public void setType(ExamEventType type) {
        this.type = type;
    }

    public List<ApplicationForm> getApplicationForms() {
        return applicationForms;
    }

    public void setApplicationForms(List<ApplicationForm> applicationForms) {
        this.applicationForms = applicationForms;
    }

    @Override
    public String toString() {
        return "ExamEvent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maintenanceDate=" + maintenanceDate +
                ", applicationDateStart=" + applicationDateStart +
                ", applicationDateEnd=" + applicationDateEnd +
                ", price=" + price +
                ", numberOfApplicants=" + numberOfApplicants +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
