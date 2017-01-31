package com.thought.exercise.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thought.exercise.domain.Pair;

public final class ReadFileUtil {
	
	public static final ReadFileUtil Instance = new ReadFileUtil();	

	private static final String TIME_PATTERN = "([^\\w\\r\\n]+)((([0-9]+)(?:min))$|lightning$)";
	private static final Pattern pattern = Pattern.compile(TIME_PATTERN , Pattern.CASE_INSENSITIVE);
	private static Matcher matcher;
	
	private ReadFileUtil(){}

	public static List<Pair<String,String>> readLines(String filename) throws Exception {
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream in = classLoader.getResourceAsStream(filename);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF8"));
	
		List<Pair<String,String>> lines = new ArrayList<Pair<String,String>>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			Pair<String, String> rawdata = parseRawData(line);
			
			if (rawdata != null && !rawdata.getFirst().equals("") &&
					!rawdata.getSecond().equals("") && !rawdata.getFirst().equals("Invalid")) {
					lines.add(rawdata);			
			}
		}
		
		bufferedReader.close();
		return lines;
	}

	public static Pair<String, String> parseRawData(String rawData) {
		matcher = pattern.matcher(rawData);
		Pair<String, String> pair = new Pair<String, String>("", "");
		if (matcher.find()) {
			if (matcher.group().trim().equals("lightning")) {
				pair = new Pair<String, String>(rawData.substring(0,matcher.start()), "5");
			}else{
				pair = new Pair<String, String>(rawData.substring(0,matcher.start()), matcher.group(4));
				if (pair.getFirst().matches(".*\\d+.*")) {				
					pair.setFirst("Invalid");
				}
			}
		}
		return pair;
	}
}
