package com.example.quizbackend.mock;

import com.example.quizbackend.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class MockData implements CommandLineRunner {
    private final QuizRepository quizRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}
