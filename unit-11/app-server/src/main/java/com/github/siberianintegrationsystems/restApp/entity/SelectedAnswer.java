package com.github.siberianintegrationsystems.restApp.entity;

import javax.persistence.*;

@Entity
public class SelectedAnswer extends BaseEntity{
  @JoinColumn(name = "answer_id")
  @OneToOne(fetch = FetchType.LAZY)
  private Answer answer;

  @JoinColumn(name = "session_id")
  @ManyToOne(fetch = FetchType.LAZY)
    private Session session;

  public SelectedAnswer(Answer answer, Session session) {
    this.answer = answer;
    this.session = session;
  }
}
