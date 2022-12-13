package com.jjjteam.jmarket.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.jjjteam.jmarket.model.Item;

/*
 * Querydsl 을 SpringDataJpa 와 함께 사용하기 위해서는 사용자 정의 repository 를 작성해야 함
 * 사용자 정의 repository 는 총 3단계임
 * 1. 사용자 정의 인터페이스 작성
 * 2. 사용자 정의 인터페이스 구현 ***
 * 3. Spring Data Jpa repository 에서 사용자 정의 인터페이스 상속 (ItemRepository)
 */


//3. ItemRepository 에서 ItemRepositoryCustom 상속
public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {
	
//	// 상품id로 데이터 조회하기
//    List<Item> findByItemId(Long id);

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
   
    
    
    @Query("select i from Item i where i.itemDetail like " +
            "%:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    @Query(value="select * from item i where i.item_detail like " +
            "%:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
}
