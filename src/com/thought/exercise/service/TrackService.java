package com.thought.exercise.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.thought.exercise.domain.Session;
import com.thought.exercise.domain.Talk;
import com.thought.exercise.domain.Track;
import com.thought.exercise.exception.InvalidScheduleException;
import com.thought.exercise.utils.SessionType;

public class TrackService {
	
    private SessionService sessionService;
    private TalkService talkService;
    private List<Track> tracks = new ArrayList<Track>();
    
    private static final int MINIMUM_TIME_PER_DAY = 420; //Maximum min of Morning + Maximum min of Afternoon
    private String networkingStartTime;
    
	
	/* create a Schedule and prints the tracks inside the schedule
	 */
	public void createSchedule() throws InvalidScheduleException{
		
		int totalTalktime = talkService.calculateTotalTalkTimeInMinutes(talkService.get_talks());
		int conferenceDays = 0;
		
		if (totalTalktime % MINIMUM_TIME_PER_DAY == 0) {		
			 conferenceDays = totalTalktime/MINIMUM_TIME_PER_DAY;
		}else{
			conferenceDays = (totalTalktime/MINIMUM_TIME_PER_DAY )+ 1;
		}
		
		for (int i = 0; i < conferenceDays; i++) {
			sessionService.createSessions(SessionType.MORNING_SESSION);
			//the last track may have only morning session
			if (talkService.get_talks().size() > 0) {
				sessionService.createSessions(SessionType.AFTERNOON_SESSION);				
			}
		}		
	}
	
	
	//combines all the tracks
	public void createTracks() throws ParseException {
	
	    for (int index = 0; index <= sessionService.getMorningSessions().size(); index++) {
	    	 Track track = new Track();
			 createMorningSessionOfTrack(sessionService.getMorningSessions().get(index),track,index);
			 if (index + 1 == sessionService.getMorningSessions().size()) {
				 break;
			 }else{
				 createAfternoonSessionOfTrack(sessionService.getAfternoonSessions().get(index),track,index); 
			 }
			 
			 tracks.add(track);
		}     
	}
	
	private void createMorningSessionOfTrack(Session session,Track track,int index) throws ParseException{			  
           track.setMorningSession(sessionService.getMorningSessions().get(index));
           track.setTrackNumber(String.valueOf(index + 1));
           
		   System.out.println("********* start track info **************");
           System.out.println("Track " + track.getTrackNumber());
           printTrack(track, SessionType.MORNING_SESSION);
	}
	
	private void createAfternoonSessionOfTrack(Session session,Track track,int index) throws ParseException{
		track.setAfternoonSession(sessionService.getAfternoonSessions().get(index));
	     printTrack(track,SessionType.AFTERNOON_SESSION);
	}
	
	
	public void printTrack(final Track track,final SessionType type) throws ParseException{
		
		DateFormat df = new SimpleDateFormat("h:mm a");
		Calendar cal = Calendar.getInstance();
		String scheduledTime;
		
		if(type.equals(SessionType.MORNING_SESSION)){

			Date date = df.parse("9:00 AM");
			cal.setTime(date);
			scheduledTime = df.format(date);
			printSession(SessionType.MORNING_SESSION, track.getMorningSession().getTalks(), scheduledTime);
			
		}else if(type.equals(SessionType.AFTERNOON_SESSION) && track.getAfternoonSession() != null){
			
            // Scheduled Lunch Time for 60 mins.
            Date date = df.parse("12:00 PM");
			scheduledTime = df.format(date);
            System.out.println(scheduledTime + " -- Lunch " + "==" + 60);
			
	        Date afternoonStartTime = df.parse("1:00 PM");
	        cal.setTime(afternoonStartTime);		
			String noonScheduleStartTime = df.format(afternoonStartTime);
			printSession(SessionType.AFTERNOON_SESSION, track.getAfternoonSession().getTalks(), noonScheduleStartTime);
			
            System.out.println(networkingStartTime + " --Networking-- ");
			System.out.println("********* end track info **************");
			
		}
	}
	
	private void printSession(final SessionType type, final List<Talk> talks ,String talkStartTime) throws ParseException{	
		for (Talk talk : talks) {
			System.out.println(talkStartTime + " -- " + talk.getTitle() + "==" + talk.getDuration());
			 talkStartTime = talkStarTime(talkStartTime,talk.getDuration());
		}
	}	
	
    private String talkStarTime(final String date, final int timeDuration) throws ParseException{
    	DateFormat dateFormat = new SimpleDateFormat("h:mm a");
    	Calendar c = Calendar.getInstance();
    	c.setTime(dateFormat.parse(date));
    	c.add(Calendar.MINUTE, timeDuration);
    	networkingStartTime = dateFormat.format(c.getTime());
        return dateFormat.format(c.getTime());
    }

    
    //getter and setters
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public SessionService getSessionService() {
		return sessionService;
	}


	public void setTalkService(TalkService talkService) {
		this.talkService = talkService;
	}


	public List<Track> getTracks() {
		return tracks;
	}
}
