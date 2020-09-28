package com.shoppingapp.impl;

import com.shoppingapp.entity.Invoice;
import com.shoppingapp.entity.Item;
import com.shoppingapp.entity.User;
import com.shoppingapp.framework.State;
import com.shoppingapp.utility.ConsoleExtras;
import com.shoppingapp.dao.ItemDao;
import com.shoppingapp.dao.UserDao;

public class BuyMenu extends State {
	
	private Invoice invoice;
	private User user;

	public BuyMenu() {
		super("BuyMenu");
	}

	@Override
	protected void initialize() {
		user = (User) controller.persistData.get("user");
		invoice = new Invoice(user.name);
		
		startPrint();
		print("0.RETURN TO MAIN PAGE");
		print("1.CHECKOUT");
		print("");
		print("Item", "Item Code", "Price");
		int i = 1;
		for(Item item : ItemDao.getItems()) {
			++i;
			print(Integer.toString(i) + "." + item.name, item.code, "$" + ConsoleExtras.parseAmount(item.price));
		}
		endPrint();
	}

	@Override
	protected void update() {
		int result = getInt("Choose your option");
		if(result == 0) 
			changeState("UserMenu");
		else if(result == 1) {
			changeState("UserMenu");
			if(!invoice.items.isEmpty())
				UserDao.addInvoice(user, invoice);
		}
		else if((result <= ItemDao.getItems().size() + 1) && (result > 0))
			invoice.items.add(ItemDao.getItems().get(result - 2));
		else
			System.out.println("Invalid Choice");

	}

	@Override
	protected void exit() {
		invoice = null;
		user = null;
	}

}
