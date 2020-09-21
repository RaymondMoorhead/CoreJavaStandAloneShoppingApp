package com.shoppingapp.entity;

import java.util.Date;
import java.util.List;

import com.shoppingapp.utility.Pair;

public class User {

	public String name;
	public String password;
	public String email;
	public List<Pair<Item, Date>> purchases;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Pair<Item, Date>> getPurchases() {
		return purchases;
	}
	public void setPurchases(List<Pair<Item, Date>> purchases) {
		this.purchases = purchases;
	}
}
