package com.thought.exercise.utils;

import java.util.List;
import java.util.Stack;

import com.thought.exercise.domain.Talk;

public class SessionCombinationHandler {

    private int currentStackMaxSumValue = 0;
    private int tempBiggestSum = 0;
	private Stack<Talk> stack = new Stack<Talk>();
	private Stack<Talk> newBiggestSumStack;
	
	
	public void populateSubset(List<Talk> talks, int fromIndex, int endIndex, SessionType type) {
    	if (currentStackMaxSumValue > type.getMinSessionTimeLimit() && currentStackMaxSumValue > tempBiggestSum) {
            	tempBiggestSum = currentStackMaxSumValue;
            	newBiggestSumStack = new Stack<Talk>();
            	newBiggestSumStack.addAll(stack);
    	}
    	
        for (int currentIndex = fromIndex; currentIndex < endIndex; currentIndex++) {
			if (currentStackMaxSumValue + talks.get(currentIndex).getDuration() <= type.getMaxSessionTimeLimit()) {
				Talk currentTalk = talks.get(currentIndex);
                stack.push(currentTalk);
                currentStackMaxSumValue += currentTalk.getDuration();
           
                populateSubset(talks, currentIndex + 1, endIndex,type);
                
                //give chance to the other possible combinations by backtracking
                currentStackMaxSumValue -= (Integer) stack.pop().getDuration();
            }
        }
        
	}

	
	public Stack<Talk> getBiggestSumStack(){
		return newBiggestSumStack;
	}
}
