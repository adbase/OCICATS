package com.pack.persistence;

import com.pack.domain.Order;

public interface OrderRepository {
	Long saveOrder(Order order);
}
