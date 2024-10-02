package fon.enrollmentsystembackend.mapper;

import fon.enrollmentsystembackend.dto.ApplicationFormDTO;
import fon.enrollmentsystembackend.dto.ExamEventDTO;
import fon.enrollmentsystembackend.model.Admin;
import fon.enrollmentsystembackend.model.ExamEvent;
import fon.enrollmentsystembackend.model.ExamEventType;
import fon.enrollmentsystembackend.util.DateRange;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ExamEventMapper {

    public static Optional<ExamEventDTO> toDtoForRead(ExamEvent examEvent) {
        return Optional.ofNullable(examEvent).map(event -> {
            ExamEventDTO dto = new ExamEventDTO();

            dto.setId(event.getId());
            dto.setExamEventName(event.getName());
            dto.setMaintenanceDate(event.getMaintenanceDate());

            DateRange dateRange = new DateRange(examEvent.getApplicationDateStart(), examEvent.getApplicationDateEnd());
            dto.setApplicationDateRange(dateRange);
            dto.setPrice(event.getPrice());
            dto.setNumberOfApplicants(event.getNumberOfApplicants());
            dto.setTypeName(event.getType().getName());

            return dto;
        });
    }

    public static Optional<ExamEventDTO> toDtoForReadWithApplicationForms(ExamEvent examEvent) {
        return Optional.ofNullable(examEvent).map(event -> {
            ExamEventDTO dto = new ExamEventDTO();

            dto.setId(event.getId());
            dto.setExamEventName(event.getName());
            dto.setMaintenanceDate(event.getMaintenanceDate());

            DateRange dateRange = new DateRange(examEvent.getApplicationDateStart(), examEvent.getApplicationDateEnd());
            dto.setApplicationDateRange(dateRange);
            dto.setPrice(event.getPrice());
            dto.setNumberOfApplicants(event.getNumberOfApplicants());
            dto.setTypeName(event.getType().getName());

            List<ApplicationFormDTO> forms = event.getApplicationForms().stream().map(ApplicationFormMapper::toDtoForReadWithExamEvent).collect(Collectors.toList());
            dto.setApplicationForms(forms);

            return dto;
        });
    }

    public static Optional<ExamEvent> fromDtoForCreate(ExamEventDTO examEventDTO, Admin creator, ExamEventType type) {
        return Optional.ofNullable(examEventDTO).map(dto -> {
            ExamEvent examEvent = new ExamEvent();

            examEvent.setName(dto.getExamEventName());
            examEvent.setMaintenanceDate(dto.getMaintenanceDate());
            examEvent.setApplicationDateStart(dto.getApplicationDateRange().getStartDate());
            examEvent.setApplicationDateEnd(dto.getApplicationDateRange().getEndDate());
            examEvent.setPrice(dto.getPrice());
            examEvent.setType(type);
            examEvent.setCreatedBy(creator);
            examEvent.setUpdatedBy(creator);

            return examEvent;
        });
    }
}
