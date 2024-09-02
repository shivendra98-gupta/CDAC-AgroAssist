package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.CategoryDto;
import com.app.entities.Category;

public interface CategoryServiceInterface {
   
	//Method To Get All The Categories
	List<Category> getAllCategory();
	
	//Method To Save A New Category
	Category saveCategory(CategoryDto categoryDto);
	
	//Method To Delete A Category 
	ApiResponse deleteByCategoryId(long id);
	
	//Method To Find Category By ID
	Category findByCategoryId(long id);
}
