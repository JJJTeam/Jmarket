package com.jjjteam.jmarket.service;


import com.jjjteam.jmarket.dto.OrderDTO;
import com.jjjteam.jmarket.dto.OrderHistDTO;
import com.jjjteam.jmarket.dto.OrderItemDTO;
import com.jjjteam.jmarket.model.Item;
import com.jjjteam.jmarket.model.Order;
import com.jjjteam.jmarket.model.OrderItem;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.repository.ItemRepository;
import com.jjjteam.jmarket.repository.OrderRepository;
import com.jjjteam.jmarket.repository.UserAddressRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


// 주문목록을 조회하는
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
	private final UserAddressRepository userAddressRepository;

	@Transactional(readOnly = true)
   public Page<OrderHistDTO> getOrderList(Long id, Pageable pageable){

	   List<Order> orders = orderRepository.findOrders(id, pageable);
	   Long totalCount = orderRepository.countOrder(id);
	   List<OrderHistDTO> orderHistDTOs = new ArrayList<>();
	   
	   for (Order order: orders) {

		 OrderHistDTO orderHistDTO = new OrderHistDTO(order);
		 List<OrderItem> orderItems = order.getOrderItems();
		
		 for (OrderItem orderItem : orderItems) {
			 OrderItemDTO orderItemDTO = new OrderItemDTO(orderItem, orderItem.getRepimg());
             orderHistDTO.addOrderItemDTO(orderItemDTO);
          }
		 orderHistDTOs.add(orderHistDTO);
	   }
	   return new PageImpl<OrderHistDTO>(orderHistDTOs, pageable, totalCount);
   }
   //주문취소하는 로직 
   @Transactional(readOnly=true)
   public boolean validateOrder(Long orderId, Long id) {
	  //현재로그인한 사용자와 주문자와 동일한지 검사 같을때는 true 반환
	   User curUser = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	   Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
	   User saveUser = order.getUser();
	   
	   if(curUser.getId()!= saveUser.getId()) {
		   return false;
	   }
	   return true;	
   }

  //주문 취소상태로 변경하면 변경감지에 의해서 트랜잭션이 끝날 때 update 쿼리가 실행
   public void cancelOrder(Long orderId){
       Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
       order.cancelOrder();

   }

   public Long orders(List<OrderDTO> orderDTOList, Long id, Long addressNum) {
	   User user = userRepository.findById(id).get();
	   List<OrderItem> orderItemList = new ArrayList<>();
	   
	   for(OrderDTO orderDTO : orderDTOList) {
		   Item item = itemRepository.findById(orderDTO.getItemId()).orElseThrow(EntityNotFoundException::new);
		   
		   OrderItem orderItem = OrderItem.createOrderItem(item, orderDTO.getCount() , item.getRepimg());
		   orderItemList.add(orderItem); 
	   }
	   
	   Order order = Order.createOrder(user, orderItemList, userAddressRepository.findById(addressNum).orElseThrow(EntityNotFoundException::new));
	   orderRepository.save(order);
	   
	   return order.getId();
   }
}
