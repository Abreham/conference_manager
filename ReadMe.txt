About
======
program-scheduler is Java program that helps to organize a programming conferences.
Several proposal which have passed the initial screening process will be scheduled 
fitting all the many possible talks/presentation into a session/s which have an efficient time
output.

Use cases/Scenario
===================
 - condition/assumption 1) Morning sessions begin at 9am and must finish by noon, for lunch
 - condition/assumption 2)  Afternoon sessions begin at 1pm and must finish in time for the networking event
 - condition/assumption 3)  The networking event can start no earlier than 4:00 and no later than 5:00.
 - condition/assumption 4) If the last day finishes before lunch there is  no afternoon session or networking
 - condition/assumption 5) the minimum talk/presentation duration is 5min/lightning.


Usage
===================
=Import project as a Java Project in Eclipse Editor
           |src 
           | |com.thought.exercise.integration.test
           |		 -TestReader a file reader Test. 
           |		 -TestTalkService tests the TalkService login
           |	     -TestTrackService test both SessionService and TrackService
           |lib
             -junit.jar
             -hamcrest.jar

	 - change to the root of the project :#cd conference-tracking-manaement
	 - go to library ------------------- :#cd lib
	 - download junit:  --------------- #wget http://central.maven.org/maven2/junit/junit/4.12/junit-4.12.jar
	 - download hamcrest----------------#wget http://central.maven.org/maven2/org/hamcrest/hamcrest-all/1.3/hamcrest-all-1.3.jar
  
 = run Integration test and unit tests
 
Library
==========
I have used both junit-4.12.jar and hamcrest-all-1.3.jar, 
this jars are only for testing,they are not package with submission.   

Domain
===================
 The program has domain objects: Talk,Session and Track.
   |src
     |com.thought.exercise.domain
       -Talk
       -Session
       -Track
        ...
Input
===================
It reads the input from a file sample.txt, using a regular expression.
convert the input into a List of String objects(title,talk_duration).
  |resources
    -sample.txt
    -test1.txt
    -test2.txt


Services
===================
Services for each domain Object, and custom-exception InvalidScheduleException, and utility classes
     |src
       |com.thought.exercise.service

Unit Tests
===================
All unit tests are 
      |test
        |com.thought.exercise.service
           -SessinServiceTest.java
           -TalkServiceTest.java
           -TrackServiceSizeTest.java
           -TrackServiceTest.java
           
   TestSuite
  ------------
        |test
          |com.thought.exercise.service
            -TracksTestSuite.java


Integration Tests
===================
All Integration test are in 
        |src 
           |com.thought.exercise.integration.test
			 -TestReader a file reader Test.
			 -TestTalkService tests the TalkService logic
			 -TestTrackService test both SessionService and TrackService




