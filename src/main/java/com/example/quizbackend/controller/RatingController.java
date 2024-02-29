package com.example.quizbackend.controller;

import com.example.quizbackend.entity.RatingDTO;
import com.example.quizbackend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @GetMapping("/getRatings")
    public List<RatingDTO> getRatings() {
        return ratingService.getAllRatings();
    }
}
