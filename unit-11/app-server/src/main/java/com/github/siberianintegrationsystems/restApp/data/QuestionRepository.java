package com.github.siberianintegrationsystems.restApp.data;

import com.github.siberianintegrationsystems.restApp.controller.dto.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.entity.Question;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface QuestionRepository
        extends CrudRepository<Question, Long> {

    List<Question> findByNameContainingIgnoreCase(String search);

    @Query(value = "SELECT * FROM QUESTION",nativeQuery = true)
    List<Question> findQuestions();

}
