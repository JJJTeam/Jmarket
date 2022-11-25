package com.jjjteam.jmarket.service;


import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jjjteam.jmarket.dto.OrderDTO;
import com.jjjteam.jmarket.dto.OrderHistDTO;
import com.jjjteam.jmarket.dto.OrderItemDTO;
import com.jjjteam.jmarket.model.Item;
import com.jjjteam.jmarket.model.ItemImg;
import com.jjjteam.jmarket.model.Order;
import com.jjjteam.jmarket.model.OrderItem;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.repository.ItemImgRepository;
import com.jjjteam.jmarket.repository.ItemRepository;
import com.jjjteam.jmarket.repository.OrderRepository;
import com.jjjteam.jmarket.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

// 상품 주문 service
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;

    
    //주문목록을 조회하는 로직 구현
    
    @Transactional(readOnly = true)
    public Page<OrderHistDTO> getOrderList(String email, Pageable pageable){
    	
    	List<Order> orders = orderRepository.findOrders(email, pageable);
    	//유저의 주문 총 개수를  구하는 변수
    	Long totalCount = orderRepository.countOrder(email);
    	
    	List<OrderHistDTO> orderHistDTOs = new ArrayList<>();
    	
    	//주문리스트를 순화하면서 구매 이력 페이지에 전달할 DTO를 생성합니다.
    	for(Order order: orders) {
    		OrderHistDTO orderHistDTO = new OrderHistDTO(order);
    		List<OrderItem> orderItems = order.getOrderItems();
    		for(OrderItem orderItem : orderItems) {
    			//주문한 상품의 대표이미지를 조회
    			ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn(orderItem.getItem().getId(), "Y");
    			OrderItemDTO orderItemDTO = new OrderItemDTO(orderItem, itemImg.getImgUrl());
    			orderHistDTO.addOrderItemDTO(orderItemDTO);
    		}
    		orderHistDTOs.add(orderHistDTO);
    	}
    	
    	//페이지 구현 객체를 생성하여 반환
    	return new PageImpl<OrderHistDTO>(orderHistDTOs, pageable, totalCount);
    	
    }

    //주문 할 상품 조회
    public Long order(OrderDTO orderDTO, String email) {
    	Item item = itemRepository.findById(orderDTO.getItemId()).orElseThrow(EntityNotFoundException::new);
    	//현재로그인한 회원의 이메일을 조회
    	User user = userRepository.findByEmail(email);
    	
    	//주문할 상품엔티티와 주문 수량을 이용하여 주문 상품 엔티티를 생성
    	List<OrderItem> orderItemList = new ArrayList<>();
    	OrderItem orderItem = OrderItem.createOrderItem(item, orderDTO.getCount());
    	orderItemList.add(orderItem);
    	
    	//회원정보와 주문할 상품 리스트 정보를 이용하여 주문 엔티티를 생성
    	Order order = Order.createOrder(user, orderItemList);
    	
    	//생성한 주문 엔티티를 저장
    	orderRepository.save(order);
    	
    	return order.getId();
    }

}
