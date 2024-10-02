package fon.enrollmentsystembackend.service.implementations;

import fon.enrollmentsystembackend.model.ExamEventType;
import fon.enrollmentsystembackend.repository.ExamEventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamEventTypeService implements fon.enrollmentsystembackend.service.ExamEventTypeService {

    private final ExamEventTypeRepository examEventTypeRepository;

    @Autowired
    public ExamEventTypeService(ExamEventTypeRepository examEventTypeRepository) {
        this.examEventTypeRepository = examEventTypeRepository;
    }

    @Override
    public List<ExamEventType> getAllExamEventTypes() {
        return examEventTypeRepository.findAll();
    }
}
