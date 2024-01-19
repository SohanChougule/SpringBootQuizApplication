package com.example.quizapp.controller;

import com.example.quizapp.model.Questions;
import com.example.quizapp.service.QuestionService;
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
    public ResponseEntity<List<Questions>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    //  http://localhost:8080/questions/category/Java
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("/get")
    //  http://localhost:8080/questions/get?id=2
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@RequestParam Integer id){
        return questionService.getQuestionsById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Questions> addQuestion(@RequestBody Questions question){
        return questionService.addQuestion(question);
    }



}
