package fon.enrollmentsystembackend.controller;

import fon.enrollmentsystembackend.model.ExamEventType;
import fon.enrollmentsystembackend.service.ExamEventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ExamEventTypeController {
    private final ExamEventTypeService examEventTypeService;

    @Autowired
    public ExamEventTypeController(ExamEventTypeService examEventTypeService) {
        this.examEventTypeService = examEventTypeService;
    }

    @GetMapping("/exam-event-types")
    public List<ExamEventType> getAllExamEventTypes() {
        return examEventTypeService.getAllExamEventTypes();
    }
}
