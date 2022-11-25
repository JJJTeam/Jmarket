package com.jjjteam.jmarket.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.jjjteam.jmarket.dto.OrderDTO;
import com.jjjteam.jmarket.model.Item;
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
