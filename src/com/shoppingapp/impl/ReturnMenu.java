package com.shoppingapp.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.shoppingapp.dao.ItemDao;
import com.shoppingapp.entity.Invoice;
import com.shoppingapp.entity.Item;
import com.shoppingapp.entity.User;
import com.shoppingapp.framework.State;
import com.shoppingapp.utility.ConsoleExtras;

public class ReturnMenu extends State {
	
	Invoice invoice = null;

	public ReturnMenu() {
		super("ReturnMenu");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void update() {
		if(invoice == null) {
			// print invoices
			User user = (User) controller.persistData.get("user");
			startPrint();
			print("Invoice No.", "Date", "Can Return");
			for(Invoice invoice : user.purchases) {
				boolean valid = ChronoUnit.DAYS.between(invoice.date, LocalDate.now()) <= 15;
				print(Integer.toString(invoice.invoiceNum),
						invoice.date.toString(),
						(valid ? "YES" : "NO"));
			}
			endPrint();
			
			// get invoice number
			int invoiceNo = getInt("Invoice Number");
			if(invoiceNo == -1) {
				changeState("UserMenu");
				return;
			}
			invoice = null;
			for(int i = 0; i < user.purchases.size(); ++i) {
				invoice = user.purchases.get(i);
				if(invoice.invoiceNum == invoiceNo)
					break;
				invoice = null;
			}
			
			// check invoice number validity
			if(invoice == null) {
				System.out.println("No such invoice exists");
				return;
			}
			else if(ChronoUnit.DAYS.between(invoice.date, LocalDate.now()) > 15) {
				System.out.println("This invoice is over 15 days old, and is invalid for returns.");
				invoice = null;
				return;
			}
			
			// print invoice contents
			startPrint();
			print("Customer:", invoice.custName);
			print("Date:", invoice.date.toString());
			print("Invoice Number:", Integer.toString(invoice.invoiceNum));
			print("");
			print("Item", "Item Code", "Price");
			for(Item item : invoice.items)
				print(item.name, item.code, "$" + ConsoleExtras.parseAmount(item.price));
			endPrint();
		}
		
		// get item code
		String itemCode = getString("Item Code");
		if(itemCode.equals("-1")) {
			changeState("UserMenu");
			return;
		}
		Item item = null;
		for(int i = 0; i < invoice.items.size(); ++i) {
			item = invoice.items.get(i);
			if(item.code.contentEquals(itemCode)) {
				invoice.items.remove(i);
				System.out.println("Success");
				break;
			}
			item = null;
		}
		if(item == null) {
			System.out.println("Invalid Item Code");
			return;
		}
		changeState("UserMenu");
		
	}

	@Override
	protected void exit() {
		invoice = null;
	}

}
