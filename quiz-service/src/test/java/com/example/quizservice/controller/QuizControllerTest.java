package com.example.quizservice.controller;

import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(QuizController.class)
//@RunWith(SpringRunner.class)
class QuizControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuizService quizService;


    private ResponseEntity<List<QuestionWrapper>> questionWrapperList;

    @BeforeEach
    void setUp() {

        questionWrapperList =
                new ResponseEntity<>(Arrays.asList(QuestionWrapper.builder().id(1).question("Mock Question").option1("Option 1")
                                .option2("option 2").option3("option 3")
                                .option4("option 4").build(),
                        QuestionWrapper.builder().id(2).question("Mock Question 2").option1("Option 1")
                                .option2("option 2").option3("option 3")
                                .option4("option 4").build()), HttpStatus.OK);
    }

    @Test
    void createQuiz() {
    }

    @Test
    void getQuiz() throws Exception {
        Integer id = 1;

        Mockito.when(quizService.getQuizQuestions(id)).thenReturn(questionWrapperList);

        mockMvc.perform(MockMvcRequestBuilders.get("/quiz/get/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        //       .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(questionWrapperList.getBody().get(0).getId()));


        /*
        mockMvc.perform(MockMvcRequestBuilders.post("/quiz/get/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\n" +
                        "    {\n" +
                        "        \"id\": 5,\n" +
                        "        \"question\": \"Which collection is ordered, changeable, and allows duplicate members?\",\n" +
                        "        \"option1\": \"LIST\",\n" +
                        "        \"option2\": \"TUPLE\",\n" +
                        "        \"option3\": \"DICTIONARY\",\n" +
                        "        \"option4\": \"SET\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 3,\n" +
                        "        \"question\": \"What is the correct file extension for Python files?\",\n" +
                        "        \"option1\": \".py\",\n" +
                        "        \"option2\": \".python\\n\",\n" +
                        "        \"option3\": \".pyt\",\n" +
                        "        \"option4\": \".pt\"\n" +
                        "    }\n" +
                        "]")).andExpect(MockMvcResultMatchers.status().isOk());*/
    }

    @Test
    void submitQuiz() {
    }
}