package com.jjjteam.jmarket.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartOrderDTO {
	
	private Long cartItemId;
	private List<CartOrderDTO> cartOrderDTOList;

}
