package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.OrderDto;
import com.app.dto.OrderItemDto;
import com.app.dto.ProductDto;
import com.app.entities.Cart;
import com.app.entities.CartItem;
import com.app.entities.Order;
import com.app.entities.OrderItem;
import com.app.entities.User;
import com.app.exceptions.OrderNotFoundException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repository.CartRepository;
import com.app.repository.OrderRepository;
import com.app.repository.UserRepository;


@Service
@Transactional
public class OrderServiceImpl implements OrderServiceInterface {

	 @Autowired
	 private UserServiceInterface userService;
	 
	 
	 @Autowired 
	 private ProductServiceInterface productService;
	 
	 @Autowired 
	 private ModelMapper mapper;
	 
	 @Autowired 
	 private OrderRepository orderRepo;
	 
	 @Autowired 
	 private UserRepository userRepo;
	 
	 @Autowired
	 private CartRepository cartRepo;
	 
	//Checked 
	 @Override 
	 public OrderDto getOrderByOrderId(Long id) {
		 
		 Order order = orderRepo.findById(id)
					.orElseThrow(() -> new OrderNotFoundException("No Order With ID : "+id+" Found !!!"));
		 
		 OrderDto orderDto = mapper.map(order,OrderDto.class); 
		 return orderDto;
			
	 }
	 
	//Checked
	@Override
	public List<OrderDto> getAllOrdersOfUserById(Long userId) {
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with Id " + userId + " Not found!!"));
		
		List<Order> orders = user.getOrders();
		
		Hibernate.initialize(orders);
		
		List<OrderDto> ordersByUser = orders.stream()
				.map(order->mapper.map(order,OrderDto.class))
				.collect(Collectors.toList());
		
		return ordersByUser;
	}

	//Checked
	@Override
	public List<OrderItemDto> getAllOrderItemsByOrderId(Long order_id) {
	    
	 
	    Order order = orderRepo.findById(order_id)
	            .orElseThrow(() -> new OrderNotFoundException("No Order With ID : " + order_id + " Found !!!"));
	    
	   
	    List<OrderItem> orderItems = order.getOrderItems();
	    Hibernate.initialize(orderItems);
	    
	    
	    List<OrderItemDto> orderItemsDto = orderItems.stream()
	            .map(orderItem -> {
	                OrderItemDto dto = new OrderItemDto();
	                dto.setQuantity(orderItem.getQuantity());
	                dto.setProduct_id(orderItem.getProduct().getId());
	                dto.setOrder_id(orderItem.getOrder().getId());
	                
	                return dto;
	            })
	            .collect(Collectors.toList());
	    
	    return orderItemsDto;
	}

	
	
    //Checked 
	@Override
	public List<ProductDto> getAllProductOrderedByOrderId(Long orderId) {
		
		Order order = orderRepo.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException("Order with Id " + orderId + " Not found!!"));
		
		List<OrderItem> orderItems = order.getOrderItems();
		
		Hibernate.initialize(orderItems);
		
		List<ProductDto> productsInOrders = orderItems.stream()
				.map(orderItem -> mapper.map(orderItem.getProduct(),ProductDto.class))
				.collect(Collectors.toList());
		
		return productsInOrders;
	}

	@Override
	public OrderDto createNewOrderByUserId(Long user_id) {
		
		//To get random date from now upto 10 days in future
		LocalDate currentDate = LocalDate.now();
        int randomDays = ThreadLocalRandom.current().nextInt(0, 11);
        // Add the random number of days to the current date
        LocalDate randomDate = currentDate.plusDays(randomDays);
        
		
		User user = userService.findUserById(user_id);
		Cart cart = user.getCart();
		
		List<CartItem> cartItems = cart.getCartItems();
		
		Hibernate.initialize(cartItems);
		
		//Price To Pay
		Double price = cartItems.stream()
		.mapToDouble(cartItem -> (cartItem.getProduct().getProductPrice()-cartItem.getProduct().getProductDiscount()) * cartItem.getProductQuantity())
		.sum();
				
		
		Order order = new Order();
		
		//To Set The User using Helper Method
		order.assignUser(user);
		order.setDelivery_status("Not Delivered");
		order.setDeliveryDate(randomDate);
		order.setDelivery_address(user.getAddress());
		order.setTotal_amount(price);
		
		//To Add All The CartItems inside the 
		cartItems.forEach(item -> {
			order.addOrderItem(item.getProduct(), item.getProductQuantity());
		});
		
		Order saveOrder =  orderRepo.save(order);	
		
		//Clearing The Cart After Order 
	    cart.getCartItems().clear(); 
	    cartRepo.save(cart); 
		
		return mapper.map(saveOrder,OrderDto.class);   
	}

	@Override
	public ApiResponse deleteOrderByUserId(Long order_id) {
		
	    Order order = orderRepo.findById(order_id)
	            .orElseThrow(() -> new OrderNotFoundException("Order with ID " + order_id + " not found"));
	    orderRepo.delete(order);
	   return new ApiResponse("Order With Id "+order_id+"Deleted Successfully!!!!");
	    
	}

}
