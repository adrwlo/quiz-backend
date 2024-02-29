package com.example.quizbackend.service;

import com.example.quizbackend.entity.Rating;
import com.example.quizbackend.entity.RatingDTO;
import com.example.quizbackend.entity.User;
import com.example.quizbackend.repository.RatingRepository;
import com.example.quizbackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<RatingDTO> getAllRatings() {
        return ratingRepository.findAll().stream()
                .map(this::mapToRatingDTO)
                .collect(Collectors.toList());
    }

    private RatingDTO mapToRatingDTO(Rating rating) {
        User user = userRepository.findById(rating.getUserId()).orElseThrow();

        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setName(user.getName());
        ratingDTO.setRating(rating.getRating());
        return ratingDTO;
    }
}
