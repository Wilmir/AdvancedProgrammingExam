package com.wilmir.calculator;

import com.wilmir.restaurant.Order;
import com.wilmir.restaurant.Restaurant;


// interface for any subscriber
public interface Observer {
	void update(Order order);
	
	void subscribe(Restaurant res);
}
