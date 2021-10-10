package com.github.siberianintegrationsystems.restApp.service;

import com.github.siberianintegrationsystems.restApp.controller.dto.sessiondto.SessionRequestDTO;

public interface SessionService {

        String сheckSession(SessionRequestDTO sessionRequestDTO);

}
