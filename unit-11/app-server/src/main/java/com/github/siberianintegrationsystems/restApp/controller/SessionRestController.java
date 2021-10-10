package com.github.siberianintegrationsystems.restApp.controller;

import com.github.siberianintegrationsystems.restApp.controller.dto.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.sessiondto.SessionRequestDTO;
import com.github.siberianintegrationsystems.restApp.entity.Question;
import com.github.siberianintegrationsystems.restApp.service.QuestionService;
import com.github.siberianintegrationsystems.restApp.service.SessionService;

import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class SessionRestController {


    public SessionRestController(SessionService sessionService,
            QuestionService questionService) {
        this.sessionService = sessionService;
        this.questionService = questionService;
    }
    private final SessionService sessionService;
    private final QuestionService questionService;


    @GetMapping("session/questions-new")
    public List<QuestionsItemDTO> getQuestionToSession(){
        return questionService.getQuestionToSession();
    }

    @PostMapping("session")
    String saveSession(@RequestBody SessionRequestDTO sessionRequestDTO){
        return sessionService.сheckSession(sessionRequestDTO);
     }









}
