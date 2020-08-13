package com.wilmir.restaurant;

import java.util.ArrayList;
import java.util.List;

import com.wilmir.calculator.Observer;

public class Restaurant {
	private List<Observer> subs = new ArrayList<>();
	private List<Order> orders = new ArrayList<>();
	
	public static List<Food> getMenu(){
		List<Food> items = new ArrayList<>();
		items.add(new Food(1,4.50));
		items.add(new Food(2,6.90));
		items.add(new Food(3,8.50));
		items.add(new Food(4,7.20));
		items.add(new Food(5,10.20));
		items.add(new Food(6,2.40));
		items.add(new Food(7,8.80));
		items.add(new Food(8,9.10));
		items.add(new Food(9,11.40));
		items.add(new Food(10,15.80));
		return items;		
	}
	
	public void subscribe(Observer sub) {
		subs.add(sub);
	}
	
	public void unSubscribe(Observer sub) {
		subs.remove(sub);
	}
	
	public void notifySubscribers(Order order) {
		for(Observer sub : subs) {
			sub.update(order);
		}
	}
	
	public void acceptOrder(Order order) {
		orders.add(order);
		notifySubscribers(order);
	}
	
}
