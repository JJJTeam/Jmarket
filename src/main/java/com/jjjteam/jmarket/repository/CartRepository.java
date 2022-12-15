package com.jjjteam.jmarket.repository;

import com.jjjteam.jmarket.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

	Cart findByUserId(Long userId);
	
}
