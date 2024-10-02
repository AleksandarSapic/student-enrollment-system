package fon.enrollmentsystembackend.service.implementations;

import fon.enrollmentsystembackend.dto.ApplicationFormDTO;
import fon.enrollmentsystembackend.mapper.ApplicationFormMapper;
import fon.enrollmentsystembackend.model.ApplicationForm;
import fon.enrollmentsystembackend.model.ExamEvent;
import fon.enrollmentsystembackend.model.User;
import fon.enrollmentsystembackend.repository.ApplicationFormRepository;
import fon.enrollmentsystembackend.repository.ExamEventRepository;
import fon.enrollmentsystembackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationFormService implements fon.enrollmentsystembackend.service.ApplicationFormService {
    private final ApplicationFormRepository applicationFormRepository;
    private final ExamEventRepository examEventRepository;
    private final UserRepository userRepository;

    @Autowired
    public ApplicationFormService(ApplicationFormRepository applicationFormRepository, ExamEventRepository examEventRepository, UserRepository userRepository) {
        this.applicationFormRepository = applicationFormRepository;
        this.examEventRepository = examEventRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public ApplicationFormDTO createApplicationForm(ApplicationFormDTO dto) {
        Optional<ExamEvent> examEventOpt = examEventRepository.findById(dto.getExamEventId());
        if (examEventOpt.isEmpty())
            throw new RuntimeException("Exam event with id " + dto.getExamEventId() + " not found");

        Optional<User> userOpt = userRepository.findById(dto.getUserId());
        if (userOpt.isEmpty())
            throw new RuntimeException("User with id " + dto.getUserId() + " not found");

        ExamEvent examEvent = examEventOpt.get();
        User user = userOpt.get();

        // Validate if application is possible based on current date and exam event dates
        validateApplicationPeriod(examEvent);

        updatePriceForUser(user, examEvent);
        userRepository.save(user);

        updateNumberOfApplicantsForExamEvent(examEvent);
        examEventRepository.save(examEvent);

        ApplicationForm applicationForm = ApplicationFormMapper.fromDtoForCreate(dto, examEvent, user);
        applicationFormRepository.save(applicationForm);

        return ApplicationFormMapper.toDtoForRead(applicationForm);
    }

    private void updateNumberOfApplicantsForExamEvent(ExamEvent examEvent) {
        examEvent.setNumberOfApplicants(examEvent.getNumberOfApplicants() + 1);
    }

    private void updatePriceForUser(User user, ExamEvent examEvent) {
        user.setPrice(user.getPrice() + examEvent.getPrice());

        //Sta ako se student prijavljuje na oba termina matematike
        List<ApplicationForm> applicationForms = user.getApplicationForms();
        for (ApplicationForm form : applicationForms) {
            if (!form.getExamEvent().getType().getName().equals(examEvent.getType().getName())) {
                user.setPrice(user.getPrice() - 500);
                break;
            }
        }
    }

    private void validateApplicationPeriod(ExamEvent examEvent) {
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        if (now.isBefore(examEvent.getApplicationDateStart()) || now.isAfter(examEvent.getApplicationDateEnd()))
            throw new RuntimeException("Application is not possible. The current date is outside the application period for this exam event.");
    }
}
