package com.github.siberianintegrationsystems.restApp.service;

import com.github.siberianintegrationsystems.restApp.controller.dto.sessiondto.SessionAnsweredQuestionDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.sessiondto.SessionQuestionAnswerDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.sessiondto.SessionRequestDTO;
import com.github.siberianintegrationsystems.restApp.data.AnswerRepository;
import com.github.siberianintegrationsystems.restApp.data.QuestionRepository;
import com.github.siberianintegrationsystems.restApp.entity.Answer;
import com.github.siberianintegrationsystems.restApp.entity.Question;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class SessionServiceImplTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private SessionService sessionService;
     @Test
    public void testCheckQuestion() {

         Question question =new Question();
         question.setName("Кто такой пушкин?");
         questionRepository.save(question);

         Answer answer = new Answer();
         answer.setName("Писатель");
         answer.setCorrect(true);
         answer.setQuestion(question);
         answerRepository.save(answer);

         Answer answer1 = new Answer();
         answer1.setName("Композитор");
         answer1.setCorrect(false);
         answer1.setQuestion(question);
         answerRepository.save(answer1);

         Answer answer2 = new Answer();
         answer2.setName("Поэт");
         answer2.setCorrect(true);
         answer2.setQuestion(question);
         answerRepository.save(answer2);

         SessionQuestionAnswerDTO sessionAnswer = new SessionQuestionAnswerDTO();
         sessionAnswer.id = String.valueOf(answer.getId());
         sessionAnswer.isSelected = true;

         SessionQuestionAnswerDTO sessionAnswer1 = new SessionQuestionAnswerDTO();
         sessionAnswer1.id = String.valueOf(answer1.getId());
         sessionAnswer1.isSelected = false;

         SessionQuestionAnswerDTO sessionAnswer2 = new SessionQuestionAnswerDTO();
         sessionAnswer2.id = String.valueOf(answer2.getId());
         sessionAnswer2.isSelected = false;

         SessionAnsweredQuestionDTO sessionTestedDTO = new SessionAnsweredQuestionDTO();
         sessionTestedDTO .answersList = Arrays.asList(sessionAnswer, sessionAnswer1,sessionAnswer2);
         sessionTestedDTO .id = String.valueOf(question.getId());

         SessionRequestDTO sessionTestDTO = new SessionRequestDTO();
         sessionTestDTO.name = "Имя Фамилия";
         sessionTestDTO.questionsList = Arrays.asList(sessionTestedDTO);
         String result = sessionService.сheckSession( sessionTestDTO);

         assertEquals("50,00", result);

         sessionTestDTO.questionsList.forEach(
                 q -> q.answersList.forEach(
                         a -> a.isSelected = false));

         result = sessionService.сheckSession( sessionTestDTO);
         assertEquals("0,00", result);


     }
}