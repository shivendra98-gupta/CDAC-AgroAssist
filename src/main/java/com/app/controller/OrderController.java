package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.OrderDto;
import com.app.dto.OrderItemDto;
import com.app.dto.ProductDto;
import com.app.service.OrderServiceInterface;

@RestController
@RequestMapping("/orders")
public class OrderController {
   
	@Autowired
	private OrderServiceInterface orderService;
	

//API End Point To Get Order By Order ID : 
	@GetMapping("/{orderId}")
	public ResponseEntity<?> getOrderByOrderId(@PathVariable("orderId") Long orderId){
		return ResponseEntity.ok(orderService.getOrderByOrderId(orderId));
	}	
	

//API End Point To Get All The Orders Items By Order ID	
	@GetMapping("all_orderitems/{orderId}")
	public ResponseEntity<?> getAllOrderItemsByOrderId(@PathVariable("orderId") Long orderId){
		List<OrderItemDto> ordersItems = orderService.getAllOrderItemsByOrderId(orderId);
		return ResponseEntity.ok(ordersItems);
	}
	
	
//API End Points To Get All The Orders Made By User ID 
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getAllOrdersByUserId(@PathVariable("userId") Long userId){
		List<OrderDto> orders = orderService.getAllOrdersOfUserById(userId);
		return ResponseEntity.ok(orders);
	}
	
//API End Point To Get All Product Ordered By Ordered Id
	@GetMapping("/products/{orderId}")
	public ResponseEntity<?> getAllProductByOrderId(@PathVariable("orderId") Long orderId){
		List<ProductDto> products = orderService.getAllProductOrderedByOrderId(orderId);
		return ResponseEntity.ok(products);
	}
	

//API End Points To Add a New Order By UserId
	@PostMapping("/{userId}")
	public ResponseEntity<?> addNewOrder(@PathVariable("userId") Long userId){
		OrderDto order = orderService.createNewOrderByUserId(userId);
		return ResponseEntity.ok(order);
	}


//API End Point To Delete An Order By OrderID
	@DeleteMapping("/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable("orderId") Long orderId){
		ApiResponse response = orderService.deleteOrderByUserId(orderId);
		return ResponseEntity.ok(response);
	}
		
}
