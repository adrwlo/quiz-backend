package com.example.quizbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingAddDTO {
    private int id;
    private int quizId;
    private String quizTitle;
    private int maxPoints;
    private int rating;
    private LocalDateTime dateTime;
}
