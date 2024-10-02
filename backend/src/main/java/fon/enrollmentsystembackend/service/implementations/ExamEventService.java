package fon.enrollmentsystembackend.service.implementations;

import fon.enrollmentsystembackend.dto.ExamEventDTO;
import fon.enrollmentsystembackend.mapper.ExamEventMapper;
import fon.enrollmentsystembackend.model.Admin;
import fon.enrollmentsystembackend.model.ExamEvent;
import fon.enrollmentsystembackend.model.ExamEventType;
import fon.enrollmentsystembackend.repository.AdminRepository;
import fon.enrollmentsystembackend.repository.ExamEventRepository;
import fon.enrollmentsystembackend.repository.ExamEventTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamEventService implements fon.enrollmentsystembackend.service.ExamEventService {

    private final ExamEventRepository examEventRepository;
    private final AdminRepository adminRepository;
    private final ExamEventTypeRepository examEventTypeRepository;

    @Autowired
    public ExamEventService(ExamEventRepository examEventRepository, AdminRepository adminRepository, ExamEventTypeRepository examEventTypeRepository) {
        this.examEventRepository = examEventRepository;
        this.adminRepository = adminRepository;
        this.examEventTypeRepository = examEventTypeRepository;
    }

    @Override
    public List<ExamEventDTO> getAllExamEvents() {
        List<ExamEvent> allEvents = examEventRepository.findAll();

        return allEvents.stream()
                .map(event -> ExamEventMapper.toDtoForRead(event)
                        .orElseThrow(() -> new RuntimeException("Mapping failed")))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ExamEventDTO> getApplicationFormsByExamEventId(Integer examEventId) {
        Optional<ExamEvent> examEvent = examEventRepository.findById(examEventId);

        return examEvent.flatMap(ExamEventMapper::toDtoForReadWithApplicationForms);
    }

    @Override
    public Optional<ExamEventDTO> getById(Integer id) {
        Optional<ExamEvent> examEvent = examEventRepository.findById(id);

        return examEvent.flatMap(ExamEventMapper::toDtoForRead);
    }

    @Transactional
    @Override
    public ExamEventDTO createExamEvent(ExamEventDTO dto) {
//        Checks if maintenance date is after application period
        validateDates(dto);

        Optional<Admin> creator = adminRepository.findById(dto.getCreatedById());

        if (creator.isEmpty())
            throw new RuntimeException("Admin with id " + dto.getCreatedById() + " not found");

        Optional<ExamEventType> type = examEventTypeRepository.findById(dto.getTypeId());

        if (type.isEmpty())
            throw new RuntimeException("Type with id " + dto.getTypeId() + " not found");

        ExamEvent examEvent = ExamEventMapper.fromDtoForCreate(dto, creator.get(), type.get())
                .orElseThrow(() -> new RuntimeException("Mapping failed"));

        ExamEvent savedExamEvent = examEventRepository.save(examEvent);

        return ExamEventMapper.toDtoForRead(savedExamEvent)
                .orElseThrow(() -> new RuntimeException("Mapping failed"));
    }

    private void validateDates(ExamEventDTO dto) {
        if (dto.getMaintenanceDate().isBefore(dto.getApplicationDateRange().getEndDate()))
            throw new RuntimeException("Maintenance date cannot be before application period");
    }
}
