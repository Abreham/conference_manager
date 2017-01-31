package com.thought.exercise.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.thought.exercise.exception.InvalidScheduleException;

public class TalkServiceTest {
	
	private TalkService talkService; 
	
	@Test
	public void loadTalksWithValidInput() {
		try {
			talkService = new TalkService("test1.txt");
		} catch (Exception exception) {
			fail("Should not throw an exception");
		}
	}
	
	
	@Test
	public void loadTalksWithInValidInput() {
		try {
			talkService = new TalkService("test3.txt");
			fail("Should throw an exception");
		} catch (Exception exception) {
			assertThat(exception, instanceOf(InvalidScheduleException.class));
		}
	}
	
	@Test
	public void calculateTotalTalkTimeInMinutesTest(){
		try {
			talkService = new TalkService("sample.txt");
			assertEquals(953, talkService.calculateTotalTalkTimeInMinutes(talkService.get_talks()));
		} catch (Exception exception) {
			fail("Should not throw an exception");
		}
	}
}
