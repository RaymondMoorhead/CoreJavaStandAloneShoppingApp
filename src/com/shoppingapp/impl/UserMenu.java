package com.shoppingapp.impl;

import com.shoppingapp.framework.State;

public class UserMenu extends State {

	public UserMenu() {
		super("UserMenu");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		startPrint();
		printOp("MainMenu", "LOGOUT");
		printOp("BuyMenu", "MAKE PURCHASE");
		printOp("ReturnMenu", "RETURN ITEM");
		printOp("ShowPurchases", "PURCHASE HISTORY");
		printOp("ShowUser", "ACCOUNT DETAILS");
		endPrint();
	}

	@Override
	protected void update() {
		inputOp();
	}

	@Override
	protected void exit() {
		// TODO Auto-generated method stub

	}

}
