package org.example.runcomp.controller;

import org.example.runcomp.model.User;
import org.example.runcomp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // ➤ CREATE a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // ➤ CREATE multiple users at once
    @PostMapping("/batch")
    public ResponseEntity<List<User>> createUsersBatch(@RequestBody List<User> users) {
        List<User> savedUsers = userRepository.saveAll(users);
        return ResponseEntity.ok(savedUsers);
    }

    // ➤ GET all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ➤ GET one user
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
