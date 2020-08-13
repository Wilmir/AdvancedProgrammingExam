package com.wilmir.restaurant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OrderItem> orderitems;
	private String destination;
	private int distance;
	private double totalPrice;
	
	public Order() {
		orderitems = new ArrayList<>();
	}

	public List<OrderItem> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(List<OrderItem> orderitems) {
		this.orderitems = orderitems;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public void addOrderItem(OrderItem orderItem) {
		if(orderitems == null) {
			orderitems = new ArrayList<>();
		}
		
		orderitems.add(orderItem);
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
