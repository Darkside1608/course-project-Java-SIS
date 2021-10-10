package com.github.siberianintegrationsystems.restApp.service;

import com.github.siberianintegrationsystems.restApp.controller.dto.AnswerItemDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.data.AnswerRepository;
import com.github.siberianintegrationsystems.restApp.data.QuestionRepository;
import com.github.siberianintegrationsystems.restApp.entity.Answer;
import com.github.siberianintegrationsystems.restApp.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    public QuestionServiceImpl(QuestionRepository questionRepository,
                               AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public QuestionsItemDTO createQuestion(QuestionsItemDTO dto) {
        Question question = new Question();
        question.setName(dto.name);
        questionRepository.save(question);

        for (AnswerItemDTO answerDTO : dto.answers) {
            Answer answer = new Answer();
            answer.setName(answerDTO.answerText);
            answer.setCorrect(answerDTO.isCorrect);
            answer.setQuestion(question);
            answerRepository.save(answer);
        }
        return new QuestionsItemDTO(question,
                answerRepository.findByQuestion(question)); }

    @Override
    public QuestionsItemDTO editQuestion(QuestionsItemDTO dto) {
        Question question = questionRepository.findById(Long.parseLong(dto.id))
                .orElseThrow(() -> new RuntimeException("No issue with such id:" + dto.id));
        answerRepository.findByQuestion(question).forEach(answer -> answerRepository.delete(answer));
        if(!question.getName().equals(dto.name)){
            question.setName(dto.name);
            questionRepository.save(question);
        }
        for(AnswerItemDTO a : dto.answers){
            Answer answer = new Answer();
            answer.setName(a.answerText);
            answer.setCorrect(a.isCorrect);
            answer.setQuestion(question);
            answerRepository.save(answer);
        }
        return new QuestionsItemDTO(question,
                answerRepository.findByQuestion(question));
    }

    @Override
    public List<QuestionsItemDTO> getQuestionToSession() {
        List<QuestionsItemDTO> questionsItemDTOList =
                questionRepository.findQuestions()
                        .stream()
                        .map(question -> {
                                    List<Answer> answers = answerRepository.findByQuestion(question);
                                    return new QuestionsItemDTO(question,
                                    answers);
                                }
                        )
                        .collect(Collectors.toList());
        return questionsItemDTOList;
    }
}

