package fon.enrollmentsystembackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exam_event_type")
public class ExamEventType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private List<ExamEvent> examEvents;

    public ExamEventType() {
    }

    public ExamEventType(int id, String name) {
        this.id = id;
        this.name = name;
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

    public List<ExamEvent> getExamEvents() {
        return examEvents;
    }

    public void setExamEvents(List<ExamEvent> examEvents) {
        this.examEvents = examEvents;
    }

    @Override
    public String toString() {
        return "ExamEventType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
