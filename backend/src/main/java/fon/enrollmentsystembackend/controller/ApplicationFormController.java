package fon.enrollmentsystembackend.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fon.enrollmentsystembackend.dto.ApplicationFormDTO;
import fon.enrollmentsystembackend.service.ApplicationFormService;
import fon.enrollmentsystembackend.view.ApplicationFormView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ApplicationFormController {

    private final ApplicationFormService applicationFormService;

    @Autowired
    public ApplicationFormController(ApplicationFormService applicationFormService) {
        this.applicationFormService = applicationFormService;
    }

    @JsonView(ApplicationFormView.Read.class)
    @PostMapping("/application-forms")
    public ApplicationFormDTO createApplicationForm(@Valid @JsonView(ApplicationFormView.Create.class) @RequestBody ApplicationFormDTO applicationForm) {
        return applicationFormService.createApplicationForm(applicationForm);
    }
}
