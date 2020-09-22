package com.shoppingapp.impl;

import com.shoppingapp.dao.UserDao;
import com.shoppingapp.entity.User;
import com.shoppingapp.framework.State;

public class RegisterMenu extends State {
	
	User user;

	public RegisterMenu() {
		super("RegisterMenu");
	}

	@Override
	protected void initialize() {
		startPrint();
		print("Please input your registration information now");
		endPrint();
		user = new User();
	}

	@Override
	protected void update() {
		user.name = getString("Username");
		if(UserDao.userExists(user.name)) {
			startPrint();
			print("Username already in use, do you wish to:");
			printOp("RegisterMenu", "RETRY");
			printOp("MainMenu", "RETURN TO MAIN MENU");
			endPrint();
			inputOp();
			return;
		}
		user.email = getString("Email");
		user.password = getString("Password");
		if(user.password.equals(getString("Confirm Password"))) {
			UserDao.addUser(user);
			controller.persistData.put("user", user);
			changeState("UserMenu");
		}
		else {
			startPrint();
			print("Passwords don't match, do you wish to:");
			printOp("RegisterMenu", "RETRY");
			printOp("MainMenu", "RETURN TO MAIN MENU");
			endPrint();
			inputOp();
		}
	}

	@Override
	protected void exit() {
		user = null;
	}

}
