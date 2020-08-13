package com.wilmir.neighborhood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


// The model for the house
public class House implements Comparable<House>{
	private char name;
	private List<House> neighbors = new ArrayList<>();
	private HashMap<House, Integer> distanceMap = new HashMap<House, Integer>();
	private boolean visited = false;
	private House parent;
	private int distance;
	
	public House(char name) {
		this.name = name;
		this.distance = Integer.MAX_VALUE;
	}

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public List<House> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(List<House> neighbor) {
		this.neighbors = neighbor;
	}

	public HashMap<House, Integer> getDistanceMap() {
		return distanceMap;
	}

	public void setDistanceMap(HashMap<House, Integer> distanceMap) {
		this.distanceMap = distanceMap;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public House getParent() {
		return parent;
	}

	public void setParent(House parent) {
		this.parent = parent;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int weight) {
		this.distance = weight;
	}

	@Override
	public String toString() {
		return "House [name=" + name + "]";
	}

	// used by priority queue to compare houses
	@Override
	public int compareTo(House o) {
		return this.distance - o.distance;
	}
	
	
}
