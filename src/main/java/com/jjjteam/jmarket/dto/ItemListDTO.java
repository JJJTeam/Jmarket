package com.jjjteam.jmarket.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ItemListDTO {
	
	private Long id;
	
	private String imgUrl;
	
	private String itemNm;
	
	private Integer price;
	
	public ItemListDTO() {
	}
	
	public ItemListDTO(Long id, String imgUrl, String itemNm, Integer price){
		this.id= id;
		this.imgUrl = imgUrl;
		this.itemNm = itemNm;
		this.price = price;
	}

}
