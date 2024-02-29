package com.example.quizbackend.repository;

import com.example.quizbackend.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
