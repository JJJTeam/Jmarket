package com.jjjteam.jmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jjjteam.jmarket.dto.ItemFormDTO;
import com.jjjteam.jmarket.security.services.items.ItemService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ItemController {

	@Autowired
	ItemService itemService;
	
//	// 사이트?
//	@GetMapping("/item/itemForm")
// 	public String item() {
// 		return "/item/itemForm";
// 	}
	
	// 기존
//	@GetMapping("/item/new")
//	public String itemForm(Model model) {
//		model.addAttribute("itemFormDTO", new ItemFormDTO());
//		return "/item/itemForm";
//	}

	@PostMapping("/item/new")
	public String itemNew(@Valid ItemFormDTO itemFormDTO, BindingResult bindingResult, Model model,
			@RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {

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
			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
			return "item/itemForm";
		}

		return "redirect:/";
	}

}
