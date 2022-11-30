package com.jjjteam.jmarket.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jjjteam.jmarket.dto.CartItemDTO;
import com.jjjteam.jmarket.model.Cart;
import com.jjjteam.jmarket.model.CartItem;
import com.jjjteam.jmarket.model.Item;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.repository.CartItemRepository;
import com.jjjteam.jmarket.repository.CartRepository;
import com.jjjteam.jmarket.repository.ItemRepository;
import com.jjjteam.jmarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
	
	private final ItemRepository itemRepository;
	private final UserRepository userRepository;
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	//private final OrderService orderService;
	
	public Long addCart(CartItemDTO cartItemDTO, String email ) { //징바구니에 담을 상품 엔티티를 조회
		Item item =itemRepository.findById(cartItemDTO.getItemId()).orElseThrow(EntityNotFoundException::new);
		User user = userRepository.findByEmail(email); //현재 로그인한 회원 엔티티 조회
		
		Cart cart = cartRepository.findByUserId(user.getId());//현재 로그인한 회원의 장바구니를 조회 
		
		if(cart==null) {//상품을처음으로 장바구니에 담을 경우 해당 회원의 장바구니 엔티티를 생성
		 cart = Cart.createCart(user);
		 cartRepository.save(cart);
		}
		
		
		CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());
		
		if(savedCartItem != null) {
			savedCartItem.addCount(cartItemDTO.getCount());//장바구니에 있던 상품일 경우 기존 수량에 현재 장바구니에 담을 수량 만큼 더해준다.
			return savedCartItem.getId();
		}else {
			//장바구니 엔티티, 상품 엔티티, 장바구니에 담을 상품 수량을 이용하여 CartItem엔티티 생성 
			CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDTO.getCount());
			
			cartItemRepository.save(cartItem);//장바구니에 들어갈 상품을 저장
			return cartItem.getId();
		}
	}
	

}
