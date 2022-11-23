package com.jjjteam.jmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jjjteam.jmarket.dto.ItemFormDTO;
import com.jjjteam.jmarket.service.ItemService;



import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ItemController {

	@Autowired
	ItemService itemService;
	
	
	// 기존
	@GetMapping("/item/itemForm")
	public String itemForm(Model model) {
		model.addAttribute("itemFormDTO", new ItemFormDTO());
		return "item/itemForm";
	}

	@PostMapping(value="/item/itemForm")
	public String itemNew(@Valid ItemFormDTO itemFormDTO, BindingResult bindingResult, Model model,
			@RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 컨트롤러 시작점 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		if (bindingResult.hasErrors()) {
			System.out.println("@@@@@@@@@@@@@@@@@ 1번째 오류 @@@@@@@@@@@@@@@@@@");
			return "item/itemForm";
		}

		if (itemImgFileList.get(0).isEmpty() && itemFormDTO.getId() == null) {
			System.out.println("@@@@@@@@@@@@@@@@@ 2번째 오류 @@@@@@@@@@@@@@@@@@");
			model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
			return "item/itemForm";
		}

		try {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@ 오류없이 서비스 시작 @@@@@@@@@@@@@@@@@@");
			itemService.saveItem(itemFormDTO, itemImgFileList);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@성공했습니다.");
			
		} catch (Exception e) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@"+e);
			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
			return "item/itemForm";
		}
		return "redirect:/";

	}
	
	
	// 상품목록
//	@GetMapping("/item/itemList")
//	public String itemList(Model model) {
//		model.addAttribute("itemListDTO", ne w ItemListDTO());
//		return "item/itemList";
//	}

	@GetMapping("/item/itemList")
	public String itemList() {
		System.out.println(itemService.itemList());
		return "item/itemList";
	} 
	
	
	
	//상품 상세보기
	@GetMapping(value = "/item/{itemId}")
    public String itemDetail(@PathVariable("itemId") Long itemId, Model model) {

        try {
            ItemFormDTO itemFormDto = itemService.getItemDetail(itemId);
            model.addAttribute("itemFormDto", itemFormDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("itemFormDto", new ItemFormDTO());
            return "item/itemList";
        }

        return "item/itemDetail";
    }
	
	//상품 수정?
	@PostMapping(value = "/item/{itemId}")
    public String itemUpdate(@Valid ItemFormDTO itemFormDTO, BindingResult bindingResult
            , @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model) {

        if (bindingResult.hasErrors()) {
            return "item/itemForm";
        }

        if (itemImgFileList.get(0).isEmpty() && itemFormDTO.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
            return "item/itemForm";
        }

        try {
            itemService.saveItem(itemFormDTO, itemImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "item/itemForm";
        }

        return "redirect:/";
    }
	
	
//	@GetMapping(value={"/item/itemList" , "/item/itemList/{page}"})
//	public String ToItemList(ItemListDTO itemListDTO, @PathVariable("page") Optional<Integer> page, Model model) {
//		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
//        Page<Item> items = itemService.loadItemList(itemListDTO, pageable);
//        model.addAttribute("items", items);
//        model.addAttribute("itemListDTO", itemListDTO);
//        model.addAttribute("maxPage", 5);
//        return "item/itemList";
//	}

}
