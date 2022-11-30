package com.jjjteam.jmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjjteam.jmarket.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	
	//카트아이디와 상품아이디를 이용해서 상품이 장바구니에 들어있는지 조회
	
	CartItem findByCartIdAndItemId(Long cartId, Long itemId);
	
}
