package com.pack.service;

import com.pack.domain.Order;

public interface OrderService {
	public void processOrder(String productId, int count);
	
	Long saveOrder(Order order);
}
