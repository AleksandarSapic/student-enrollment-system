package fon.enrollmentsystembackend.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fon.enrollmentsystembackend.dto.ExamEventDTO;
import fon.enrollmentsystembackend.service.ExamEventService;
import fon.enrollmentsystembackend.view.ExamEventView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ExamEventController {

    private final ExamEventService examEventService;

    @Autowired
    public ExamEventController(ExamEventService examEventService) {
        this.examEventService = examEventService;
    }

    @JsonView(ExamEventView.Read.class)
    @GetMapping("/exam-events")
    public List<ExamEventDTO> getAllExamEvents() {
        return examEventService.getAllExamEvents();
    }

    @JsonView({ExamEventView.Read.class})
    @GetMapping("/exam-events/{examEventId}")
    public ExamEventDTO getExamEventById(@PathVariable Integer examEventId) {
        Optional<ExamEventDTO> examEvent = examEventService.getById(examEventId);

        if (examEvent.isEmpty())
            throw new RuntimeException("Exam Event with id " + examEventId + " not found");

        return examEvent.get();
    }

    @JsonView(ExamEventView.ReadWithApplicationForms.class)
    @GetMapping("/exam-events/{examEventId}/application-forms")
    public ExamEventDTO getApplicationFormsByExamEventId(@PathVariable Integer examEventId) {
        Optional<ExamEventDTO> examEvent = examEventService.getApplicationFormsByExamEventId(examEventId);

        if (examEvent.isEmpty())
            throw new RuntimeException("Exam Event with id " + examEventId + " not found");

        return examEvent.get();
    }

    @JsonView(ExamEventView.Read.class)
    @PostMapping("/exam-events")
    public ExamEventDTO createExamEvent(@Valid @JsonView(ExamEventView.Create.class) @RequestBody ExamEventDTO examEvent) {
        return examEventService.createExamEvent(examEvent);
    }
}
