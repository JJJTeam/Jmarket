package com.jjjteam.jmarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jjjteam.jmarket.dto.CartDetailDTO;
import com.jjjteam.jmarket.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	
	//카트아이디와 상품아이디를 이용해서 상품이 장바구니에 들어있는지 조회
	CartItem findByCartIdAndItemId(Long cartId, Long itemId);
	

	@Query("select new com.jjjteam.jmarket.dto.CartDetailDTO(ci.id, i.itemNm, i.price, ci.count, i.repimg) " +
			"from CartItem ci " +
			"join ci.item i " +
			
			"where ci.cart.id = :cartId " +
//			"and im.item.id = ci.item.id " +
//			"and im.repimgYn = 'Y' " +
			"order by ci.regTime desc"
	)
	List<CartDetailDTO> findCartDetailDTOList(Long cartId);


	
	
}
