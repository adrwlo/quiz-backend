package com.example.quizbackend.service;

import com.example.quizbackend.entity.User;
import com.example.quizbackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> saveUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public String deleteUser(int id) {
        userRepository.deleteById(id);
        return "Deleted user with id " + id;
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setName(user.getName());

        return userRepository.save(existingUser);
    }
}
