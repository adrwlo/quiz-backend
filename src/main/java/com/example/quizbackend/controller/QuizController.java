package com.example.quizbackend.controller;

import com.example.quizbackend.entity.QuizDataDTO;
import com.example.quizbackend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping("/getQuizzes")
    public ResponseEntity<List<QuizDataDTO>> getAllQuizzesWithQuestionsAndAnswers() {
        List<QuizDataDTO> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @PostMapping("/addQuiz")
    public Void addQuiz(@RequestBody QuizDataDTO quizDataDTO) {
        quizService.addQuiz(quizDataDTO);
        return null;
    }

    @DeleteMapping("deleteQuiz/{title}")
    public Void deleteQuiz(@PathVariable String title) {
        quizService.deleteQuiz(title);
        return null;
    }
}
