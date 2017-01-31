package com.thought.exercise.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.thought.exercise.domain.Pair;
import com.thought.exercise.domain.Talk;
import com.thought.exercise.exception.InvalidScheduleException;
import com.thought.exercise.utils.Function;
import com.thought.exercise.utils.ReadFileUtil;

public class TalkService {
	
	private String fileName;
	private List<Talk> talks;
	private static final StringListToTalkFunction STRING_LIST_TO_TALK_LIST = new StringListToTalkFunction();
	
	public TalkService(String fileName) throws InvalidScheduleException {
		this.fileName = fileName;
		loadTalks();
	}

	public List<Talk> get_talks() {
		return talks;
	}

	public void setTalks(List<Talk> talks) {
		this.talks = talks;
	}

	private void loadTalks() throws InvalidScheduleException {
		try {
			talks = STRING_LIST_TO_TALK_LIST.apply(ReadFileUtil.readLines(fileName));
		     Collections.sort(talks, new Comparator<Talk>() {
					@Override
					public int compare(Talk talk1, Talk talk2) {
						return talk1.compareTo(talk2);
					}
		   });
	
		} catch (Exception exception) {
			if (exception instanceof NullPointerException) {
				throw new InvalidScheduleException("Couldn't find the source file");
			}else{
				throw new InvalidScheduleException(exception + "Input program data is corrupted or not properly formatted");				
			}
		}
	}
	
	/*
	 * a function Transforming List<Pair<String,String>> into List<Talk> objects
	 */
	   
	private static class StringListToTalkFunction implements Function<List<Pair<String,String>>, List<Talk>>{

		@Override
		public List<Talk> apply(final List<Pair<String, String>> input) {
			List<Talk> talks = new ArrayList<Talk>();
			for (Pair<String, String> pair : input)
			{
			    System.out.println(pair.getFirst() + "/" + pair.getSecond());
			    talks.add(new Talk(pair.getFirst(), Integer.parseInt(pair.getSecond())));
			}
			
			return talks;
		}
		
	}
	
	public int calculateTotalTalkTimeInMinutes(List<Talk> talksList){
		 int totalTime = 0;
		 if(talksList == null || talksList.isEmpty()){
	            System.out.println("Total time is zero");
	     }else{    	 
			 for(Talk talk : talksList) {
				 totalTime += talk.getDuration();
			 } 
		 }
		 
	     return totalTime;
	}
}
