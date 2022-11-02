package com.jjjteam.jmarket.dto;

import com.jjjteam.jmarket.constant.OrderStatus;
import com.jjjteam.jmarket.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// 주문 정보를 담을 dto
@Getter
@Setter
public class OrderHistDTO {

    public OrderHistDTO(Order order){
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }

    private Long orderId; //주문아이디
    private String orderDate; //주문날짜
    private OrderStatus orderStatus; //주문 상태

    private List<OrderHistDTO> orderItemDtoList = new ArrayList<>();

    //주문 상품리스트
    public void addOrderItemDto(OrderHistDTO orderItemDto){ // '현재 주문한 상품 정보' 를 주문 상품 리스트에 담음음
       orderItemDtoList.add(orderItemDto);
    }
}
