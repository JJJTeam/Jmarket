package com.jjjteam.jmarket.service;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jjjteam.jmarket.dto.OrderDTO;
import com.jjjteam.jmarket.dto.OrderHistDTO;
import com.jjjteam.jmarket.dto.OrderItemDTO;
import com.jjjteam.jmarket.model.Item;

import com.jjjteam.jmarket.model.Order;
import com.jjjteam.jmarket.model.OrderItem;
import com.jjjteam.jmarket.model.User;

import com.jjjteam.jmarket.repository.ItemRepository;
import com.jjjteam.jmarket.repository.OrderRepository;
import com.jjjteam.jmarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;


// 주문목록을 조회하는
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;


    //주문할 상품 조회
   public Long order(OrderDTO orderDTO, Long id) {
	   Item item = itemRepository.findById(orderDTO.getItemId()).orElseThrow(EntityNotFoundException::new);
	   //현재로그인한 회원의 아이디를 이용해서 회원정보 ㅈ
	   User user = userRepository.findById(id).get();
	   
	   List<OrderItem> orderItemList = new ArrayList<>();
	   
	   OrderItem orderItem = OrderItem.createOrderItem(item, orderDTO.getCount(), item.getRepimg() );
	   orderItemList.add(orderItem);
	   
	   Order order = Order.createOrder(user, orderItemList);
	   orderRepository.save(order);
	   
	   return order.getId();
   }
   
   @Transactional(readOnly = true)
   public Page<OrderHistDTO> getOrderList(Long id, Pageable pageable){


	   List<Order> orders = orderRepository.findOrders(id, pageable);
	   log.warn("orders : {}", orders);
	   Long totalCount = orderRepository.countOrder(id);
	   log.warn("totalCount : {}", totalCount);
	   
	   List<OrderHistDTO> orderHistDTOs = new ArrayList<>();
	   
	   for (Order order: orders) {
		 OrderHistDTO orderHistDTO = new OrderHistDTO(order);
		 List<OrderItem> orderItems = order.getOrderItems();
		
		 for (OrderItem orderItem : orderItems) {
//             ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn
//                     (orderItem.getItem().getId(), "Y");
//             OrderItemDTO orderItemDTO =                     new OrderItemDTO(orderItem, itemImg.getImgUrl());
			 OrderItemDTO orderItemDTO = new OrderItemDTO(orderItem, orderItem.getRepimg());
             orderHistDTO.addOrderItemDTO(orderItemDTO);
          }
		 
		 orderHistDTOs.add(orderHistDTO);
		 
	   }
	   
	   return new PageImpl<OrderHistDTO>(orderHistDTOs, pageable, totalCount);
   }
   
   
   //주문취소하는 로직 
   @Transactional(readOnly=true)
   public boolean validateOrder(Long orderId, String email) {
	  //현재로그인한 사용자와 주문자와 동일한지 검사 같을때는 true 반환
	   User curUser = userRepository.findByEmail(email);
	   Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
	   User saveUser = order.getUser();
	   
	   if(!StringUtils.equals(curUser.getEmail(), saveUser.getEmail())) {
		   return false;
	   }
	   return true;	
   }
   
   
   
  //주문 취소상태로 변경하면 변경감지에 의해서 트랜잭션이 끝날 때 update 쿼리가 실행
   public void cancelOrder(Long orderId){
       Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
       order.cancelOrder();
   }
   
   
   
   
   public Long orders(List<OrderDTO> orderDTOList, Long id) {
	   User user = userRepository.findById(id).get();
	   List<OrderItem> orderItemList = new ArrayList<>();
	   
	   for(OrderDTO orderDTO : orderDTOList) {
		   Item item = itemRepository.findById(orderDTO.getItemId()).orElseThrow(EntityNotFoundException::new);
		   
		   OrderItem orderItem = OrderItem.createOrderItem(item, orderDTO.getCount() , item.getRepimg());
		   orderItemList.add(orderItem); 
	   }
	   
	   Order order = Order.createOrder(user, orderItemList);
	   orderRepository.save(order);
	   
	   return order.getId();
   }
   
   
   
   
   
   

}
