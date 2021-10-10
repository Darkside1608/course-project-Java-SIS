package com.github.siberianintegrationsystems.restApp.data;

import com.github.siberianintegrationsystems.restApp.entity.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRequestRepository extends CrudRepository<Session, String> {

}
