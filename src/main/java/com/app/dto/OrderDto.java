package com.app.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto extends BaseDto {
  
	//Handled By The Hibernate 
	private LocalDate order_date;
	
	private Double total_amount;
	
	private LocalDate deliveryDate;
	
	private String delivery_status;
	
	private String delivery_address;
	
	//private List<OrderItemDto> orderItems = new ArrayList<>();
	
	//private Long user_id;
}
