package com.ensah.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.bo.Session;
import com.ensah.repository.SessionRepository;
import com.ensah.service.SessionService;
@Service

public class SessionServiceImpl implements  SessionService {
@Autowired 
private SessionRepository sessionRepository;

@Override
public List<Session> getAllSession() {
	
	return sessionRepository.findAll();
}

@Override
public Session getSessionById(Long id) {
		return sessionRepository.findById(id).get();
}

}