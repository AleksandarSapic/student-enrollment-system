package fon.enrollmentsystembackend.mapper;

import fon.enrollmentsystembackend.dto.ApplicationFormDTO;
import fon.enrollmentsystembackend.dto.ExamEventDTO;
import fon.enrollmentsystembackend.dto.UserDTO;
import fon.enrollmentsystembackend.model.ApplicationForm;
import fon.enrollmentsystembackend.model.ExamEvent;
import fon.enrollmentsystembackend.model.User;

import java.util.Optional;

public class ApplicationFormMapper {

    public static ApplicationFormDTO toDtoForReadWithExamEvent(ApplicationForm applicationForm) {
        ApplicationFormDTO dto = new ApplicationFormDTO();
        UserDTO user = UserMapper.toDtoForRead(applicationForm.getUser());

        dto.setUser(user);
        dto.setApplicationNumber(applicationForm.getApplicationNumber());

        return dto;
    }

    public static ApplicationFormDTO toDtoForReadWithUser(ApplicationForm applicationForm) {
        ApplicationFormDTO dto = new ApplicationFormDTO();
        Optional<ExamEventDTO> examEvent = ExamEventMapper.toDtoForRead(applicationForm.getExamEvent());

        if (examEvent.isEmpty())
            throw new IllegalArgumentException("Exam event cannot be empty");

        dto.setExamEvent(examEvent.get());
        dto.setApplicationNumber(applicationForm.getApplicationNumber());

        return dto;
    }

    public static ApplicationForm fromDtoForCreate(ApplicationFormDTO dto, ExamEvent examEvent, User user) {
        ApplicationForm applicationForm = new ApplicationForm();

        applicationForm.setExamEvent(examEvent);
        applicationForm.setUser(user);

        return applicationForm;
    }

    public static ApplicationFormDTO toDtoForRead(ApplicationForm applicationForm) {
        ApplicationFormDTO dto = new ApplicationFormDTO();

        dto.setUser(UserMapper.toDtoForRead(applicationForm.getUser()));
        dto.setExamEvent(ExamEventMapper.toDtoForRead(applicationForm.getExamEvent()).orElseThrow(IllegalArgumentException::new));
        dto.setApplicationNumber(applicationForm.getApplicationNumber());

        return dto;
    }
}
