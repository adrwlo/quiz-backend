package com.example.quizbackend.repository;

import com.example.quizbackend.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    boolean existsByTitle(String title);
    void deleteByTitle(String title);
    List<Quiz> findByTitle(String title);
}
