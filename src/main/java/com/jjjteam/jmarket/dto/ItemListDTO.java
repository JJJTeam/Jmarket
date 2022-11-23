package com.jjjteam.jmarket.dto;

import java.util.List;

import com.jjjteam.jmarket.constant.ClothingItems;
import com.jjjteam.jmarket.constant.ItemColor;
import com.jjjteam.jmarket.constant.ItemSize;
import com.jjjteam.jmarket.model.Item;
import com.jjjteam.jmarket.model.ItemImg;

import lombok.Builder;
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
	
	@Builder
	public ItemListDTO(
			Long id, 
			String imgUrl, 
			String itemNm, 
			Integer price){
		this.id= id;
		this.imgUrl = imgUrl;
		this.itemNm = itemNm;
		this.price = price;
	}
	
//	public ItemImg toEntity(ItemListDTO dto) {
//		ItemImg entity = ItemImg.builder()
//				.imgUrl(dto.imgUrl)
//				.build();
//		
//		return entity;
//	}
//	
//	public Item toEntity1(ItemListDTO dto) {
//		Item entity = Item.builder()
//				.itemNm(dto.itemNm)
//				.price(dto.price)
//				.build();
//		
//		return entity;
//	}
//	
//	
//	
//	public static ItemListDTO of(ItemImg entity) {
//		ItemListDTO dto = ItemListDTO.builder()
//				.imgUrl(entity.getImgUrl())
//				.build();
//		
//		return dto;
//	}
//	
//	public static ItemListDTO of1(Item entity) {
//		ItemListDTO dto = ItemListDTO.builder()
//				.itemNm(entity.getItemNm())
//				.price(entity.getPrice())
//				.build();
//		
//		return dto;
//	}


}
