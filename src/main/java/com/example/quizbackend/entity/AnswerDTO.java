package com.example.quizbackend.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {
    private String answer;
    private boolean correctAnswer;

    public AnswerDTO(String answerText, boolean correct) {
    }
}
