package com.example.quizbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDataDTO {
    private Integer id;
    private String title;
    private List<QuizQuestionDTO> quizQuestionDTOs;
}
