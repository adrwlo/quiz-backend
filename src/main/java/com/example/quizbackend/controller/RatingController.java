package com.example.quizbackend.controller;

import com.example.quizbackend.entity.Rating;
import com.example.quizbackend.entity.RatingDTO;
import com.example.quizbackend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @GetMapping("/getRatings")
    public List<Rating> getRatings() {
        return ratingService.getAllRatings();
    }

    @PostMapping("/addRating")
    public ResponseEntity<String> addRating(@RequestBody RatingDTO ratingDTO) {
        try {
            ratingService.addRating(ratingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the rating.");
        }
    }
}
