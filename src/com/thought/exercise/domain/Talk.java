package com.thought.exercise.domain;

import java.io.Serializable;
import java.util.Date;

public class Talk implements Serializable,Comparable<Talk>{
	
	private static final long serialVersionUID = 1L;
	
	private String title;
	private int duration;
	private Date startTime;
	private boolean scheduled;

	public Talk(final String title, final int talkDuaration) {
		this.title = title;
		this.duration = talkDuaration;
	}
	
	@Override
	public int compareTo(Talk anotherTalk) {		
		return this.getDuration() - anotherTalk.getDuration();
	}
	
	public boolean isScheduled(){
		return scheduled;
	}
	
	public void setScheduled(boolean scheduled){
		this.scheduled = scheduled;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		   Talk other = (Talk) obj;
        if (!title.equals(other.title))
			return false;
		return true;
	}
}
