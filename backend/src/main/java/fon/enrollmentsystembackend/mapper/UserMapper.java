package fon.enrollmentsystembackend.mapper;

import fon.enrollmentsystembackend.dto.ApplicationFormDTO;
import fon.enrollmentsystembackend.dto.UserDTO;
import fon.enrollmentsystembackend.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDtoForRead(User user) {
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setMiddleName(user.getMiddleName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());

        return dto;
    }

    public static UserDTO toDtoForReadDetail(User user) {
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setMiddleName(user.getMiddleName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPrice(user.getPrice());

        return dto;
    }

    public static UserDTO toDtoForReadWithForms(User user) {
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setMiddleName(user.getMiddleName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());

        List<ApplicationFormDTO> forms = user.getApplicationForms().stream().map(ApplicationFormMapper::toDtoForReadWithUser).collect(Collectors.toList());
        dto.setApplicationForms(forms);

        return dto;
    }

    public static User fromDtoForCreate(UserDTO dto) {
        User user = new User();

        user.setFirstName(dto.getFirstName());
        user.setMiddleName(dto.getMiddleName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }
}
