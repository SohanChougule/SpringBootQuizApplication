package com.example.quizservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {
    @Id
    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    //private String answer;

}
