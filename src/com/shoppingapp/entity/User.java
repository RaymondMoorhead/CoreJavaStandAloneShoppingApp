package com.shoppingapp.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

	public String name;
	public String password;
	public String email;
	public List<Invoice> purchases;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.purchases = new ArrayList<Invoice>();
	}
	
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
	public List<Invoice> getPurchases() {
		return purchases;
	}
	public void setPurchases(List<Invoice> purchases) {
		this.purchases = purchases;
	}
	public void addPurchase(Invoice purchase) {
		purchases.add(purchase);
	}
}
