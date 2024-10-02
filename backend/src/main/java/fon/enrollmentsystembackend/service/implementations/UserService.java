package fon.enrollmentsystembackend.service.implementations;

import fon.enrollmentsystembackend.dto.UserDTO;
import fon.enrollmentsystembackend.mapper.UserMapper;
import fon.enrollmentsystembackend.model.User;
import fon.enrollmentsystembackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements fon.enrollmentsystembackend.service.UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream().map(UserMapper::toDtoForRead).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getById(Integer id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(UserMapper::toDtoForReadDetail);
    }

    public Optional<UserDTO> getApplicationFormsByUserId(Integer id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(UserMapper::toDtoForReadWithForms);
    }

    @Transactional
    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = UserMapper.fromDtoForCreate(dto);
        User savedUser = userRepository.save(user);

        return UserMapper.toDtoForRead(savedUser);
    }
}
