package com.pack.persistence;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pack.domain.Order;

@Repository
public class InMemoryOrderRepository implements OrderRepository{

	private Map<Long, Order> listOfOrders;
	private long nextOrderId;
	
	public InMemoryOrderRepository() {
		listOfOrders = new HashMap<>();
		nextOrderId = 1000;
	}
	
	@Override
	public Long saveOrder(Order order) {
		// TODO Auto-generated method stub
		order.setOrderId(getNextOrderId());
		listOfOrders.put(order.getOrderId(), order);
		return order.getOrderId();
	}
	
	private synchronized long getNextOrderId() {
		return nextOrderId++;
	}

}
