package com.jjjteam.jmarket.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class CartOrderDTO {
	
	private Long cartItemId;//
	private List<CartOrderDTO> cartOrderDTOList;
	private Long addressNum;

}
