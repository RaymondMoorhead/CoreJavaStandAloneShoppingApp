package com.shoppingapp;

import com.shoppingapp.dao.ItemDao;
import com.shoppingapp.dao.UserDao;
import com.shoppingapp.entity.Item;
import com.shoppingapp.entity.User;
import com.shoppingapp.framework.StateController;
import com.shoppingapp.impl.BuyMenu;
import com.shoppingapp.impl.LoginMenu;
import com.shoppingapp.impl.MainMenu;
import com.shoppingapp.impl.RegisterMenu;
import com.shoppingapp.impl.ReturnMenu;
import com.shoppingapp.impl.ShowUser;
import com.shoppingapp.impl.ShowInvoice;
import com.shoppingapp.impl.ShowPurchases;
import com.shoppingapp.impl.UserMenu;

public class Main {

	public static void main(String[] args) {
		StateController controller = new StateController();
		
		// add local memory dao debug data
		UserDao.addUser(new User("Karl", "ShieldBash!", "coolguy@mail.com"));
		
		ItemDao.addItem(new Item("Jacket", "Ja1", 2000));
		ItemDao.addItem(new Item("Jeans", "Je1", 1000));
		ItemDao.addItem(new Item("Shirt", "Sh1", 500));
		
		// add states
		controller.addState(new MainMenu());
		controller.addState(new BuyMenu());
		controller.addState(new LoginMenu());
		controller.addState(new RegisterMenu());
		controller.addState(new ReturnMenu());
		controller.addState(new ShowInvoice());
		controller.addState(new UserMenu());
		controller.addState(new ShowUser());
		controller.addState(new ShowPurchases());
		
		// run
		controller.run("MainMenu");
	}

}
