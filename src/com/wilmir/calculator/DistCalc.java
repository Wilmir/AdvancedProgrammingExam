package com.wilmir.calculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.wilmir.neighborhood.Graph;
import com.wilmir.neighborhood.House;
import com.wilmir.restaurant.Order;
import com.wilmir.restaurant.Restaurant;

/* The DistCalc implementation uses the Observer Design Pattern
 * In this implem, the DistCalc object subscribes to a restaurant
 * It will update the distance everytime a new order is accepted by the restaurant
 * DISTANCE CALCULATION:
 * The distance returned is obtained by detemining the minimum path between the source and destination
 * The algorithm used to determine the minimum distance is Single Source Shortest Path using Dijkstra's Algorithm
 */
public class DistCalc implements Observer{
	private Restaurant restaurant;
	private Graph neighborhood = new Graph();
	
	@Override
	public void subscribe(Restaurant res) {
		restaurant = res;
	}
	
	@Override
	public void update(Order order) {
		System.out.println("\nDistCalc received an update from the restaurant. New order's destination is: " + order.getDestination());
		
		// Set source as House A
		House source = neighborhood.getHouses().get(0);
		
		// Get the destination based on passed destination Name in the argument
		int destinationIndex = order.getDestination().charAt(0) - 65;		
		House destination = neighborhood.getHouses().get(destinationIndex);
		
		// Parse CSV file and add roads between houses
		try {
			readCSVFileThenAddRoads();
		} catch (FileNotFoundException e) {
			System.out.println("Error: File Not Found");
		} catch (IOException e) {
			System.out.println("I/O error");
		}
		
		// get the minimum distance between source and destination
		order.setDistance(neighborhood.getDistance(source, destination));
	}	
	
	//parse the CSV file and add roads between the houses
	private void readCSVFileThenAddRoads() throws FileNotFoundException, IOException {
		
		String path ="/Users/wilmirnicanor/eclipse-workspace/AdvancedProgrammingFinalExam/src/com/wilmir/restaurant/sunflower.csv";
		String linkData = "";
		
		File file = new File(path);
		
		if(file.exists()) {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while((linkData = br.readLine())!= null) {
				addRoads(linkData);
			}
		}else {
			throw new FileNotFoundException("File not found");
		}
	}
	
	
	//helper method to add roads in the houses based on the parsed CSV line
	private void addRoads(String linkData) {
		String[] values = linkData.split(",");				
		int firstHouseIndex = values[0].charAt(0) - 65;
		int secondHouseIndex = values[1].charAt(0) - 65;
		int distance = Integer.parseInt(values[2]);
		
		neighborhood.addLinks(firstHouseIndex, secondHouseIndex, distance);
	}

}
