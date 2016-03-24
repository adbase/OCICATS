package com.pack.persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.pack.domain.Product;
import com.pack.exception.ProductNotFoundException;

@Repository
public class InMemoryProductRepository implements ProductRepository{
	
	private List<Product> listsOfProducts = new ArrayList<Product>();
	
	public InMemoryProductRepository() {
		Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(550));
		iphone.setDescription("Apple iPhone 5s smartphone with 4.00-inch 640x1136 display"
				+ " and 8-megapixel rear camera.");
		iphone.setCategory("smartphone");
		iphone.setManufacturer("apple");
		iphone.setUnitsInStock(1000);
		
		
		Product laptop_dell = new Product("P1235", "Dell Inspiron", new BigDecimal(700));
		laptop_dell.setDescription("Dell Inspiron 14-inch Laptop(Black) with 3ed Generation "
				+ "Intel Core processors.");
		laptop_dell.setCategory("laptop");
		laptop_dell.setManufacturer("dell");
		laptop_dell.setUnitsInStock(1000);
		
		Product table_Nexus = new Product("P1236", "Nexus 7", new BigDecimal(300));
		table_Nexus.setDescription("Google Nexus 7 is the lightest 7 inch table With "
				+ "a quad-core Qualcomm Snapdragon S4 Pro processor.");
		table_Nexus.setCategory("tablet");
		table_Nexus.setManufacturer("google");
		table_Nexus.setUnitsInStock(1000);
		
		listsOfProducts.add(iphone);
		listsOfProducts.add(laptop_dell);
		listsOfProducts.add(table_Nexus);
	}
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return listsOfProducts;
	}

	@Override
	public Product getProductById(String productId) {
		// TODO Auto-generated method stub
		for(Product product : listsOfProducts) {
			if(product != null && product.getProductId() != null && product.getProductId().equals(productId)) {
				return product;
			}
		}
		
		throw new ProductNotFoundException("No products found with the product id: " + productId);
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		// TODO Auto-generated method stub
		List<Product> productsByCategory = new ArrayList<>();
		for(Product product : listsOfProducts) {
			if(product != null && product.getCategory() != null && product.getCategory().equals(category)) {
				productsByCategory.add(product);
			}
		}
		return productsByCategory;
	}

	@Override
	public List<Product> getProductsByManufacturer(String manufacturer) {
		// TODO Auto-generated method stub
		List<Product> productsByManufacturer = new ArrayList<>();
		for(Product product : listsOfProducts) {
			if(product != null && product.getManufacturer() != null && manufacturer.equals(product.getManufacturer())) {
				productsByManufacturer.add(product);
			}
		}
		return productsByManufacturer;
	}
	
	@Override
	public Set<Product> getProductsByFilter(Map<String, List<String>> map) {
		// TODO Auto-generated method stub
		Set<Product> productsByBrand = new HashSet<>();
		Set<Product> productsByCategory = new HashSet<>();
		
		
		Set<String> criterias = map.keySet();
		
		if(criterias.contains("brand")) {
			for(String brandName : map.get("brand")) {
				for(Product product : listsOfProducts) {
					if(brandName.equals(product.getManufacturer())) {
						productsByBrand.add(product);
					}
				}
			}
		}
		
		if(criterias.contains("category")) {
			for(String category : map.get("category")) {
				productsByCategory.addAll(this.getProductsByCategory(category));
			}
		}
		
		productsByCategory.retainAll(productsByBrand);
		
		return productsByCategory;
	}

	@Override
	public Set<Product> getProductsByPriceFilter(Map<String, List<String>> priceFilter) {
		// TODO Auto-generated method stub
		Set<Product> productsByLow = new HashSet<>();
		Set<Product> productsByHigh = new HashSet<>();
		
		Set<String> criterias = priceFilter.keySet();
		
		if(criterias.contains("low")) {
			for(String price : priceFilter.get("low")) {
				BigDecimal lowPrice = new BigDecimal(price);
				for(Product product : listsOfProducts) {
					if(lowPrice.compareTo(product.getUnitPrice()) <= 0) {
						productsByLow.add(product);
					}
				}
			}
		}
		
		if(criterias.contains("high")) {
			for(String price : priceFilter.get("high")) {
				BigDecimal highPrice = new BigDecimal(price);
				for(Product product : listsOfProducts) {
					if(highPrice.compareTo(product.getUnitPrice()) >= 0) {
						productsByHigh.add(product);
					}
				}
			}
		}
		
		if(productsByHigh.isEmpty()) {
			return productsByLow;
		}
		
		if(productsByLow.isEmpty()) {
			return productsByHigh;
		}
		
		productsByHigh.retainAll(productsByLow);
		
		return productsByHigh;
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		listsOfProducts.add(product);
	}

	

}
