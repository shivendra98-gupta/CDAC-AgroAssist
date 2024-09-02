package com.app.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CategoryDto;
import com.app.entities.Category;
import com.app.service.CategoryServiceInterface;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
	 @Autowired
	 private CategoryServiceInterface categoryService;
	 
	 @Autowired
	 private ModelMapper modelMapper;
	 
	 
 //API Endpoint To Get All The Categories ("/category"): Used in Admin And Home Page
	 @GetMapping("") 
	 public ResponseEntity<?> findAllCategory(){
		 List<Category> categoryList = categoryService.getAllCategory();
		 return ResponseEntity.ok(categoryList);
	 }
	 
 //API Endpoint To Post The New Category ("/category") 
		@PostMapping("")
		public ResponseEntity<?> saveCategoryWithImage(CategoryDto catDto){
			Category savedCategory= categoryService.saveCategory(catDto);
			return ResponseEntity.ok(savedCategory);
		}
		
 //API Endpoint To Get Category By ID ("/category/id")
		@GetMapping("/{id}")
		public ResponseEntity<?> findByCategoryId(@PathVariable("id") long  categoryId) {
			Category category=categoryService.findByCategoryId(categoryId);
			return ResponseEntity.ok(category);
		}
	
 //API Endpoint To Delete Category By ID ("/category/id")
		@DeleteMapping("/{id}")
		public ResponseEntity<?> deleteCategoryById(@PathVariable("id") long categoryId) {
			Category category = categoryService.findByCategoryId(categoryId);
		 ApiResponse response = categoryService.deleteByCategoryId(category.getId());
		   return  ResponseEntity.status(HttpStatus.OK).body(response);
		}
	 
}
