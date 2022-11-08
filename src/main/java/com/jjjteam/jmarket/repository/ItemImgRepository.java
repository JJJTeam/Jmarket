package com.jjjteam.jmarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjjteam.jmarket.model.ItemImg;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long>{
	
	// 이미지가 잘 저장됐는지 테스트 코드를 작성하기 위해서 ItemImgRepository 인터페이스에 findByItemIdOrderByIdAsc 메소드를 추가
	List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
	
	ItemImg findByItemIdAndRepimgYn(Long itemId, String repimgYn); // 상품 대표 이미지를 찾는 쿼리 메소드 추가
    // 하는 이유는, 구매 이력 페이지에서 주문 상품 대표 이미지 출력할려고
}
