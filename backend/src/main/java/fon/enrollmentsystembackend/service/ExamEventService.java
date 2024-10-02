package fon.enrollmentsystembackend.service;

import fon.enrollmentsystembackend.dto.ExamEventDTO;

import java.util.List;
import java.util.Optional;

public interface ExamEventService {
    List<ExamEventDTO> getAllExamEvents();

    Optional<ExamEventDTO> getApplicationFormsByExamEventId(Integer examEventId);

    Optional<ExamEventDTO> getById(Integer id);

    ExamEventDTO createExamEvent(ExamEventDTO dto);
}
