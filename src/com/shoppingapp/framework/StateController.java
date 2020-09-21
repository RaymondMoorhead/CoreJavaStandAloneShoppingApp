package com.shoppingapp.framework;

import java.util.HashMap;

public class StateController {

	private HashMap<String, State> states = new HashMap<String, State>();
	private String changeState = null;
	private State curState;
	private boolean exit = false;
	
	public HashMap<String, Object> persistData = new HashMap<String, Object>();
	
	public void run(String startState) {
		curState = states.get(startState);
		curState.initialize();
		
		while(curState != null) {
			curState.update();
			if(changeState != null) {
				curState.exit();
				curState = states.get(changeState);
				curState.initialize();
				changeState = null;
			}
			else if(exit) {
				curState.exit();
				break;
			}
		}
	}
	
	public void addState(State state) {
		state.controller = this;
		states.put(state.name, state);
	}
	
	void changeState(String name) {
		exit = (changeState = name) == null;
	}
}
