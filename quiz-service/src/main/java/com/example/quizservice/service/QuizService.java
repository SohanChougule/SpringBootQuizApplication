package com.example.quizservice.service;

import com.example.quizservice.error.QuizNotFoundException;
import com.example.quizservice.feign.QuizInterface;
import com.example.quizservice.model.*;
import com.example.quizservice.repositories.QuizRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, Integer numOfQuestions, String title) {
        try {
            List<Integer> questionIds = quizInterface.getQuestionsForQuiz(category, numOfQuestions).getBody();
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionIds(questionIds);
            quizRepository.save(quiz);
            return new ResponseEntity<>("success", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("BadRequest", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) throws QuizNotFoundException {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isPresent()) {
            List<Integer> questionIds = quiz.get().getQuestionIds();
            ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromIds(questionIds);
            return questions;
        } else {
            throw new QuizNotFoundException("Quiz not available");
        }

        // return new ResponseEntity<>(questionForUsers, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> calculateResult(List<UserResponse> userResponses) {
        ResponseEntity<Integer> score = quizInterface.getScore(userResponses);
        return score;
    }
}
