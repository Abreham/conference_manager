package com.thought.exercise.domain;

import java.util.List;

import com.thought.exercise.utils.SessionType;

public class Session {
	
	private SessionType sessionType;
	private int duration;
	private List<Talk> talks;
    
	
	public Session(SessionType sessionType, List<Talk> sessionTalks) {
		this.sessionType = sessionType;
		this.talks = sessionTalks;
	}


	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<Talk> getTalks() {
		return talks;
	}

	public void setTalks(List<Talk> sessionTalks) {
		this.talks = sessionTalks;
	}

	public SessionType getSessionType() {
		return sessionType;
	}

	public void setSessionType(SessionType sessionType) {
		this.sessionType = sessionType;
	}
}
