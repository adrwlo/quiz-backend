package com.example.quizbackend.service;

import com.example.quizbackend.entity.*;
import com.example.quizbackend.repository.QuizRepository;
import com.example.quizbackend.repository.RatingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Transactional
    public List<RatingDTO> getAllRatings() {
        List<RatingDTO> ratingDTOList = new ArrayList<>();
        List<Rating> ratings = ratingRepository.findAll();

        for (Rating rating : ratings) {
            RatingDTO ratingDTO = new RatingDTO();
            ratingDTO.setId(rating.getId());
            ratingDTO.setQuizId(rating.getQuizId());
            ratingDTO.setMaxPoints(rating.getMaxPoints());
            ratingDTO.setRating(rating.getRating());
            ratingDTO.setDateTime(rating.getDateTime());

            Quiz quiz = quizRepository.findById(rating.getQuizId()).orElse(null);
            if (quiz != null) {
                ratingDTO.setQuizTitle(quiz.getTitle());
            }

            ratingDTOList.add(ratingDTO);
        }

        return ratingDTOList;
    }

    public void addRating(RatingDTO ratingDTO) {
        Quiz existingQuiz = quizRepository.findById(ratingDTO.getQuizId()).orElse(null);

        if (existingQuiz != null) {
            try {
                Rating rating = new Rating();
                rating.setQuizId(ratingDTO.getQuizId());
                rating.setMaxPoints(ratingDTO.getMaxPoints());
                rating.setRating(ratingDTO.getRating());
                rating.setDateTime(LocalDateTime.now());
                System.out.println(ratingDTO);
                ratingRepository.save(rating);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error adding rating: " + e.getMessage(), e);
            }
        } else {
            throw new IllegalArgumentException("Quiz with ID " + ratingDTO.getQuizId() + " does not exist.");
        }
    }
}
