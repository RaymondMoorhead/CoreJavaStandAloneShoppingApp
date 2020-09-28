package com.shoppingapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shoppingapp.entity.Invoice;
import com.shoppingapp.entity.Item;
import com.shoppingapp.entity.User;

public class DaoManager {
	
	public static boolean useLocal = true;
	
	private static final String username = "root";
	private static final String password = "root";
	private static final String url = "jdbc:mysql://localhost";
	private static final String options = "useSSL=false";
	private static final String dbName = "shopping_app";
	private static final String driver = "com.mysql.jdbc.Driver";
	
	public static void initializeRemote() {
		useLocal = false;
		try {
			createDatabaseIfMissing();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ItemDao.initialize();
		UserDao.initialize();
	}
	
	public static void initializeLocal() {
		useLocal = true;
		
		// items for sale
		Item debugItem1 = new Item("Jacket", "Ja1", 2000);
		Item debugItem2 = new Item("Jeans", "Je1", 1000);
		Item debugItem3 = new Item("Shirt", "Sh1", 500);
		ItemDao.addItem(debugItem1);
		ItemDao.addItem(debugItem2);
		ItemDao.addItem(debugItem3);
		
		// user
		User debugUser = new User(0, "TestUser", "TestPassword", "mail@mail.com");
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
	}
	
	public static void createDatabaseIfMissing() throws SQLException, ClassNotFoundException{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url + "?" + options, username, password);
		ResultSet resultSet = conn.getMetaData().getCatalogs();
		boolean found = false;

		while (resultSet.next()) {
			// Get the database name, which is at position 1
			if(dbName.equals(resultSet.getString(1))) {
				found = true;
				break;
			}
		}
		resultSet.close();
		
		if(!found) {
			PreparedStatement stmt = conn.prepareStatement("create database ?");
			stmt.setString(1, dbName);
			stmt.executeUpdate();
		}
	}
	
	public static void createTableIfMissing(String table, String values) throws SQLException{
		Connection conn = getConnection();
		ResultSet resultSet = conn.getMetaData().getTables(null, null, table, new String[] {"TABLE"});;

		if (!resultSet.next()) {
			String sql = "create table " + table + "(" + values + ")";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		resultSet.close();
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url + "/" + dbName + "?" + options, username, password);
		} catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}
}
