package com.shoppingapp.dao;

import java.util.HashMap;

import com.shoppingapp.entity.User;
import com.shoppingapp.security.Encrypt;

public class UserDao {
	
	private static HashMap<String, User> users = new HashMap<String, User>();

	public static User getUser(String name, String password) {
		User result = users.get(name);
		if(result != null && result.password.equals(encryptPass(name, password)))
			return result;
		else
			return null;
	}
	
	public static void updateUser(User user) {
		if(!users.containsKey(user.name))
			return;
		users.put(user.name, user);
	}
	
	public static boolean userExists(String name) {
		return users.containsKey(name);
	}
	
	public static void addUser(User user) {
		if(users.containsKey(user.name))
			return;
		user.password = encryptPass(user.name, user.password);
		users.put(user.name, user);
	}
	
	private static String encryptPass(String name, String password) {
		return Encrypt.encryptIrreversable(password, name, password, 20);
	}
}
