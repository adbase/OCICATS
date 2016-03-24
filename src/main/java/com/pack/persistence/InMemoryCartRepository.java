package com.pack.persistence;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pack.domain.Cart;

@Repository
public class InMemoryCartRepository implements CartRepository{

	private Map<String, Cart> listOfCarts;
	
	public InMemoryCartRepository() {
		listOfCarts = new HashMap<>();
	}
	
	@Override
	public Cart create(Cart cart) {
		// TODO Auto-generated method stub
		if(listOfCarts.keySet().contains(cart.getCartId())) {
			throw new IllegalArgumentException(String.format("Cannot create a cart. A cart with the give id (%)"
					+ " already exist.", cart.getCartId()));
		}
		
		listOfCarts.put(cart.getCartId(), cart);
		return cart;
	}

	@Override
	public Cart read(String cartId) {
		// TODO Auto-generated method stub
		return listOfCarts.get(cartId);
	}

	@Override
	public void update(String cartId, Cart cart) {
		// TODO Auto-generated method stub
		if(!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("Cannot update a cart. A cart with the give id (%)"
					+ " does not exist.", cart.getCartId()));
		}
		
		listOfCarts.put(cartId, cart);
	}

	@Override
	public void delete(String cartId) {
		// TODO Auto-generated method stub
		if(!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("Cannot delete a cart. A cart with the give id (%)"
					+ " does not exist.", cartId));
		}
		listOfCarts.remove(cartId);
	}

}
