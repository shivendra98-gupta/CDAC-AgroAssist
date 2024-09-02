package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.CartItemDto;
import com.app.dto.ProductDto;
import com.app.entities.Cart;
import com.app.entities.CartItem;
import com.app.entities.User;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repository.CartItemRepository;
import com.app.repository.CartRepository;

@Service
@Transactional
public class CartServiceImpl implements CartServiceInterface {
   
	 @Autowired 
	 private CartRepository cartRepo;
	 
	 @Autowired
	 private UserServiceInterface userService;
	 
	 @Autowired 
	 private ProductServiceInterface productService;
	 
	 @Autowired 
	 private CartItemRepository cartItemRepo;
	 
	 @Autowired 
	 private ModelMapper mapper;
	 
	//Implementation Of Get Cart By Cart ID 
		@Override
		public Cart getCartByCartId(long id) {
			    Cart cart = cartRepo.findById(id)
			    		.orElseThrow(()-> new ResourceNotFoundException("Cart with Id "+id+" Not Found!!"));
			return cart;
		}
	 
	 //Implementation Of Get Cart By User ID 
	 @Override
	 public Cart getCartByUserId(long id)
	 {
		 User user = userService.findUserById(id);
		 Cart cart = user.getCart();
		 return cart;
	 }
	 
     //Implementation Of Method To Add Items In The Cart
	 @Override 
	 public Cart addItemInCart(long userId, CartItemDto cartItemDto)
	 {
		 User user = userService.findUserById(userId);
		 Cart cart = user.getCart();
		 
		 CartItem cartItem = new CartItem();
		 
		 cartItem.setCart(cart);
		 cartItem.setProduct(productService.findByProductId(cartItemDto.getProductId()));
		 cartItem.setProductQuantity(cartItemDto.getProductQuantity());
		 
		 List<CartItem> cartItemsList = cart.getCartItems();
		  cartItemsList.add(cartItem);
		 
		  cart.setCartItems(cartItemsList);
		  
		 return  cartRepo.save(cart);
	 }
	 
	 
		@Override
		public Cart removeItemInCart(long userId, CartItemDto cartItemDto) {
			 User user = userService.findUserById(userId);
			 Cart cart = user.getCart();
			 
			 cart.getCartItems().forEach(item -> {
				 if(item.getProduct().getId()==cartItemDto.getProductId()) {
					 cartItemRepo.deleteById(item.getId());
				 }
			 });
			 
			 cart.getCartItems().removeIf(item -> (item.getProduct().getId()==cartItemDto.getProductId()));
			 Cart updatedCart = cartRepo.save(cart);
			 
			return updatedCart;
		}
		
		@Override
		public List<ProductDto> getProductsInCart(Long cartId) {
		    Cart cart = cartRepo.findById(cartId)
		            .orElseThrow(() -> new ResourceNotFoundException("Cart with Id " + cartId + " Not found!!"));

		    // Initialize the cart items to avoid LazyInitializationException
		    List<CartItem> cartItems = cart.getCartItems();
		   // Hibernate.initialize(cartItems);

		    // Map CartItems to ProductDtos
		    List<ProductDto> productsInCart = cartItems.stream()
		            .map(cartItem -> mapper.map(cartItem.getProduct(), ProductDto.class))
		            .collect(Collectors.toList());

		    return productsInCart;
		}
	  
	 
}
