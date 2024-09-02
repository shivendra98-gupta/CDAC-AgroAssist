package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Category;
import com.app.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
   
	//Method To Find The Product By ID
	Optional<Product> findById(Long id);

	//Method To Find The Product By Name
	Optional<Product> findByProductName(String name);
	
	//Method To Find All The Product By Name
	List<Product> findAllByProductName(String name);
	
	//Method To Find All The Product By Category
	List<Product> findAllByCategory(Category category);
	
	//Method To Find All The Product 
	List<Product> findAll();
		
	
}
