package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.CartItem;
import java.util.List;
import com.app.entities.Cart;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
	List<CartItem> findAllByCart(Cart cart);
}
