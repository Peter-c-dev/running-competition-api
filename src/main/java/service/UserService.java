package service;


import org.example.runcomp.dto.CreateUserRequest;
import org.example.runcomp.dto.UserDTO;
import org.example.runcomp.model.User;
import org.example.runcomp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.name());

        User saved = userRepository.save(user);

        return new UserDTO(saved.getId(), saved.getName());
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserDTO(u.getId(), u.getName()))
                .toList();
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(u -> new UserDTO(u.getId(), u.getName()))
                .orElse(null);
    }
}
