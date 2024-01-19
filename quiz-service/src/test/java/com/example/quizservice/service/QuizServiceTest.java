package com.example.quizservice.service;

import com.example.quizservice.feign.QuizInterface;
import com.example.quizservice.model.QuestionWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuizServiceTest {

    @MockBean
    QuizInterface quizInterface;

    @BeforeEach
    void setUp() {
        ResponseEntity<List<QuestionWrapper>> questionWrappers =
                new ResponseEntity (Arrays.asList(QuestionWrapper.builder().id(1)
                        .question("Mock Question").option1("Option 1")
                        .option2("option 2").option3("option 3")
                        .option4("option 4").build()), HttpStatus.OK);

        Mockito.when(quizInterface.getQuestionsFromIds(Arrays.asList(1))).thenReturn(questionWrappers);

    }

    @Test
    void createQuiz() {
    }

    @Test
    public void getQuizQuestions() {
        List<Integer> questionIds = Arrays.asList(1);
        List<QuestionWrapper> questionWrappers = quizInterface.getQuestionsFromIds(questionIds).getBody();
        System.out.println(questionWrappers);
        assertEquals(questionIds.get(0), questionWrappers.get(0).getId());
    }

    @Test
    void calculateResult() {
    }
}