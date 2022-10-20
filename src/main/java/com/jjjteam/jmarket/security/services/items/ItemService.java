package com.jjjteam.jmarket.security.services.items;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jjjteam.jmarket.dto.ItemFormDTO;
import com.jjjteam.jmarket.entity.Item;
import com.jjjteam.jmarket.entity.ItemImg;
import com.jjjteam.jmarket.repository.ItemImgRepository;
import com.jjjteam.jmarket.repository.ItemRepository;

import java.util.List;

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
}
