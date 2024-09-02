package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.entities.Product;

public interface ProductServiceInterface {
  
	List<Product>getAllProduct();
	
	List<Product> findAllByProductName(String name);
	
	Product findByProductId(Long id);
	
	Product findByProductName(String name);
	
	List<Product> findProductsByCategoryId(Long catId);
	
	List<Product> findProductsByCategoryName(String catName);
	
	Product saveNewProduct (ProductDto product);
	
	Product updateProduct(ProductDto productDto,Long Id);
	
	ApiResponse deleteByProductId(Long id);
	
	ApiResponse deleteByProductName(String name);
}
