package com.example.quizapp.service;

import com.example.quizapp.model.Questions;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.QuestionWrapper;
import com.example.quizapp.model.UserResponse;
import com.example.quizapp.repositories.QuestionRepository;
import com.example.quizapp.repositories.QuizRepository;
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
    QuestionRepository questionRepositories;

    public ResponseEntity<String> createQuiz(String category, Integer numOfQuestions, String title) {
        try {
            List<Questions> questions = questionRepositories.findRandomQuestionsByCategory(category,numOfQuestions);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizRepository.save(quiz);
            return new ResponseEntity<>("success", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("BadRequest", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        List<QuestionWrapper> questionForUsers = new ArrayList<>();
        try {
            Optional<Quiz> quiz = quizRepository.findById(id);
            if(quiz.isPresent()){
                List<Questions> questionsFromDB = quiz.get().getQuestions();
                for (Questions q:questionsFromDB) {
                    QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption1(),
                            q.getOption2(),q.getOption3(),q.getOption4());
                    questionForUsers.add(qw);
                }
                return new ResponseEntity<>(questionForUsers, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionForUsers, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<UserResponse> userResponses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Questions> questions = quiz.getQuestions();
        int rightAnswer = 0,i =0;
        for (UserResponse userResponse : userResponses) {
            if (userResponse.getResponse().equals(questions.get(i).getAnswer())){
                rightAnswer++;
            }
            i++;
        }
        return new ResponseEntity<>(rightAnswer,HttpStatus.OK);
    }
}
