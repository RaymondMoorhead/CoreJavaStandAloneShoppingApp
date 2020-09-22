package com.shoppingapp.impl;

import com.shoppingapp.entity.Invoice;
import com.shoppingapp.entity.Item;
import com.shoppingapp.entity.User;
import com.shoppingapp.framework.State;
import com.shoppingapp.utility.ConsoleExtras;

public class ShowPurchases extends State {

	public ShowPurchases() {
		super("ShowPurchases");
	}

	@Override
	protected void initialize() {
		User user = (User) controller.persistData.get("user");
		startPrint();
		if(user.purchases.isEmpty()) {
			print("No Purchases");
		}
		else {
			boolean first = true;
			for(Invoice invoice : user.purchases) {
				if(!first)
					printSeparator();
				else
					first = false;
				print("Customer:", invoice.custName);
				print("Date:", invoice.date.toString());
				print("Invoice Number:", Integer.toString(invoice.invoiceNum));
				print("");
				print("Item", "Item Code", "Price");
				for(Item item : invoice.items)
					print(item.name, item.code, "$" + ConsoleExtras.parseAmount(item.price));
				
			}
		}
		endPrint();
	}

	@Override
	protected void update() {
		pause();
		changeState("UserMenu");
	}

	@Override
	protected void exit() {
		// TODO Auto-generated method stub

	}

}
