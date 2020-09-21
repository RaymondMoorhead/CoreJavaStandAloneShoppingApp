package com.shoppingapp.entity;

public class Item {

	public String name;
	public String code;
	public double price;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(String name, String code, double price) {
		super();
		this.name = name;
		this.code = code;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
