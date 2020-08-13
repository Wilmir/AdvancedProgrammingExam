package com.wilmir.restaurant;

import java.io.Serializable;

public class Food implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private double price;
	
	public Food () {}
	
	public Food(int id, double price) {
		this.id = id;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", price=" + price + "]";
	}
}
