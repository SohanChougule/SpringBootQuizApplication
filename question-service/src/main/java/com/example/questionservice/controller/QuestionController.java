package com.example.questionservice.controller;

import com.example.questionservice.model.QuestionWrapper;
import com.example.questionservice.model.Questions;
import com.example.questionservice.model.UserResponse;
import com.example.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/allquestions")
    public ResponseEntity<List<Questions>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    //  http://localhost:8080/questions/category/Java
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("/get")
    //  http://localhost:8080/questions/get?id=2
    public ResponseEntity<List<Questions>> getQuestionsById(@RequestParam Integer id) {
        return questionService.getQuestionsById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Questions> addQuestion(@RequestBody Questions question) {
        return questionService.addQuestion(question);
    }

    @GetMapping("/generate")
    //  http://localhost:8080/questions/generate?category=Java&numOfQuestions=2
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numOfQuestions) {
        return questionService.getQuestionsForQuiz(category,numOfQuestions);
    }

    @PostMapping("/get")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds) {
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<UserResponse> userResponses) {
        return questionService.getScore(userResponses);
    }

}
