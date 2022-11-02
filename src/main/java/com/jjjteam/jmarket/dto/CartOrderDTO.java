package com.jjjteam.jmarket.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

// 주문할 상품 데이터 전달용
@Getter
@Setter
public class CartOrderDTO {

    private Long cartItemId;

    private List<CartOrderDTO> cartOrderDTOList;

}