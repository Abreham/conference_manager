package com.thought.exercise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.thought.exercise.domain.Session;
import com.thought.exercise.domain.Talk;
import com.thought.exercise.utils.SessionCombinationHandler;
import com.thought.exercise.utils.SessionType;

public class SessionService {

	private List<Session> morningSessions = new ArrayList<Session>();
	private List<Session> afternoonSessions = new ArrayList<Session>();
	private TalkService talkService;
	private SessionCombinationHandler sessionHelper;
	

	public SessionService(TalkService talkService) {
		this.talkService = talkService;	
	}
	
	public void createSessions(final SessionType type) {
	    sessionHelper = new SessionCombinationHandler();
	    
	    List<Talk> talks = talkService.get_talks();
        sessionHelper.populateSubset(talks, 0, talks.size(), type);       
        if (type == SessionType.MORNING_SESSION) {
        	morningSessions.add(new Session(type, sessionHelper.getBiggestSumStack()));
		}else{
			afternoonSessions.add(new Session(type, sessionHelper.getBiggestSumStack()));
		}     

        removeScheduledTalks(sessionHelper.getBiggestSumStack());			
	}
	
	private List<Talk> removeScheduledTalks(Stack<Talk> talks){
	   for (Talk talk : talks) {
		  System.out.println("** " + talk.getTitle() + " duration " + talk.getDuration());
		  if (talkService.get_talks().contains(talk)) {
			   talkService.get_talks().remove(talk);
			   talk.setScheduled(true);
		  }
	   }
	   return talks;
	}

	/*TODO remove this method
	 * morning session is equal to maxSessionTimeLimit.
	 * SessionType.AFTERNOON_SESSION min <= afternoon session <= SessionType.AFTERNOON_SESSION maximum
	 */
	public boolean isValidSession(final int sessionTimeMin, SessionType type) {
		boolean validSession = false;
		if (type.equals(SessionType.MORNING_SESSION))
			validSession = (sessionTimeMin <= type.getMaxSessionTimeLimit());
		else
			validSession = (sessionTimeMin <= type.getMaxSessionTimeLimit());
		return validSession;
	}

	public List<Session> getMorningSessions(){
		return morningSessions;
	}

	public List<Session> getAfternoonSessions(){
		return afternoonSessions;
	}
}
