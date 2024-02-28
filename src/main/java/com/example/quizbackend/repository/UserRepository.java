package com.example.quizbackend.repository;

import com.example.quizbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByName(String name);
}
