package com.thought.exercise.domain;


public class Track {

	private String trackNumber;
	private Session morningSession;
	private Session afternoonSession;

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	public Session getMorningSession() {
		return morningSession;
	}

	public void setMorningSession(Session morningSession) {
		this.morningSession = morningSession;
	}

	public Session getAfternoonSession() {
		return afternoonSession;
	}

	public void setAfternoonSession(Session afternoonSession) {
		this.afternoonSession = afternoonSession;
	}
}
