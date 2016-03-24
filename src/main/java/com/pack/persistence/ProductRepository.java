package com.pack.persistence;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pack.domain.Product;

public interface ProductRepository {
	public List<Product> getAllProducts();
	
	public Product getProductById(String productId);
	
	public List<Product> getProductsByCategory(String category);
	
	public List<Product> getProductsByManufacturer(String manufacturer);
	
	public Set<Product> getProductsByFilter(Map<String, List<String>> map);
	
	public Set<Product> getProductsByPriceFilter(Map<String, List<String>> priceFilter);
	
	public void addProduct(Product product);
}
