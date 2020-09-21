package com.shoppingapp.impl;

import com.shoppingapp.framework.State;

public class MainMenu extends State {

	public MainMenu() {
		super("MainMenu");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		startPrint();
		print("1.REGISTER");
		print("1.LOGIN");
		print("1.BUY AN ITEM");
		print("1.RETURN AN ITEM");
		print("1.EXIT");
		endPrint();
	}

	@Override
	protected void update() {
		int result = getInt("What would you like to do?");
		switch(result) {
			case 1:
				changeState("RegisterMenu");
				break;
			case 2:
				changeState("LoginMenu");
				break;
			case 3:
				changeState("BuyMenu");
				break;
			case 4:
				changeState("ReturnMenu");
				break;
			case 5:
				shutdown();
				break;
			default:
				break;
		}
	}

	@Override
	protected void exit() {
	}

}
