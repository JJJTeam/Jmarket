 package com.jjjteam.jmarket.dto;

import org.modelmapper.ModelMapper;

import com.jjjteam.jmarket.model.ItemImg;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemImgDTO {
	private Long id;

	private String imgName;

	private String oriImgName;

	private String imgUrl;

	private String repImgYn;

	private static ModelMapper modelMapper = new ModelMapper();
	
	
	public static ItemImgDTO of(ItemImg itemImg) {
		return modelMapper.map(itemImg, ItemImgDTO.class);
	}
	
//	@Builder
//    public ItemImgDTO(String imgName, String oriImgName, String imgUrl, String repImgYn) {
//        this.imgName = imgName;
//        this.oriImgName = oriImgName;
//        this.imgUrl = imgUrl;
//        this.repImgYn = repImgYn;
//    }
//
//	public ItemImg toEntity(ItemImgDTO dto) {
//		ItemImg entity = ItemImg.builder()
//				.imgName(dto.imgName)
//				.oriImgName(dto.oriImgName)
//				.imgUrl(dto.imgUrl)
//				.repimgYn(dto.repImgYn)
//				.build();
//
//		return entity;
//	}
	
//	public static ItemImgDTO of(ItemImg entity) {
//		ItemImgDTO dto = ItemImgDTO.builder()
//				.imgName(entity.getImgName())
//				.oriImgName(entity.getOriImgName())
//				.imgUrl(entity.getImgUrl())
//				.repImgYn(entity.getRepimgYn())
//				.build();
//
//		return dto;
//	}
	
}