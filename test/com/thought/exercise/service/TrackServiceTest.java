package com.thought.exercise.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thought.exercise.domain.Track;
import com.thought.exercise.exception.InvalidScheduleException;


public class TrackServiceTest {

	private ServiceFactory serviceFactory;
	private TrackService trackService;
	private SessionService sessionService;
	
	@Before
	public void init(){		
		String fileName = "test2.txt";
		serviceFactory = new TrackServiceFactory();
		try {
			 trackService = serviceFactory.instance(fileName);
			 sessionService = trackService.getSessionService();
		} catch (InvalidScheduleException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void createTracks() {
		List<Track> tracks = null;
		try {
			trackService.createSchedule();
			trackService.createTracks();
			tracks = trackService.getTracks();
			assertFalse(tracks.isEmpty());
			assertEquals(tracks.size(),2);
		} catch (Exception exception) {
			fail("tracks should be created");
		} 
	}
	

	@Test
	public void createScheduleTest() {
		try {
			trackService.createSchedule();
			assertFalse(sessionService.getMorningSessions().isEmpty());
			assertFalse(sessionService.getAfternoonSessions().isEmpty());
			
			assertTrue(sessionService.getMorningSessions().size() == 3);
			assertTrue(sessionService.getAfternoonSessions().size() == 2);
		} catch (Exception exception) {
			fail("tracks should be created");
		} 
	}
}
