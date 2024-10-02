package fon.enrollmentsystembackend.service;

import fon.enrollmentsystembackend.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAllUsers();

    Optional<UserDTO> getById(Integer id);

    UserDTO createUser(UserDTO dto);

    Optional<UserDTO> getApplicationFormsByUserId(Integer userId);
}
