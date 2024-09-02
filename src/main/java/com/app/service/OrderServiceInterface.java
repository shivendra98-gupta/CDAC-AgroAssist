package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.CartItemDto;
import com.app.dto.OrderDto;
import com.app.dto.OrderItemDto;
import com.app.dto.ProductDto;

public interface OrderServiceInterface {
	
	  OrderDto getOrderByOrderId(Long id);
	  
   
	//To Get The List Of The All The Orders Made By The UserId 
	  List<OrderDto> getAllOrdersOfUserById(Long userId);
	  
	  List<OrderItemDto> getAllOrderItemsByOrderId(Long order_id);
	  
	  List<ProductDto> getAllProductOrderedByOrderId(Long userId);
	  
	  OrderDto createNewOrderByUserId(Long user_id);
	  
	  ApiResponse deleteOrderByUserId(Long user_id);
}
