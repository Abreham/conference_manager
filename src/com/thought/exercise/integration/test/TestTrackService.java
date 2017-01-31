package com.thought.exercise.integration.test;

import java.text.ParseException;

import com.thought.exercise.exception.InvalidScheduleException;
import com.thought.exercise.service.ServiceFactory;
import com.thought.exercise.service.TrackService;
import com.thought.exercise.service.TrackServiceFactory;

/*
 * Test Track and schedule creation 
 * throws exception in case of parse error.
 */
public class TestTrackService {
	public static void main(String[] args) {
		String fileName = "sample.txt";
		ServiceFactory serviceFactory = new TrackServiceFactory();
		try {
			TrackService trackService = serviceFactory.instance(fileName);
			trackService.createSchedule();
			
			System.out.println();
			System.out.println();
			
			trackService.createTracks();
		} catch (InvalidScheduleException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}
}
