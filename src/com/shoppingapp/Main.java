package com.shoppingapp;

import com.shoppingapp.dao.ItemDao;
import com.shoppingapp.dao.UserDao;
import com.shoppingapp.entity.Invoice;
import com.shoppingapp.entity.Item;
import com.shoppingapp.entity.User;
import com.shoppingapp.framework.StateController;
import com.shoppingapp.impl.BuyMenu;
import com.shoppingapp.impl.LoginMenu;
import com.shoppingapp.impl.MainMenu;
import com.shoppingapp.impl.RegisterMenu;
import com.shoppingapp.impl.ReturnMenu;
import com.shoppingapp.impl.ShowUser;
import com.shoppingapp.impl.ShowPurchases;
import com.shoppingapp.impl.UserMenu;

public class Main {

	public static void main(String[] args) {
		StateController controller = new StateController();
		
		// add local memory dao debug data
		
		// items for sale
		Item debugItem1 = new Item("Jacket", "Ja1", 2000);
		Item debugItem2 = new Item("Jeans", "Je1", 1000);
		Item debugItem3 = new Item("Shirt", "Sh1", 500);
		ItemDao.addItem(debugItem1);
		ItemDao.addItem(debugItem2);
		ItemDao.addItem(debugItem3);
		
		// user
		User debugUser = new User("TestUser", "TestPassword", "mail@mail.com");
		UserDao.addUser(debugUser);
		
		// user's purchases
		Invoice debugInvoiceNew = new Invoice(debugUser.name);
		debugInvoiceNew.items.add(debugItem1);
		debugInvoiceNew.items.add(debugItem2);
		debugInvoiceNew.items.add(debugItem3);
		debugInvoiceNew.items.add(debugItem1);
		debugUser.purchases.add(debugInvoiceNew);
		
		Invoice debugInvoiceOld = new Invoice(debugUser.name);
		debugInvoiceOld.date = debugInvoiceOld.date.minusDays(16);
		debugInvoiceOld.items.add(debugItem3);
		debugInvoiceOld.items.add(debugItem1);
		debugInvoiceOld.items.add(debugItem3);
		debugInvoiceOld.items.add(debugItem2);
		debugUser.purchases.add(debugInvoiceOld);
		
		// add states
		controller.addState(new MainMenu());
		controller.addState(new BuyMenu());
		controller.addState(new LoginMenu());
		controller.addState(new RegisterMenu());
		controller.addState(new ReturnMenu());
		controller.addState(new UserMenu());
		controller.addState(new ShowUser());
		controller.addState(new ShowPurchases());
		
		// run
		controller.run("MainMenu");
	}

}
