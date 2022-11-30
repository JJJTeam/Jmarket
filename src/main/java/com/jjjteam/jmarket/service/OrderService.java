package com.jjjteam.jmarket.service;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
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
import com.jjjteam.jmarket.model.ItemImg;
import com.jjjteam.jmarket.model.Order;
import com.jjjteam.jmarket.model.OrderItem;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.repository.ItemImgRepository;
import com.jjjteam.jmarket.repository.ItemRepository;
import com.jjjteam.jmarket.repository.OrderRepository;
import com.jjjteam.jmarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;


// 주문목록을 조회하는
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;

     
   public Long order(OrderDTO orderDTO, String email) {
	   Item item = itemRepository.findById(orderDTO.getItemId()).orElseThrow(EntityNotFoundException::new);
	   User user = userRepository.findByEmail(email);
	   
	   List<OrderItem> orderItemList = new ArrayList<>();
	   OrderItem orderItem = OrderItem.createOrderItem(item, orderDTO.getCount());
	   orderItemList.add(orderItem);
	   Order order = Order.createOrder(user, orderItemList);
	   orderRepository.save(order);
	   
	   return order.getId();
   }
   
   @Transactional(readOnly = true)
   public Page<OrderHistDTO> getOrderList(String email, Pageable pageable){
	   
	   List<Order> orders = orderRepository.findOrders(email, pageable);
	   Long totalCount = orderRepository.countOrder(email);
	   
	   List<OrderHistDTO> orderHistDTOs = new ArrayList<>();
	   
	   for (Order order: orders) {
		 OrderHistDTO orderHistDTO = new OrderHistDTO(order);
		 List<OrderItem> orderItems = order.getOrderItems();
		
		 for (OrderItem orderItem : orderItems) {
             ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn
                     (orderItem.getItem().getId(), "Y");
             OrderItemDTO orderItemDTO =
                     new OrderItemDTO(orderItem, itemImg.getImgUrl());
             orderHistDTO.addOrderItemDTO(orderItemDTO);
          }
		 
		 orderHistDTOs.add(orderHistDTO);
		 
	   }
	   
	   return new PageImpl<OrderHistDTO>(orderHistDTOs, pageable, totalCount);
   }
   
   
   
   @Transactional(readOnly=true)
   public boolean validateOrder(Long orderId, String email) {
	   User curUser = userRepository.findByEmail(email);
	   Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
	   User saveUser = order.getUser();
	   
	   if(!StringUtils.equals(curUser.getEmail(), saveUser.getEmail())) {
		   return false;
	   }
	   return true;	
   }
   
   
   
  
   public void cancelOrder(Long orderId){
       Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
       order.cancelOrder();
   }
   
   
   
   
   public Long orders(List<OrderDTO> orderDTOList, String email) {
	   User user = userRepository.findByEmail(email);
	   List<OrderItem> orderItemList = new ArrayList<>();
	   
	   for(OrderDTO orderDTO : orderDTOList) {
		   Item item = itemRepository.findById(orderDTO.getItemId()).orElseThrow(EntityNotFoundException::new);
		   
		   OrderItem orderItem = OrderItem.createOrderItem(item, orderDTO.getCount());
		   orderItemList.add(orderItem); 
	   }
	   
	   Order order = Order.createOrder(user, orderItemList);
	   orderRepository.save(order);
	   
	   return order.getId();
   }
   
   
   
   
   
   

}
