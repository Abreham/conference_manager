package com.thought.exercise.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thought.exercise.domain.Session;
import com.thought.exercise.exception.InvalidScheduleException;
import com.thought.exercise.utils.SessionType;

public class SessionServiceTest {

	private TalkService talkService; 
	private SessionService sessionService;
	
	@Before
	public void setUp(){
		try {
			talkService = new TalkService("sample.txt");
		} catch (InvalidScheduleException e) {
			fail("talk should have been loaded ......");
		}
		sessionService = new SessionService(talkService);
	}
	
	@Test
	public void createSessionWithMorningSessionType(){
		sessionService.createSessions(SessionType.MORNING_SESSION);
		List<Session> sessions = sessionService.getMorningSessions();
		assertFalse(sessions.isEmpty());
	}
	
	@Test
	public void createSessionWithAfterNoonSessionType(){
		sessionService.createSessions(SessionType.AFTERNOON_SESSION);
		List<Session> sessions = sessionService.getAfternoonSessions();
		assertTrue(!sessions.isEmpty());
	}
	
	@Test
	public void checkMorningSessionValidity(){
		boolean isValid = sessionService.isValidSession(180, SessionType.MORNING_SESSION);
		assertTrue(isValid);
	}
	
	@Test
	public void checkMinimumAfternoonSessionValidity(){
		boolean isValid = sessionService.isValidSession(181, SessionType.AFTERNOON_SESSION);
		assertTrue(isValid);
	}
	
	@Test
	public void checkMaximumAfternoonSessionValidity(){
		boolean isValid = sessionService.isValidSession(240, SessionType.AFTERNOON_SESSION);
		assertTrue(isValid);
	}
	
	@Test
	public void checkInvalidfternoonSessionValidity(){
		boolean isValid = sessionService.isValidSession(241, SessionType.AFTERNOON_SESSION);
		assertFalse(isValid);
	}
}
