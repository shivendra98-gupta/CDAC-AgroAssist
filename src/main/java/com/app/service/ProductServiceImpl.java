package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.entities.Category;
import com.app.entities.Product;
import com.app.exceptions.ProductHandlingException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repository.CategoryRepository;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;
import com.app.utils.StorageServiceInterface;

@Service
@Transactional
public class ProductServiceImpl implements ProductServiceInterface {

	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private StorageServiceInterface storageService;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired 
	private UserRepository userRepo;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	
	//Method Implementation To Get All The Product 
	@Override
	public List<Product> getAllProduct() {
		List<Product> productList = productRepo.findAll();
		if(productList.isEmpty()) {
			throw new ProductHandlingException("Product List is Empty!!!");
		}
		return productList;
	}

	//Method Implementation To Find All Product By Name
	@Override
	public List<Product> findAllByProductName(String name) {
		List<Product> productList = productRepo.findAllByProductName(name);
		if(productList.isEmpty()) {
			throw new ProductHandlingException("Product List is Empty!!!");
		}
		return productList;
	}
	
	
	//Method Implementation To Find Product By Id
	@Override
	public Product findByProductId(Long id) {
		Product product  = productRepo.findById(id)
				.orElseThrow(()-> new ProductHandlingException("Product with Id "+id+" Not found!!"));
		return product;
	}
	
	//Method Implementation To Find Product By Name
	@Override
	public Product findByProductName(String name) {
		Product product = productRepo.findByProductName(name)
				.orElseThrow(()-> new ProductHandlingException("Product with Name "+name+" Not found!!"));
		return product;
	}
	
	//Method Implementation To Find Product By Category ID 
	@Override
	public List<Product> findProductsByCategoryId(Long catId) {
		  Category category = categoryRepo.findById(catId)
				  .orElseThrow(()-> new ResourceNotFoundException("Category Id with Id"+catId+" Not Found!!!"));
		  List<Product> products = productRepo.findAllByCategory(category);
		return products;
	}
	
	//Method To Get All Products By Category Name
	@Override
	public List<Product> findProductsByCategoryName(String catName)
	{
		Category category = categoryRepo.findByCategoryName(catName)
				.orElseThrow(()-> new ResourceNotFoundException("Category Name "+catName+" Not Exist"));
		
		return category.getProducts();
	}
	
	
	//Method To Add A New Products
	@Override
	public Product saveNewProduct(ProductDto productDto) {
	    
		Category category = categoryRepo.findByCategoryName(productDto.getCategoryName())
				.orElseThrow(()-> new ResourceNotFoundException("Category Name "+productDto.getCategoryName()+" Not Exist"));
		
		Product product = modelMapper.map(productDto, Product.class);
		
		String fileName = storageService.store(productDto.getProductImage());
		product.setProductImage(fileName);
		
		//Using Helper Method To Add The Newly Created Product To Category
		category.addProduct(product);
		//Save The Changes
		//categoryRepo.save(category);
		return productRepo.save(product);
		
	}
	
	@Override
	public Product updateProduct(ProductDto productDto,Long Id)
	{
		Product product  = productRepo.findById(Id)
				.orElseThrow(()-> new ProductHandlingException("Product with Id "+Id+" Not found!!"));
		
		
		product.setProductName(productDto.getProductName());
		
		
		product.setProductDescription(productDto.getProductDescription());
		
		product.setQuantity(productDto.getQuantity());
		
		product.setProductDiscount(productDto.getProductDiscount());
		
		product.setProductPrice(productDto.getProductPrice());
		
		product.setProductRating(productDto.getProductRating());
		
		if(productDto.getCategoryName() != null)
		{
		//Update the Category of The Product 
		Category category = categoryRepo.findByCategoryName(productDto.getCategoryName())
				.orElseThrow(()-> new ResourceNotFoundException("Category Name "+productDto.getCategoryName()+" Not Exist"));
		
		//Category categoryPrev = categoryRepo.findByCategoryName(product.get)
		
		//First Remove The Product From Previous Category 
		product.removeCategory();
		//Add the New Category To The Product 
		category.addProduct(product);
		categoryRepo.save(category);
		}
		//Update The Image
		if(productDto.getProductImage() != null)
		{
		String fileName = storageService.store(productDto.getProductImage());
		product.setProductImage(fileName);
		}
		
		return productRepo.save(product);
		
	}
	
	
	//Method To Remove A Product By Id
	@Override
	public ApiResponse deleteByProductId(Long id) {
		Product product = findByProductId(id);
		
		//Testing For OrphanRemoval By Setting The Produc's Category field As NULL
		product.removeCategory();
		
		productRepo.delete(product);
		return new ApiResponse("Product With Id "+id+"Deleted Successfully!!!!");
	}

	
	//Method To Remove A Product By Product Name
	@Override
	public ApiResponse deleteByProductName(String name) {
		Product product = productRepo.findByProductName(name)
				.orElseThrow(()-> new ProductHandlingException("Product with Name "+name+" Not found!!"));
		
		//Testing For OrphanRemoval By Setting The Produc's Category field As NULL
		product.removeCategory();
		
		productRepo.delete(product);
		return new ApiResponse("Product With Name "+name+"Deleted Successfully!!!!");
	}
	
	
	

}
