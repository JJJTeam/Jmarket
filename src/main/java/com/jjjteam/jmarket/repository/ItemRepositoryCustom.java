package com.jjjteam.jmarket.repository;

import com.jjjteam.jmarket.dto.ItemListDTO;
import com.jjjteam.jmarket.dto.ItemSearchDTO;
import com.jjjteam.jmarket.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
 * Querydsl 을 SpringDataJpa 와 함께 사용하기 위해서는 사용자 정의 repository 를 작성해야 함
 * 사용자 정의 repository 는 총 3단계임
 * 1. 사용자 정의 인터페이스 작성
 * 2. 사용자 정의 인터페이스 구현 ***
 * 3. Spring Data Jpa repository 에서 사용자 정의 인터페이스 상속 (ItemRepository)
 */


//1. 사용자 정의 인터페이스 작성
public interface ItemRepositoryCustom{
	Page<Item> getAdminItemPage(ItemSearchDTO itemSearchDTO, Pageable pageable);

	Page<ItemListDTO> getMainItemPage(ItemSearchDTO itemSearchDTO, Pageable pageable);
}
