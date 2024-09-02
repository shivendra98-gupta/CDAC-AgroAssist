package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CartItemDto;
import com.app.dto.ProductDto;
import com.app.entities.Cart;
import com.app.service.CartServiceInterface;

@RestController
@RequestMapping("/carts")
public class CartController {
   
	@Autowired
	private CartServiceInterface cartService;
	
	//Get Cart Of The Logged In User : Used By User
	@GetMapping("/{userId}")
	public ResponseEntity<?> getCartByUserId(@PathVariable("userId") long userId){
		Cart cart = cartService.getCartByUserId(userId);
		return ResponseEntity.ok(cart);
	}
	
	@GetMapping("products/{cartId}")
	public ResponseEntity<?> getProductInCart(Long cartId)
	{
		List<ProductDto> productDto = cartService.getProductsInCart(cartId);
		return ResponseEntity.ok(productDto);
	}
	
	
	//Method To Save The Items In Cart
	@PostMapping("/{userId}")
	public ResponseEntity<?> saveCartByUserId(@RequestBody CartItemDto cartItem,@PathVariable("userId") long userId){
		Cart savedCart = cartService.addItemInCart(userId, cartItem);
		return ResponseEntity.ok(savedCart);
	}
	
	//Method To Update Cart Items
	@PutMapping("/{userId}")
	public ResponseEntity<?> updateProductInCart(@PathVariable("userId") long userId, @RequestBody CartItemDto cartItemDto ) {
		Cart updateCart = cartService.addItemInCart(userId, cartItemDto);
		return ResponseEntity.ok(updateCart);
	}
	
	//Method To Remove Cart Items
	@PutMapping("remove/{userId}")
	public ResponseEntity<?> removeProductInCart(@PathVariable("userId") long userId, @RequestBody CartItemDto cartItemDto ) {
		Cart cart = cartService.removeItemInCart(userId,cartItemDto);
		return ResponseEntity.ok("Cart updated With Id "+cart.getId()+" !!!! Removed Item!!! With Id "+cartItemDto.getCartItemId());
	}
	
}
