package fon.enrollmentsystembackend.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fon.enrollmentsystembackend.dto.UserDTO;
import fon.enrollmentsystembackend.service.ApplicationFormService;
import fon.enrollmentsystembackend.service.UserService;
import fon.enrollmentsystembackend.view.UserView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @JsonView(UserView.Read.class)
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @JsonView(UserView.ReadDetail.class)
    @GetMapping("/users/{userId}")
    public UserDTO getUserById(@PathVariable Integer userId) {
        Optional<UserDTO> user = userService.getById(userId);

        if (user.isEmpty())
            throw new RuntimeException("User with id " + userId + " not found");

        return user.get();
    }

    @JsonView(UserView.ReadWithApplicationForms.class)
    @GetMapping("/users/{userId}/application-forms")
    public UserDTO getApplicationFormsByUserId(@PathVariable Integer userId) {
        Optional<UserDTO> userWithForms = userService.getApplicationFormsByUserId(userId);

        if (userWithForms.isEmpty())
            throw new RuntimeException("User with id " + userId + " not found");

        return userWithForms.get();
    }

    @JsonView(UserView.Read.class)
    @PostMapping("/users")
    public UserDTO createUser(@Valid @JsonView(UserView.Create.class) @RequestBody UserDTO user) {
        return userService.createUser(user);
    }
}
