package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.CategoryDto;
import com.app.entities.Category;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repository.CategoryRepository;
import com.app.utils.StorageServiceInterface;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryServiceInterface {
  
	  @Autowired
	  private CategoryRepository categoryRepo;
	  
	  @Autowired
	  private StorageServiceInterface storageService;
	  
	  
	  //Implementation Of Method To Get All The Category
	  @Override
	  public List<Category> getAllCategory()
	  {
			List<Category> categoryList = categoryRepo.findAll();
			if(categoryList.isEmpty()) {
				throw new ResourceNotFoundException("Category List is Empty!!!");
			}
			return categoryList;
	  }
	  
	  
	  
	  //Implementation Of Method To Save A New Category
	  @Override 
	  public Category saveCategory(CategoryDto categoryDto)
	  {
		  Category category = new Category();
		  
		  category.setCategoryName(categoryDto.getCategoryName());
		  category.setCategoryDescription(categoryDto.getCategoryDescription());
		  
		    if (categoryDto.getCategoryImage() != null && !categoryDto.getCategoryImage().isEmpty()) {
		        String fileName = storageService.store(categoryDto.getCategoryImage());
		        category.setCategoryImage(fileName);
		    }
		    
		    return categoryRepo.save(category);
	  }
	  
	  
	  //Implementation Of Method To Delete The Category
	  @Override
	 public  ApiResponse deleteByCategoryId(long id)
	 {
		  Category category = categoryRepo.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("Category With Category Id  "+id +" Not Found "));
		  
		  categoryRepo.delete(category); 
		return new ApiResponse ("Category  with Id "+category.getId()+" Deleted Successfully ");
	 }
	  
	 //Implementation Of Method To Find Category By Id
	 @Override
	 public Category findByCategoryId(long id)
	 {
			Category category = categoryRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Category With Category Id "+id +" Not Found"));
			return category;
	 }
}
