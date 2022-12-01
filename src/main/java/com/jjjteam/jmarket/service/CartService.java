package com.jjjteam.jmarket.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import org.springframework.stereotype.Service;

import com.jjjteam.jmarket.dto.CartDetailDTO;
import com.jjjteam.jmarket.dto.CartItemDTO;
import com.jjjteam.jmarket.dto.CartOrderDTO;
import com.jjjteam.jmarket.dto.OrderDTO;
import com.jjjteam.jmarket.model.Cart;
import com.jjjteam.jmarket.model.CartItem;
import com.jjjteam.jmarket.model.Item;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.repository.CartItemRepository;
import com.jjjteam.jmarket.repository.CartRepository;
import com.jjjteam.jmarket.repository.ItemRepository;
import com.jjjteam.jmarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CartService {
	
	private final ItemRepository itemRepository;
	private final UserRepository userRepository;
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final OrderService orderService;
	
	
	//카트추가하기
	public Long addCart(CartItemDTO cartItemDTO, Long id ) { //징바구니에 담을 상품 엔티티를 조회
		System.out.println("빨리 밥 먹고 싶다 @@@@@@@ㅋㅋㅋㅋㅋ");
		Item item =itemRepository.findById(cartItemDTO.getItemId()).get();
		System.out.println("item 111111:::  "  + item);
		User user = userRepository.findById(id).orElseThrow(); //현재 로그인한 회원 엔티티 조회
		
		System.out.println("user 22222:::  "  + user);
		Cart cart = cartRepository.findByUserId(user.getId());//현재 로그인한 회원의 장바구니를 조회 
//		
		System.out.println("cart 33333:::  "  + cart);
		log.info(" cart1 : {}", cart);
		if(cart == null) {//상품을처음으로 장바구니에 담을 경우 해당 회원의 장바구니 엔티티를 생성
		 cart = Cart.createCart(user);
		 cartRepository.save(cart);
		}
		log.info(" cart2 : {}", cart);
		
//		//현재상품이 장바구니에 들어갔는지 확인
//		CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

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
	
	
	   @Transactional(readOnly = true)
	    public List<CartDetailDTO> getCartList(Long id){

	        List<CartDetailDTO> cartDetailDTOList = new ArrayList<>();

	        User user = userRepository.findById(id).get();
	        Cart cart = cartRepository.findByUserId(user.getId());
	    
	        if(cart == null){
	            return cartDetailDTOList;
	        }
	        
	        cartDetailDTOList = cartItemRepository.findCartDetailDTOList(cart.getId());
	        
	        return cartDetailDTOList;
	        
	    }
	   
	   

	   
	   /*
	     * 자바스크립트 코드에서 업데이트할 장바구니 상품번호는 조작이 가능하므로
	     * 현재로그인한 회원과 해당 장바구니 상품을 저장한 회원이 같은지 검사하는 로직
	     * */
	    @Transactional(readOnly = true)
	    public boolean validateCartItem(Long cartItemId, String email){
	    	//현재로그인한회원조회
	        User curUser = userRepository.findByEmail(email);
	       //장바구니 상품을 저장한 회원을 조회
	        CartItem cartItem = cartItemRepository.findById(cartItemId)
	                .orElseThrow(EntityNotFoundException::new);
	        User savedUser = cartItem.getCart().getUser();

	        if(!StringUtils.equals(curUser.getEmail(), savedUser.getEmail())){
	            return false;
	        }

	        return true;
	    }
	    
	    public void updateCartItemCount(Long cartItemId, int count){
	        CartItem cartItem = cartItemRepository.findById(cartItemId)
	                .orElseThrow(EntityNotFoundException::new);

	        cartItem.updateCount(count);
	    }

	    public void deleteCartItem(Long cartItemId) {
	        CartItem cartItem = cartItemRepository.findById(cartItemId)
	                .orElseThrow(EntityNotFoundException::new);
	        cartItemRepository.delete(cartItem);
	    }

	    public Long orderCartItem(List<CartOrderDTO> cartOrderDTOList, String email){
	        List<OrderDTO> orderDTOList = new ArrayList<>();

	        for (CartOrderDTO cartOrderDTO : cartOrderDTOList) {
	            CartItem cartItem = cartItemRepository
	                            .findById(cartOrderDTO.getCartItemId())
	                            .orElseThrow(EntityNotFoundException::new);

	            OrderDTO orderDTO = new OrderDTO();
	            orderDTO.setItemId(cartItem.getItem().getId());
	            orderDTO.setCount(cartItem.getCount());
	            orderDTOList.add(orderDTO);
	        }

	        Long orderId = orderService.orders(orderDTOList, email);
	        for (CartOrderDTO cartOrderDTO : cartOrderDTOList) {
	            CartItem cartItem = cartItemRepository
	                            .findById(cartOrderDTO.getCartItemId())
	                            .orElseThrow(EntityNotFoundException::new);
	            cartItemRepository.delete(cartItem);
	        }

	        return orderId;
	    }


		
	 
	

}
