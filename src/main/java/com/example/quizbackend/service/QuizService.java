package com.example.quizbackend.service;

import com.example.quizbackend.entity.*;
import com.example.quizbackend.repository.AnswerRepository;
import com.example.quizbackend.repository.QuestionRepository;
import com.example.quizbackend.repository.QuizRepository;
import com.example.quizbackend.repository.RatingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public List<QuizDataDTO> getAllQuizzes() {
        List<QuizDataDTO> quizList = new ArrayList<>();
        List<Quiz> quizzes = quizRepository.findAll();

        for (Quiz quiz : quizzes) {
            List<Question> questions = questionRepository.findByQuizId(quiz.getId());
            List<QuizQuestionDTO> quizQuestions = new ArrayList<>();

            for (Question question : questions) {
                List<Answer> answers = answerRepository.findByQuestionId(question.getId());
                List<AnswerDTO> answerDTOs = new ArrayList<>();

                for (Answer answer : answers) {
                    AnswerDTO answerDTO = new AnswerDTO();
                    answerDTO.setAnswer(answer.getAnswerText());
                    answerDTO.setCorrectAnswer(answer.isCorrect());
                    answerDTOs.add(answerDTO);
                }

                QuizQuestionDTO quizQuestionDTO = new QuizQuestionDTO();
                quizQuestionDTO.setQuestion(question.getQuestionText());

                for (int i = 0; i < Math.min(answerDTOs.size(), 4); i++) {
                    switch (i) {
                        case 0:
                            quizQuestionDTO.setAnswerA(answerDTOs.get(i));
                            break;
                        case 1:
                            quizQuestionDTO.setAnswerB(answerDTOs.get(i));
                            break;
                        case 2:
                            quizQuestionDTO.setAnswerC(answerDTOs.get(i));
                            break;
                        case 3:
                            quizQuestionDTO.setAnswerD(answerDTOs.get(i));
                            break;
                    }
                }
                quizQuestions.add(quizQuestionDTO);
            }

            QuizDataDTO quizDataDTO = new QuizDataDTO();
            quizDataDTO.setTitle(quiz.getTitle());
            quizDataDTO.setQuizQuestionDTOs(quizQuestions);
            quizList.add(quizDataDTO);
        }
        return quizList;
    }

    public void addQuiz(QuizDataDTO quizDataDTO) {
        try {
            String title = quizDataDTO.getTitle();

            if (quizRepository.existsByTitle(title)) {
                throw new IllegalArgumentException("Quiz with title '" + title + "' already exists.");
            }

            Quiz quiz = new Quiz();
            quiz.setTitle(quizDataDTO.getTitle());
            quiz = quizRepository.save(quiz);

            for (QuizQuestionDTO quizQuestionDTO : quizDataDTO.getQuizQuestionDTOs()) {
                Question question = new Question();
                question.setQuizId(quiz.getId());
                question.setQuestionText(quizQuestionDTO.getQuestion());
                question = questionRepository.save(question);

                Answer answerA = createAnswer(quizQuestionDTO.getAnswerA(), question.getId());
                Answer answerB = createAnswer(quizQuestionDTO.getAnswerB(), question.getId());
                Answer answerC = createAnswer(quizQuestionDTO.getAnswerC(), question.getId());
                Answer answerD = createAnswer(quizQuestionDTO.getAnswerD(), question.getId());

                answerRepository.save(answerA);
                answerRepository.save(answerB);
                answerRepository.save(answerC);
                answerRepository.save(answerD);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error adding quiz: " + e.getMessage(), e);
        }
    }

    private Answer createAnswer(AnswerDTO answerDTO, int questionId) {
        Answer answer = new Answer();
        answer.setQuestionId(questionId);
        answer.setAnswerText(answerDTO.getAnswer());
        answer.setCorrect(answerDTO.isCorrectAnswer());
        return answer;
    }

    @Transactional
    public void deleteQuiz(String title) {
        try {
            List<Quiz> quizzes = quizRepository.findByTitle(title);
            System.out.println(quizzes);

            if (!quizzes.isEmpty()) {
                int quizId = quizzes.get(0).getId();

                System.out.println(quizId);;

                List<Rating> ratings = ratingRepository.findAllById(Collections.singleton(quizId));
                ratingRepository.deleteAll(ratings);

                List<Question> questions = questionRepository.findByQuizId(quizId);
                for (Question question : questions) {
                    List<Answer> answers = answerRepository.findByQuestionId(question.getId());
                    answerRepository.deleteAll(answers);
                }

                questionRepository.deleteAll(questions);
                quizRepository.deleteById(quizId);
            } else {
                throw new IllegalArgumentException("Quiz with title '" + title + "' not exists.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting quiz: " + e.getMessage(), e);
        }
    }
}
