package com.shoppingapp;

import com.shoppingapp.framework.StateController;
import com.shoppingapp.impl.MainMenu;

public class Main {

	public static void main(String[] args) {
		StateController controller = new StateController();;
		
		// add states
		controller.addState(new MainMenu());
		
		// run
		controller.run("MainMenu");
	}

}
