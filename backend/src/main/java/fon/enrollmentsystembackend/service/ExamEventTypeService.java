package fon.enrollmentsystembackend.service;

import fon.enrollmentsystembackend.model.ExamEventType;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExamEventTypeService {
    List<ExamEventType> getAllExamEventTypes();
}
