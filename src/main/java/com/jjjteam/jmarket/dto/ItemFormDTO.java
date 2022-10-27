package com.jjjteam.jmarket.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.jjjteam.jmarket.constant.ItemSellStatus;
import com.jjjteam.jmarket.entity.Item;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ItemFormDTO {

	private Long id;

	@NotBlank(message = "상품명은 필수 입력 값입니다.")
	private String itemNm;

	@NotNull(message = "가격은 필수 입력 값입니다.")
	private Integer price;

	@NotBlank(message = "상세 내용은 필수 입력 값입니다.")
	private String itemDetail;

	@NotNull(message = "재고는 필수 입력 값입니다.")
	private Integer stockNumber;

	private ItemSellStatus itemSellStatus;

	private List<ItemImgDTO> itemImgDtoList = new ArrayList<>();

	private List<Long> itemImgIds = new ArrayList<>();

	@Builder
	public ItemFormDTO(String itemNm, Integer price, String itemDetail, Integer stockNumber,
			ItemSellStatus itemSellStatus) {
		this.itemNm = itemNm;
		this.price = price;
		this.itemDetail = itemDetail;
		this.stockNumber = stockNumber;
		this.itemSellStatus = itemSellStatus;
	}

	public Item toEntity(ItemFormDTO dto) {
		Item entity = Item.builder()
				.itemNm(dto.itemNm)
				.itemDetail(dto.itemDetail)
				.itemSellStatus(dto.itemSellStatus)
				.price(dto.price)
				.stockNumber(dto.stockNumber)
				.build();

		return entity;
	}

	public static ItemFormDTO of(Item entity) {
		ItemFormDTO dto = ItemFormDTO.builder()
				.itemNm(entity.getItemNm())
				.itemDetail(entity.getItemDetail())
				.itemSellStatus(entity.getItemSellStatus())
				.price(entity.getPrice())
				.stockNumber(entity.getStockNumber())
				.build();

		return dto;
	}
}
