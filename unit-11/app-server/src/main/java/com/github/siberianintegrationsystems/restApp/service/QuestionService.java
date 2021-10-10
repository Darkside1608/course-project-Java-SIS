package com.github.siberianintegrationsystems.restApp.service;

import com.github.siberianintegrationsystems.restApp.controller.dto.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.entity.Question;

import java.util.List;

public interface QuestionService {
    QuestionsItemDTO createQuestion(QuestionsItemDTO dto);
    QuestionsItemDTO editQuestion(QuestionsItemDTO dto);
    List<QuestionsItemDTO> getQuestionToSession();


}
