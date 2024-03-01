package com.example.quizbackend.repository;

import com.example.quizbackend.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {}
