package com.jjjteam.jmarket.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jjjteam.jmarket.dto.ItemFormDTO;

import com.jjjteam.jmarket.dto.ItemListDTO;
import com.jjjteam.jmarket.dto.ItemSearchDTO;
import com.jjjteam.jmarket.model.Item;


import com.jjjteam.jmarket.repository.ItemRepository;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class ItemService {
	private final ItemRepository itemRepository;
    public void saveItem(ItemFormDTO itemFormDTO) throws Exception {
        // 상품 등록
        Item item = itemFormDTO.setRepTime().createItem();
        itemRepository.save(item);

    }
	

    // 상품 수정하기를 위한 service
    // 상품이랑, 상품이미지의 entity -> dto 로 바꾸 기만 하는 service
	@Transactional(readOnly = true)
    public ItemFormDTO getItemDetail(Long itemId) {

        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        ItemFormDTO itemFormDTO = ItemFormDTO.of(item);

        return itemFormDTO; //itemFormDTO 에는 상품이랑 상품 이미지 다 있움
    }

	
	// 상품 데이터 수정
    // dto 로 변환된 상품이랑 상품이미지를 수정
	public Long updateItem(ItemFormDTO itemFormDto) throws Exception {

        //상품 수정
        Item item = itemRepository.findById(itemFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);
        return item.getId();
    }
	
	
	// 상품 데이터 조회
    // 상품 조회 조건 + 페이지 정보 를 파라미터로 받음
    @Transactional(readOnly = true) // 트랜젝션을 readOnly 로 설정할 경우, JPA 가 변경감지(더티체킹)를 수행하지 않아서 성능 향상됨 _데이터 수정이 일어나지 않기 때문에
    public Page<Item> getAdminItemPage(ItemSearchDTO itemSearchDto, Pageable pageable){
    	log.info("테으스팅비낟.");
    	itemRepository.getAdminItemPage(itemSearchDto, pageable).getContent().stream().forEach(x -> System.out.print(x.getRepimg()));
        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }

    // 메인 페이지에 보여줄 상품 데이테 조회
    @Transactional(readOnly = true)
    public Page<ItemListDTO> getMainItemPage(ItemSearchDTO itemSearchDto, Pageable pageable){
    	
        return itemRepository.getMainItemPage(itemSearchDto, pageable);
    }
    
    // 상품 삭제하기
    @Transactional
    public void deleteItem(Long id) {
    	itemRepository.deleteById(id);
    	System.out.print("@@@@@@@@@@@@@@@@@@@@@@@@@@ 리파지토리 성공");
    }
	
}