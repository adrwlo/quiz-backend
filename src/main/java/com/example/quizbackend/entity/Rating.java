package com.example.quizbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quizId;
    private int maxPoints;
    private int rating;
    private LocalDateTime dateTime;
}
