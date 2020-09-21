package com.shoppingapp.impl;

import com.shoppingapp.entity.User;
import com.shoppingapp.framework.State;

public class ShowUser extends State {

	public ShowUser() {
		super("ShowUser");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		User user = (User) controller.persistData.get("user");
		startPrint();
		print("Name", user.name);
		print("Email", user.email);
		print("Server-side password", user.password);
		endPrint();
	}

	@Override
	protected void update() {
		pause();
		changeState("UserMenu");
	}

	@Override
	protected void exit() {
		// TODO Auto-generated method stub

	}

}
