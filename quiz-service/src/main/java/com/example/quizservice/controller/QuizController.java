package com.example.quizservice.controller;

import com.example.quizservice.error.QuizNotFoundException;
import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.QuizDto;
import com.example.quizservice.model.UserResponse;
import com.example.quizservice.service.QuizService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private final Logger logger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        logger.info("inside createQuiz of QuizController");
        return quizService.createQuiz(quizDto.getCategory(),quizDto.getNumOfQuestions(),quizDto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id)
            throws QuizNotFoundException {
       // logger.info("inside getQuiz of QuizController");
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<UserResponse> userResponse){
        logger.info("inside submitQuiz of QuizController");
        return quizService.calculateResult(userResponse);
    }
}
