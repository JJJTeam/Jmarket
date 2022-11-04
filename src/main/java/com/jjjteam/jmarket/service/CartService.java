package com.jjjteam.jmarket.service;


import com.jjjteam.jmarket.dto.CartDetailDTO;
import com.jjjteam.jmarket.dto.CartItemDTO;
import com.jjjteam.jmarket.dto.CartOrderDTO;
import com.jjjteam.jmarket.dto.OrderDTO;
import com.jjjteam.jmarket.entity.Cart;
import com.jjjteam.jmarket.entity.CartItem;
import com.jjjteam.jmarket.entity.Item;
import com.jjjteam.jmarket.entity.Member;
import com.jjjteam.jmarket.repository.CartItemRepository;
import com.jjjteam.jmarket.repository.CartRepository;
import com.jjjteam.jmarket.repository.ItemRepository;
import com.jjjteam.jmarket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    // 장바구니에 상품을 담는 로직
    public Long addCart(CartItemDTO cartItemDto, String email){

        Item item = itemRepository.findById(cartItemDto.getItemId()) //장바구니에 담을 상품 엔티티 조회
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email); // 현재 로그인한 회원 엔티티 조회

        Cart cart = cartRepository.findByMemberId(member.getId()); // 현재 로그인한 회원의 장바구니 엔티티 조회

        if(cart == null){ // 회원에게 장바구니가 없으면, 만들어줌
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        // 상품이 장바구니에 들어가있는지 아닌지 조회
        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        // 만약 상품이 이미 있으면은 개수를 +
        if(savedCartItem != null){
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else { // 아니면은 CartItem 에 상품 저장
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }

    // 이메일을 이용하여 카트 리스트를 조회합니다.
    @Transactional(readOnly = true)
    public List<CartDetailDTO> getCartList(String email){

        List<CartDetailDTO> cartDetailDtoList = new ArrayList<>();

        Member member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.getId());

        if(cart == null){ // 위에서 유저 카트 조회해서, 없으면은 그냥 반환하고
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());
        return cartDetailDtoList; // 카트 있으면은 cartItemRepository 의 JPQL 쿼리로 걸러진 아이템들을 담영서 반환
    }

    // 카트 아이템이 유효한지 확인
    // 회원 검증
    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String email){

        Member curMember = memberRepository.findByEmail(email); // 현재 로그인한 회원 조회

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new); // 장바구니의 상품을 조회해서

        Member savedMember = cartItem.getCart().getMember(); // 그 상품을 저장한 회원 조회

        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){ // 로그인한 회원 == 장바구니에 상품을 저장한 회원
            return false;
        }
        return true;
    }

    // 장바구 상품의 수량을 업데이트함
    public void updateCartItemCount(Long cartItemId, int count){

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);// 장바구니의 상품을 조회해서

        cartItem.updateCount(count); // 장바구니 상품 개수 ++
    }

    // 장바구니 아이템 제거
    public void deleteCartItem(Long cartItemId) {

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);// 장바구니의 상품을 조회해서

        cartItemRepository.delete(cartItem); // 장바구니 상품 제거
    }


    // 상품 주문 + 장바구니에서 주문한거 제거
    public Long orderCartItem(List<CartOrderDTO> cartOrderDtoList, String email){
        List<OrderDTO> orderDtoList = new ArrayList<>();

        // 주문한 상품을 orderDtoList 에 담아줌
        for (CartOrderDTO cartOrderDto : cartOrderDtoList) {
            CartItem cartItem = cartItemRepository
                    .findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderDTO orderDto = new OrderDTO();
            orderDto.setItemId(cartItem.getItem().getId());
            orderDto.setCount(cartItem.getCount());
            orderDtoList.add(orderDto);
            // 여러 개의 상품을 하나의 주문에 담아야 함
        }

        // 주문한 상품은 장바구니에서 제거함
        Long orderId = orderService.orders(orderDtoList, email);
        for (CartOrderDTO cartOrderDto : cartOrderDtoList) {
            CartItem cartItem = cartItemRepository
                    .findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            cartItemRepository.delete(cartItem);
        }
        return orderId;
    }
}
