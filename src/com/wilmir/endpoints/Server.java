package com.wilmir.endpoints;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.wilmir.calculator.DistCalc;
import com.wilmir.calculator.Observer;
import com.wilmir.restaurant.Order;
import com.wilmir.restaurant.OrderItem;
import com.wilmir.restaurant.Restaurant;

public class Server {
	public static final int PORT = 9000;
	static Restaurant restaurant = new Restaurant();
	static Observer distanceCalculator = new DistCalc();


	public static void main(String[] args) throws IOException, ClassNotFoundException {
		new Server().runServer();
		
	}
	
	public void runServer() throws IOException, ClassNotFoundException {
		ServerSocket serverSocket= new ServerSocket(PORT);
		System.out.println("Server is up and ready for connections");
		
		// The restaurant adds DistCalc to its subscribers
		// The DistCalc object subscribes to restaurant
		restaurant.subscribe(distanceCalculator);
		distanceCalculator.subscribe(restaurant);
		
		while(true) {
			Socket socket = serverSocket.accept();
			
			ClientHandler task = new ClientHandler(socket, restaurant);
			
			new Thread(task).start();
			
		}	
	}

}
