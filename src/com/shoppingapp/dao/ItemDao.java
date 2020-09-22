package com.shoppingapp.dao;

import java.util.ArrayList;
import java.util.List;

import com.shoppingapp.entity.Item;

public class ItemDao {
	private static ArrayList<Item> items = new ArrayList<Item>();

	public static List<Item> getItems() {
		return items;
	}
	
	public static void addItem(Item item) {
		items.add(item);
	}
}
