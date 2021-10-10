package com.github.siberianintegrationsystems.restApp.service;


import com.github.siberianintegrationsystems.restApp.controller.dto.AnswerItemDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.data.AnswerRepository;
import com.github.siberianintegrationsystems.restApp.data.QuestionRepository;
import com.github.siberianintegrationsystems.restApp.entity.Answer;

import com.github.siberianintegrationsystems.restApp.entity.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;

import java.util.stream.Collectors;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class QuestionServiceImplTest {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Before
    public void Answer() {
        QuestionsItemDTO questionsItemDTO = new QuestionsItemDTO();
        questionsItemDTO.name = "Сколько дней в году";

        Answer answer = new Answer();
        answer.setName("365");
        answer.setCorrect(true);
        answerRepository.save(answer);

        Answer answer1 = new Answer();
        answer1.setName("320");
        answer1.setCorrect(false);
        answerRepository.save(answer1);

        AnswerItemDTO answerTestDTO = new AnswerItemDTO(answer);
        AnswerItemDTO answerTestDTO1 = new AnswerItemDTO(answer1);

        questionsItemDTO.answers = Arrays.asList(answerTestDTO, answerTestDTO1);

        QuestionsItemDTO createdQuestion = questionService.createQuestion(questionsItemDTO);

        Answer answer2 = new Answer();
        answer2.setName("89");
        answer2.setCorrect(false);
        answerRepository.save(answer2);

        Answer answer3 = new Answer();
        answer3.setName("9");
        answer3.setCorrect(true);
        answerRepository.save(answer3);

        AnswerItemDTO answerTestDTO2 = new AnswerItemDTO(answer3);
        AnswerItemDTO answerTestDTO3 = new AnswerItemDTO(answer3);

        QuestionsItemDTO questionsItemDTO1 = new QuestionsItemDTO();
        questionsItemDTO1.name = "Сколько жизней у кошки?";
        questionsItemDTO1.answers = Arrays.asList(answerTestDTO2, answerTestDTO3);

        QuestionsItemDTO createdTestQuestion = questionService.createQuestion(questionsItemDTO1);
    }

    @After
    public void clear() {
        questionRepository.deleteAll();
        answerRepository.deleteAll();
    }

    @Test
    public void createTest() {
        Answer answer = new Answer();
        answer.setName("365");
        answer.setCorrect(true);
        answerRepository.save(answer);

        Answer answer1 = new Answer();
        answer1.setName("320");
        answer1.setCorrect(false);
        answerRepository.save(answer1);

        AnswerItemDTO answerTestDTO = new AnswerItemDTO(answer);
        AnswerItemDTO answerTestDTO1 = new AnswerItemDTO(answer1);

        QuestionsItemDTO questionsItemDTO = new QuestionsItemDTO();
        questionsItemDTO.name = "Сколько дней в году?";
        questionsItemDTO.answers = Arrays.asList(answerTestDTO, answerTestDTO1);

        QuestionsItemDTO createdQuestion = questionService.createQuestion(questionsItemDTO);


        QuestionsItemDTO dto1 = questionService.createQuestion(questionsItemDTO);

        assertThat(dto1, sameBeanAs(questionsItemDTO).ignoring("id").ignoring("answers"));
        assertThat(dto1.answers, sameBeanAs(questionsItemDTO.answers).ignoring("id"));
        assertNotNull(dto1.answers.stream().map(a -> a.id).collect(Collectors.toList()));
        assertNotNull(dto1.id);

    }

    @Test
    public void editTest() {
        Answer answer = new Answer();
        answer.setName("365");
        answer.setCorrect(true);
        answerRepository.save(answer);

        Answer answer1 = new Answer();
        answer1.setName("320");
        answer1.setCorrect(false);
        answerRepository.save(answer1);

        AnswerItemDTO answerTestDTO = new AnswerItemDTO(answer);
        AnswerItemDTO answerTestDTO1 = new AnswerItemDTO(answer1);

        QuestionsItemDTO questionsItemDTO = new QuestionsItemDTO();
        questionsItemDTO.name = "Сколько дней в году?";
        questionsItemDTO.answers = Arrays.asList(answerTestDTO, answerTestDTO1);

        QuestionsItemDTO createdQuestion = questionService.createQuestion(questionsItemDTO);


        QuestionsItemDTO dto1 = questionService.createQuestion(questionsItemDTO);
        Long idQuestion = questionRepository.findByNameContainingIgnoreCase(questionsItemDTO.name).get(0).getId();

        Question question = questionRepository.findById(idQuestion).get();
        int answersBeforeEdit = createdQuestion.answers.size();
        createdQuestion.name = createdQuestion.name + "Текст";
        createdQuestion.answers.remove(0);
        QuestionsItemDTO editQuestion = questionService.editQuestion(createdQuestion);

        assertTrue(editQuestion.name.contains("Текст"));
        assertEquals(answersBeforeEdit - 1,
                editQuestion.answers.size());


    }


}