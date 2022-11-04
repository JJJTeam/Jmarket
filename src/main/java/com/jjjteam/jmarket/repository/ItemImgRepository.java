package com.jjjteam.jmarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjjteam.jmarket.model.ItemImg;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long>{
	
	// 이미지가 잘 저장됐는지 테스트 코드를 작성하기 위해서 ItemImgRepository 인터페이스에 findByItemIdOrderByIdAsc 메소드를 추가
	List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
}
