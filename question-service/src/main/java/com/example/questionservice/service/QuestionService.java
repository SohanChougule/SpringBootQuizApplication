package com.example.questionservice.service;

import com.example.questionservice.model.QuestionWrapper;
import com.example.questionservice.model.Questions;
import com.example.questionservice.model.UserResponse;
import com.example.questionservice.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<Questions>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findAllByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Questions>> getQuestionsById(Integer id) {
        try {
            return new ResponseEntity<>(questionRepository.findAllById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Questions> addQuestion(Questions questions) {
        try {
            return new ResponseEntity<>(questionRepository.save(questions), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Questions(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numOfQuestions) {
        List<Integer> questions = questionRepository.getQuestionsForQuiz(category, numOfQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> questionForUsers = new ArrayList<>();
        List<Questions> questionsFromDB = new ArrayList<>();
        try {
            for (Integer q : questionIds) {
                Questions question = questionRepository.findById(q).get();
                QuestionWrapper qw = new QuestionWrapper(question.getId(), question.getQuestion(), question.getOption1(),
                        question.getOption2(), question.getOption3(), question.getOption4());
                questionForUsers.add(qw);
            }
            return new ResponseEntity<>(questionForUsers, HttpStatus.OK);
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionForUsers, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> getScore(List<UserResponse> userResponses) {
        int rightAnswer = 0,i =0;
        for (UserResponse userResponse : userResponses) {
            Questions question = questionRepository.findById(userResponse.getId()).get();
            if (userResponse.getResponse().equals(question.getAnswer())){
                rightAnswer++;
            }
            i++;
        }
        return new ResponseEntity<>(rightAnswer,HttpStatus.OK);
    }
}