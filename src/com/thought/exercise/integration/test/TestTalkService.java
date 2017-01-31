package com.thought.exercise.integration.test;

import com.thought.exercise.exception.InvalidScheduleException;
import com.thought.exercise.service.TalkService;

public class TestTalkService {
	public static void main(String[] args) {
		String fileName = "sample.txt";
		
			try {
				TalkService talkService = new TalkService(fileName); 
				System.out.println("#####");
				System.out.println("Loading service :" + talkService.getClass().getCanonicalName());
			} catch (InvalidScheduleException e) {
				e.printStackTrace();
			}
	}
}