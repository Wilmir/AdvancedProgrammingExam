package com.wilmir.endpoints;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.wilmir.calculator.DistCalc;
import com.wilmir.calculator.Observer;
import com.wilmir.restaurant.Order;
import com.wilmir.restaurant.OrderItem;
import com.wilmir.restaurant.Restaurant;

// Thread class that handles new client connection
public class ClientHandler implements Runnable{
	private Socket socket;
	private Restaurant restaurant;
	
	public ClientHandler(Socket socket, Restaurant restaurant) {
		this.socket = socket;
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		ObjectInputStream objectInputStream;
		ObjectOutputStream objectOutputStream;
		
		//Read the order from the client
		Order order = new Order();
		
		try {
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			order = (Order)objectInputStream.readObject();
			
			restaurant.acceptOrder(order);
			calculateTotalPrice(order);
			
			System.out.println("The server complete the calculation: ");
			System.out.println("Total Price: " + order.getTotalPrice());
			System.out.println("Distance: " + order.getDistance());
			
			//Send the order object back with the calculated total price and distance
			objectOutputStream.writeObject(order);		
			
			System.out.println("The calculation has been sent back to the client");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void calculateTotalPrice(Order order) {
		double totalPrice = 0;
		
		for(OrderItem orderItem : order.getOrderitems()) {
			totalPrice += orderItem.getSubtotal();
		}
		order.setTotalPrice(totalPrice);
	}
}
