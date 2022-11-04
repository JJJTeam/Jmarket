package com.jjjteam.jmarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jjjteam.jmarket.dto.ItemFormDTO;
import com.jjjteam.jmarket.dto.ItemImgDTO;
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
		// 상품 등록
		Item item = itemFormDTO.toEntity(itemFormDTO);
		itemRepository.save(item);

		// 이미지 등록
		for (int i = 0, max = itemImgFileList.size(); i < max; i++) {
			ItemImg itemImg = ItemImg.builder().item(item).repimgYn(i == 0 ? "Y" : "N").build();

			itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
		}

		return item.getId();
	}
	
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

        return itemFormDTO;
    }
	
	
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
	
}
