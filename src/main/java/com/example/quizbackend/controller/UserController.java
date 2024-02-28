package com.example.quizbackend.controller;

import com.example.quizbackend.entity.User;
import com.example.quizbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/addUsers")
    public List<User> addUsers(@RequestBody List<User> users) {
        return userService.saveUsers(users);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
