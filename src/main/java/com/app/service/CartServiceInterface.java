package com.app.service;

import java.util.List;

import com.app.dto.CartItemDto;
import com.app.dto.ProductDto;
import com.app.entities.Cart;

public interface CartServiceInterface {
   
	
	//Method To Get The Cart By Cart ID
	Cart getCartByCartId(long id);
	
	
	//Method To Get The Cart Of Logged In User
	Cart getCartByUserId(long id);
	
	
	//Method To Add New Items To Cart 
	Cart addItemInCart(long userId, CartItemDto cartItemDto);
	
	
	//Method To Remove Items From Cart 
	Cart removeItemInCart(long userId, CartItemDto cartItemDto);
	
	 List<ProductDto> getProductsInCart(Long cartId);
	
}
