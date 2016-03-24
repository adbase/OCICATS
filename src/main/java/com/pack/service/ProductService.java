package com.pack.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pack.domain.Product;

public interface ProductService {
	public List<Product> getAllProducts();
	
	public Product getProdcutById(String productId);
	
	public List<Product> getProductsByCategory(String Category);
	
	public List<Product> getProductsByManufacturer(String manufacturer);
	
	public Set<Product> getProductsByFilter(Map<String, List<String>> map);
	
	public Set<Product> getProductsByPriceFilter(Map<String, List<String>> priceFilter);
	
	public void addProduct(Product product);
}
