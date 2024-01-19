package com.example.questionservice.repositories;

import com.example.questionservice.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Integer> {

    List<Questions> findAllByCategory(String category);

    List<Questions> findAllById(Integer id);

    @Query(value = "SELECT * FROM questions q where q.category = :category ORDER BY RANDOM() LIMIT :numOfQuestions",nativeQuery = true)
    List<Questions> findRandomQuestionsByCategory(String category, Integer numOfQuestions);

    @Query(value = "SELECT q.id FROM questions q where q.category = :category ORDER BY RANDOM() LIMIT :numOfQuestions",nativeQuery = true)
    List<Integer> getQuestionsForQuiz(String category, Integer numOfQuestions);
}
