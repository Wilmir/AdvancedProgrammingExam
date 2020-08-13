package com.wilmir.endpoints;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

import com.wilmir.restaurant.Food;
import com.wilmir.restaurant.Order;
import com.wilmir.restaurant.OrderItem;
import com.wilmir.restaurant.Restaurant;

public class Client {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Socket clientSocket = new Socket("localhost", Server.PORT);
			
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
		
		// Retrieves the menu
		List<Food> menu = Restaurant.getMenu();
		
		Order order = new Order();
		getOrder(order, menu);
		
		objectOutputStream.writeObject(order);
		Order orderResponse = (Order)objectInputStream.readObject();
		
		printOrder(orderResponse);
		
		clientSocket.close();
	}


	
	private static void getOrder(Order order, List<Food> menu) {
		System.out.print("\nEnter the destination: ");
		String destination = sc.next();
		order.setDestination(destination);
		
		String control = "y";
		while(!control.equalsIgnoreCase("n")) {
			System.out.print("\nEnter item id: ");
			int orderId = sc.nextInt();
			
			System.out.print("How many Item#" + orderId + " do you wish to order? ");
			int quantity = sc.nextInt();
			
			OrderItem item = new OrderItem(menu.get(orderId-1), quantity);
			order.addOrderItem(item);
			
			System.out.print("Do you want to order another item? (y/n) ");
			control = sc.next();
		}
	}
	
	private static void printOrder(Order orderResponse) {
		System.out.println("\nTotal Price:" + orderResponse.getTotalPrice());
		System.out.println("Destination: House " + orderResponse.getDestination());
		System.out.println("Distance to destination: " + orderResponse.getDistance());
		
		System.out.println("\n************* Order Details *************");
		for(OrderItem item : orderResponse.getOrderitems()) {
			System.out.println(item.getFood() + " Qty: " +  item.getQuantity() + " Subtotal: " + item.getSubtotal());
		}
		
	}
}
