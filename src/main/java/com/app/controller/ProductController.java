package com.app.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.entities.Product;
import com.app.service.CategoryServiceInterface;
import com.app.service.ProductServiceInterface;


@RequestMapping("/products")
@RestController
public class ProductController {
     
	 @Autowired 
	 private ProductServiceInterface productService;
	 
	 @Autowired 
	 private CategoryServiceInterface categoryService;
	 
	 @Autowired
	 private ModelMapper modelMapper;
	 
	 
     //API End Point To Get All The Product ("/products")
		@GetMapping("")
		public ResponseEntity<?>getAllProducts(){
			List<Product>productList = productService.getAllProduct();
			return ResponseEntity.ok(productList);
		}
		
		
	//API End Point To Get Product By Product ID ("/products/id")
		@GetMapping("/{id}")
		public ResponseEntity<?> findProductById(@PathVariable("id") Long  id) {
			Product product = productService.findByProductId(id);
			return ResponseEntity.ok(product);
		}
   
		
	//API End Point To Get All Products By Category ID ("/products/category/{categoryId}")
		@GetMapping("/category/{categoryId}")
		public ResponseEntity<?>getAllProductsByCategoryId(@PathVariable Long  categoryId){
			List<Product>productList = productService.findProductsByCategoryId(categoryId);
			return ResponseEntity.ok(productList);
		}
		
		
	//API End Point To Get All Products By Category Name ("products/category_name/{categoryName}")
		@GetMapping("/category_name/{categoryName}")
		public ResponseEntity<?>getAllProductsByCategoryName(@PathVariable String  categoryName){
			List<Product>productList = productService.findProductsByCategoryName(categoryName);
			return ResponseEntity.ok(productList);
		}
		
    
    //API End Point To Create A New Product with image ("/products")
		@PostMapping("")
		public ResponseEntity<?> saveProductWithImage(ProductDto productDto){
			Product savedProduct= productService.saveNewProduct(productDto);
			return ResponseEntity.ok(savedProduct);
		}
		
    //ERROR (Deleted Automatically)
    //API End Point To Update The Product 
		@PutMapping("/{id}")
		public ResponseEntity<?> updateProduct(ProductDto productDto,@PathVariable("id") Long id)
		{
			Product savedProduct= productService.updateProduct(productDto,id);
			return ResponseEntity.ok(savedProduct);
		}
		
		
	//API End Point To Delete Product By Product Id
		@DeleteMapping("/{id}")
		public ApiResponse deleteProductById(@PathVariable("id") Long id) {
		    ApiResponse response = productService.deleteByProductId(id);
		    return response;
		}
	
		
	//API End Point To Delete Product By Product Name
		@DeleteMapping("/name/{name}")
		public ApiResponse deleteProductByName(@PathVariable("name") String name) {
			List<Product> products = productService.findAllByProductName(name);
			for (Product product : products) {
				long id = product.getId();
				productService.deleteByProductId(id);
			}
			return new ApiResponse("All Products With Name  "+name +"Deleted Successfully!!!");
		}
		
		
		
		
}
