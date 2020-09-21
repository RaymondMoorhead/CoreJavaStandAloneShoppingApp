package com.shoppingapp.framework;

import java.util.ArrayList;
import java.util.Scanner;

import com.shoppingapp.utility.ConsoleExtras;

public abstract class State {
	
	// TO OVERRIDE
	
	protected State(String name) {this.name = name; input = new Scanner(System.in);}
	
	protected abstract void initialize();
	protected abstract void update();
	protected abstract void exit();
	
	// INTERFACE FOR EXTENDED CLASSES
	
	protected StateController controller;
	protected Scanner input;
	
	protected void changeState(String name) {
		controller.changeState(name);
	}
	
	protected void shutdown() {
		controller.changeState(null);
		System.out.println("Goodbye");
	}
	
	protected void startPrint() {
		// clear screen (require support for ANSI escape codes)
	    System.out.print(ConsoleExtras.ANSI_CLEAR);  
	    System.out.flush();  
	    
	    System.out.println("    Standalone Ecommerce App");
		System.out.println("+===============================================+"); // 47 '='
		options.clear();
	}
	
	protected void printOp(String toState, String... data) {
		options.add(toState);
		data[0] = Integer.toString(options.size()) + '.' + data[0];
		print(data);
	}
	
	protected void print(String... data) {
		switch(data.length) {
			case 1:
				System.out.printf("|   %-41s   |\n", data[0]);
				break;
			case 2:
				System.out.printf("|   %-20s %-20s   |\n", data[0], data[1]);
				break;
			case 3:
				System.out.printf("|   %-13s %-13s %-13s   |\n", data[0], data[1], data[2]);
				break;
			default:
				System.out.println("Invalid number of State.print parameters");
				break;
		}
	}
	
	protected void inputOp() {
		System.out.printf("Please select an option %d-%d: ", 1, options.size());
		int result = input.nextInt();
		if(result >= 1 && result <= options.size())
			changeState(options.get(result - 1));
	}
	
	protected void endPrint() {
		System.out.println("+===============================================+\n"); // 47 '='
	}
	
	protected int getInt(String request) {
		System.out.printf("%s : ", request);
		return input.nextInt();
	}
	
	protected String getString(String request) {
		System.out.printf("%s : ", request);
		return input.next();
	}
	
	protected String pause() {
		System.out.println("Please Enter to continue");
		return input.nextLine();
	}
	
	// INTERFACE FOR CONTROLLER
	
	public final String name;
	
	// PRIVATE
	private ArrayList<String> options = new ArrayList<String>();
}
