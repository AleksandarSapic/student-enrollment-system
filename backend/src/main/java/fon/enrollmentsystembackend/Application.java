package fon.enrollmentsystembackend;

import fon.enrollmentsystembackend.controller.ApplicationFormController;
import fon.enrollmentsystembackend.controller.ExamEventController;
import fon.enrollmentsystembackend.controller.UserController;
import fon.enrollmentsystembackend.model.Admin;
import fon.enrollmentsystembackend.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
