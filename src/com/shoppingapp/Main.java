package com.shoppingapp;

import com.shoppingapp.dao.DaoManager;
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
		
		// initialize database dao
		DaoManager.initializeRemote();
//		DaoManager.initializeLocal();
		
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
