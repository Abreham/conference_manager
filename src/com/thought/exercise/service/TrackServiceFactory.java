package com.thought.exercise.service;

import com.thought.exercise.exception.InvalidScheduleException;

public class TrackServiceFactory implements ServiceFactory {
	
	public TrackService instance(String fileName) throws InvalidScheduleException{
		TalkService talkService = new TalkService(fileName);
		SessionService sessionService = new SessionService(talkService);          
		TrackService trackService = new TrackService();
		trackService.setSessionService(sessionService);
	    trackService.setTalkService(talkService);
		return trackService;
	}
}
