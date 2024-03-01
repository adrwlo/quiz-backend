package com.example.quizbackend.repository;

import com.example.quizbackend.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer>  {
    List<Question> findAllById(int id);
}
