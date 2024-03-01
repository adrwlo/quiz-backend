package com.example.quizbackend.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizDTO {
    private String title;
    private List<QuizDataDTO> quizData;
}
