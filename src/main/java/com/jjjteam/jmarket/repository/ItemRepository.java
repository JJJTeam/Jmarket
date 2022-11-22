package com.jjjteam.jmarket.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jjjteam.jmarket.dto.ItemListDTO;
import com.jjjteam.jmarket.dto.ItemSearchDTO;
import com.jjjteam.jmarket.dto.MainItemDTO;
import com.jjjteam.jmarket.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	// 상품id로 데이터 조회하기
    List<Item> findByItemId(Long id);
	
    // 상품명으로 데이터 조회하기
    List<Item> findByItemNm(String itemNm);

    // or 조건?
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // lessThan 조건?
    List<Item> findByPriceLessThan(Integer price);

    // orderBy 조건?
    List<Item> findAllByOrderByPriceDesc();

    // orderBy 조건 + 가격 조건?
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
    
	Page<Item> getLoadItemList(ItemListDTO itemListDTO, Pageable pageable);
	
	Page<Item> getLoadItem(ItemListDTO itemListDTO, Pageable pageable);
    
//	Page<Item> getAdminItemPage(ItemSearchDTO itemSearchDto, Pageable pageable);
//
//	Page<MainItemDTO> getMainItemPage(ItemSearchDTO itemSearchDto, Pageable pageable);
}
