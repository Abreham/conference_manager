package com.thought.exercise.service;

import org.junit.Test;
import static org.junit.Assert.*;

import com.thought.exercise.domain.Pair;
import com.thought.exercise.utils.ReadFileUtil;


public class ReadUtilsTest {

	@Test
	public void tesSimple() {
		Pair<String, String> pair = ReadFileUtil.parseRawData("");
		assertEquals("", pair.getFirst());
	}
	
	@Test
	public void simple() {
		Pair<String, String> pair = ReadFileUtil.parseRawData("java introduction 8min");
		assertEquals("java introduction", pair.getFirst());
	}
	
	@Test
	public void simple_2() {
		Pair<String, String> pair = ReadFileUtil.parseRawData("java introduction 8min");
		assertEquals("8", pair.getSecond());
	}
	
	@Test
	public void hourTest() {
		Pair<String, String> pair = ReadFileUtil.parseRawData("java introduction 1hr");
		assertEquals("", pair.getFirst());
		assertEquals("", pair.getSecond());
	}
	
	@Test
	public void testinvalid() {
		Pair<String, String> pair = ReadFileUtil.parseRawData("45min");
		assertEquals("", pair.getFirst());
		assertEquals("", pair.getSecond());
	}
	
	@Test
	public void testinvalid2() {
		Pair<String, String> pair = ReadFileUtil.parseRawData("hello");
		assertEquals("", pair.getFirst());
		assertEquals("", pair.getSecond());
	}
}
