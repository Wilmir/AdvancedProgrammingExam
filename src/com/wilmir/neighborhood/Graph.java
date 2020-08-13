package com.wilmir.neighborhood;

import java.util.ArrayList;
import java.util.PriorityQueue;

/* The model for the neighborhood
 * The neighborhood is set to have undirected weighted edges (also referred to as roads)
 */
public class Graph {
	private ArrayList<House> houses = new ArrayList<House>();
	
	public Graph() {
		// create 25 houses: A,B,C,D,E...Y
		for(int i=0;i<25; i++) {
			House house = new House((char)(65+i));
			houses.add(house);
		}
	}
	
	// Perform dijkstra to determine the shortest distance from source to destination
	public int getDistance(House source, House destination) {
		return dijkstra(source, destination);
	}

	/* Modified Dijkstra Algorithm, the algorithm stops when the minimum distance from
	 * source to destination has already been determined
	 */
	private int dijkstra(House source, House destination) {
		int totalDistance = 0;
		PriorityQueue<House> queue = new PriorityQueue<>(); //the PQueue helps in finding the node with the minimum distance
		source.setDistance(0); 
		queue.addAll(this.houses);
		
		while(!queue.isEmpty()) {
			House currentHouse = queue.remove();
			
			//check if currentNode is already the destination, so the algorithm will not need to check the distance for all houses
			if(currentHouse.getName() == (destination.getName())) {
				totalDistance = currentHouse.getDistance();
				return totalDistance;
			}
			
			for(House neighbor : currentHouse.getNeighbors()) {
				if(queue.contains(neighbor)) {
					if(neighbor.getDistance() > (currentHouse.getDistance() + currentHouse.getDistanceMap().get(neighbor))) {
						neighbor.setDistance(currentHouse.getDistance() + currentHouse.getDistanceMap().get(neighbor));
						neighbor.setParent(currentHouse);
						
						//Refresh the Priority Queue 
						queue.remove(neighbor);
						queue.add(neighbor);
					}
				}
			}
		}
		
		return totalDistance;
	}
	

	// Helper method to add undirected weighted distance from one node to another and vice versa
	public void addLinks(int i, int j, int d) {
		House first = houses.get(i);
		House second = houses.get(j);
		
		first.getNeighbors().add(second);
		first.getDistanceMap().put(second, d);

		second.getNeighbors().add(first);
		second.getDistanceMap().put(first, d);		
	}

	
	public ArrayList<House> getHouses() {
		return houses;
	}
}
