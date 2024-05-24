package com.ensah.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ensah.bo.Salle;
import com.ensah.bo.Session;
import com.ensah.bo.Surveillance;
import com.ensah.repository.SessionRepository;

public interface SessionService  {
	List<Session> getAllSession();
	 Session getSessionById(Long id);

}
