package com.example.quizbackend.controller;

import com.example.quizbackend.entity.QuizDataDTO;
import com.example.quizbackend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> addQuiz(@RequestBody QuizDataDTO quizDataDTO) {
        try {
            quizService.addQuiz(quizDataDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the quiz.");
        }
    }

    @DeleteMapping("deleteQuiz/{title}")
    public Void deleteQuiz(@PathVariable String title) {
        quizService.deleteQuiz(title);
        return null;
    }
}
