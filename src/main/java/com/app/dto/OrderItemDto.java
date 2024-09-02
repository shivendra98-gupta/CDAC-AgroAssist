package com.app.dto;

import com.app.entities.Order;
import com.app.entities.Product;

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
public class OrderItemDto extends BaseDto {

	private Integer quantity;
	
	private Long product_id;
	
	private Long order_id;
}
