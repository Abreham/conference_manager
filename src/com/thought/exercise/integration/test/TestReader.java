package com.thought.exercise.integration.test;

import java.util.List;

import com.thought.exercise.domain.Pair;
import com.thought.exercise.utils.ReadFileUtil;
import com.thought.exercise.utils.SessionType;

public class TestReader {
	public static void main(String[] args) {
		String fileName = "sample.txt";
		System.out.println("morning session limit is : " + SessionType.AFTERNOON_SESSION);
		try {
			List<Pair<String,String>> results = ReadFileUtil.readLines(fileName);
			printListPair(results);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static <A extends Comparable<? super A>, B extends Comparable<? super B>> void printListPair(List<Pair<A,B >> results) {
		 for (Pair<A, B> pair : results) {
			 System.out.println(pair.getFirst() + " = " + pair.getSecond());
		}
	}
}
