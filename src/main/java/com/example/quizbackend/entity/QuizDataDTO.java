package com.example.quizbackend.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizDataDTO {
    private String question;
    private AnswerDTO answerA;
    private AnswerDTO answerB;
    private AnswerDTO answerC;
    private AnswerDTO answerD;

    public QuizDataDTO(String questionText, AnswerDTO answerDTO, Object o, Object o1) {
    }

    public QuizDataDTO() {

    }
}
