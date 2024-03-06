package com.example.quizbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestionDTO {
    private String question;
    private AnswerDTO answerA;
    private AnswerDTO answerB;
    private AnswerDTO answerC;
    private AnswerDTO answerD;
}
