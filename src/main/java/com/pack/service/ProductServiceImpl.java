package com.pack.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.domain.Product;
import com.pack.persistence.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productReposity;
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productReposity.getAllProducts();
	}

	@Override
	public Product getProdcutById(String productId) {
		// TODO Auto-generated method stub
		return productReposity.getProductById(productId);
	}

	@Override
	public List<Product> getProductsByCategory(String Category) {
		// TODO Auto-generated method stub
		return productReposity.getProductsByCategory(Category);
	}

	@Override
	public Set<Product> getProductsByFilter(Map<String, List<String>> map) {
		// TODO Auto-generated method stub
		return productReposity.getProductsByFilter(map);
	}

	@Override
	public List<Product> getProductsByManufacturer(String manufacturer) {
		// TODO Auto-generated method stub
		return productReposity.getProductsByManufacturer(manufacturer);
	}

	@Override
	public Set<Product> getProductsByPriceFilter(Map<String, List<String>> priceFilter) {
		// TODO Auto-generated method stub
		return productReposity.getProductsByPriceFilter(priceFilter);
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		productReposity.addProduct(product);
	}

}
