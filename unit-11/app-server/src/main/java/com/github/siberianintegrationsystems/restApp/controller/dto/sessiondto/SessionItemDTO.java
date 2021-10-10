package com.github.siberianintegrationsystems.restApp.controller.dto.sessiondto;

import com.github.siberianintegrationsystems.restApp.controller.dto.JournalItemDTO;
import com.github.siberianintegrationsystems.restApp.entity.Question;
import com.github.siberianintegrationsystems.restApp.entity.Session;

import java.util.Date;
import java.util.List;

public class SessionItemDTO extends JournalItemDTO {
    public String name;
    public Date insertDate;
    public Double result;

    public SessionItemDTO() {
    }

    public SessionItemDTO(Session session) {
        this.id = session.getId().toString();
        this.name = session.getName();
        this.insertDate = session.getInsertDate();
        this.result = session.getPercent();


    }
}
