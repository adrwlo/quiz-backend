package com.example.quizbackend.service;

import com.example.quizbackend.entity.*;
import com.example.quizbackend.repository.AnswerRepository;
import com.example.quizbackend.repository.QuestionRepository;
import com.example.quizbackend.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public List<QuizDataDTO> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream()
                .map(this::mapToQuizDataDTO)
                .collect(Collectors.toList());
    }

    private QuizDataDTO mapToQuizDataDTO(Quiz quiz) {
        QuizDataDTO quizDataDTO = new QuizDataDTO();
        quizDataDTO.setQuestion(quiz.getTitle());

        List<Question> questions = questionRepository.findAllById(quiz.getId());
        if (!questions.isEmpty()) {
            quizDataDTO.setAnswerA(new AnswerDTO(questions.get(0).getQuestionText(), true));
            if (questions.size() > 1) {
                quizDataDTO.setAnswerB(new AnswerDTO(questions.get(1).getQuestionText(), true));
            }
            if (questions.size() > 2) {
                quizDataDTO.setAnswerC(new AnswerDTO(questions.get(2).getQuestionText(), true));
            }
            if (questions.size() > 3) {
                quizDataDTO.setAnswerD(new AnswerDTO(questions.get(3).getQuestionText(), true));
            }
        }

        return quizDataDTO;
    }
}