package com.jjjteam.jmarket.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jjjteam.jmarket.dto.ItemFormDTO;
import com.jjjteam.jmarket.dto.ItemImgDTO;
import com.jjjteam.jmarket.dto.ItemListDTO;
import com.jjjteam.jmarket.dto.ItemSearchDTO;
import com.jjjteam.jmarket.dto.MainItemDTO;
import com.jjjteam.jmarket.model.Item;
import com.jjjteam.jmarket.model.ItemImg;
import com.jjjteam.jmarket.repository.ItemImgRepository;
import com.jjjteam.jmarket.repository.ItemRepository;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemService {
	private final ItemRepository itemRepository;
	private final ItemImgService itemImgService;
	private final ItemImgRepository itemImgRepository;


	public Long saveItem(ItemFormDTO itemFormDTO, List<MultipartFile> itemImgFileList) throws Exception {
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ saveItem 서비스 시작@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		// 상품 등록
		Item item = itemFormDTO.toEntity(itemFormDTO);
		itemRepository.save(item);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ item :" + item);
		

		// 이미지 등록
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 이미지 등록 시작 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		
		for (int i = 0, max = itemImgFileList.size(); i < max; i++) {
			ItemImg itemImg = ItemImg.builder().item(item).repimgYn(i == 0 ? "Y" : "N").build();
			
			itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
			
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 이미지 등록 끝 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		return item.getId();
	}
	
	// 상품 수정하기를 위한 service
    // 상품이랑, 상품이미지의 entity -> dto 로 바꾸기만 하는 service
	@Transactional(readOnly = true)
    public ItemFormDTO getItemDetail(Long itemId) {

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDTO> itemImgDtoList = new ArrayList<>();

        for (ItemImg itemImg : itemImgList) {
            ItemImgDTO itemImgDTO = ItemImgDTO.of(itemImg);
            itemImgDtoList.add(itemImgDTO);
        }

        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        ItemFormDTO itemFormDTO = ItemFormDTO.of(item);
        itemFormDTO.setItemImgDtoList(itemImgDtoList);

        return itemFormDTO; //itemFormDTO 에는 상품이랑 상품 이미지 다 있움
    }
	

	
	// 상품 데이터 수정
    // dto 로 변환된 상품이랑 상품이미지를 수정
	public Long updateItem(ItemFormDTO itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {

        //상품 수정
        Item item = itemRepository.findById(itemFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);

        List<Long> itemImgIds = itemFormDto.getItemImgIds();
        
        //이미지 등록
        for (int i = 0, max = itemImgFileList.size(); i < max; i++) {
            itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
        }
        
        return item.getId();
    }
	
	
//	// 상품 데이터 조회
//    // 상품 조회 조건 + 페이지 정보 를 파라미터로 받음
//    @Transactional(readOnly = true) // 트랜젝션을 readOnly 로 설정할 경우, JPA 가 변경감지(더티체킹)를 수행하지 않아서 성능 향상됨 _데이터 수정이 일어나지 않기 때문에
//    public Page<Item> getAdminItemPage(ItemSearchDTO itemSearchDto, Pageable pageable){
//        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
//    }
//
//    // 메인 페이지에 보여줄 상품 데이테 조회
//    @Transactional(readOnly = true)
//    public Page<MainItemDTO> getMainItemPage(ItemSearchDTO itemSearchDto, Pageable pageable){
//        return itemRepository.getMainItemPage(itemSearchDto, pageable);
//    }
	
}
