package com.example.quizbackend.service;

import com.example.quizbackend.entity.*;
import com.example.quizbackend.repository.QuizRepository;
import com.example.quizbackend.repository.RatingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Transactional
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public void addRating(RatingDTO ratingDTO) {
        Quiz existingQuiz = quizRepository.findById(ratingDTO.getQuizId()).orElse(null);

        if (existingQuiz != null) {
            try {
                Rating rating = new Rating();
                rating.setQuizId(ratingDTO.getQuizId());
                rating.setMaxPoints(ratingDTO.getQuizId());
                rating.setRating(ratingDTO.getRating());
                rating.setDateTime(LocalDateTime.now());

                ratingRepository.save(rating);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error adding rating: " + e.getMessage(), e);
            }
        } else {
            throw new IllegalArgumentException("Quiz with ID " + ratingDTO.getQuizId() + " does not exist.");
        }
    }
}
