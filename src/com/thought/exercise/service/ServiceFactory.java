package com.thought.exercise.service;

import com.thought.exercise.exception.InvalidScheduleException;

public interface ServiceFactory {
	 TrackService instance(String fileName) throws InvalidScheduleException;
}
