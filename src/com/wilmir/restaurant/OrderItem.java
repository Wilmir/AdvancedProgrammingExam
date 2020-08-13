package com.wilmir.restaurant;

import java.io.Serializable;

public class OrderItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Food food;
	
	private int quantity;
	
	private double subtotal;

	public OrderItem() {}
	
	public OrderItem(Food food, int quantity) {
		this.food = food;
		this.quantity = quantity;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubtotal() {
		return this.food.getPrice() * this.quantity;
	}
	
}
