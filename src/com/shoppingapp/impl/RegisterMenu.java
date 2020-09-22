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
		
		// email
		while(!(user.email = getString("Email")).matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			System.out.println("Please input a valid email");
		}
		
		// password
		while(!(user.password = getString("Password")).equals(getString("Confirm Password"))) {
			System.out.println("Passwords don't match, please input them again");
		}
			UserDao.addUser(user);
			changeState("MainMenu");
	}

	@Override
	protected void exit() {
		user = null;
	}

}
