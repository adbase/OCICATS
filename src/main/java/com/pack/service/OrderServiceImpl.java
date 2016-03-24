package com.pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.domain.Order;
import com.pack.domain.Product;
import com.pack.persistence.OrderRepository;
import com.pack.persistence.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public void processOrder(String productId, int count) {
		// TODO Auto-generated method stub
		Product product = productRepository.getProductById(productId);
		
		if(product.getUnitsInStock() < count) {
			throw new IllegalArgumentException("Out of Stock. Available Units in Stock " +
					product.getUnitsInStock());
		}else {
			product.setUnitsInStock(product.getUnitsInStock() - count);
		}
	}

	@Override
	public Long saveOrder(Order order) {
		// TODO Auto-generated method stub
		Long orderId = orderRepository.saveOrder(order);
		cartService.delete(order.getCart().getCartId());
		return orderId;
	}

}
