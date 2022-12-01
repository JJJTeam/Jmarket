package com.jjjteam.jmarket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jjjteam.jmarket.dto.ItemFormDTO;
import com.jjjteam.jmarket.dto.ItemListDTO;
import com.jjjteam.jmarket.dto.ItemSearchDTO;
import com.jjjteam.jmarket.model.Item;
import com.jjjteam.jmarket.service.ItemService;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ItemController {

	@Autowired
	ItemService itemService;

	// 상품등록 get
	@GetMapping("/item/itemForm")
	@Secured("ROLE_ADMIN")
	public String itemForm(Model model) {
		model.addAttribute("itemFormDTO", new ItemFormDTO());
		return "item/itemForm";
	}

	// 상품등록 post
	@PostMapping(value = "/item/itemForm")
	public String itemNew(@Valid ItemFormDTO itemFormDTO, BindingResult bindingResult, Model model,
			@RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {
		System.out.println(
				"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 컨트롤러 시작점 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
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
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@" + e);
			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
			return "item/itemForm";
		}
		return "redirect:/";

	}

	// 상품 관리 화면 get
	@GetMapping(value = { "/item/itemList", "/item/itemList/{page}" })
	public String ToItemList(ItemSearchDTO itemSearchDTO, @PathVariable("page") Optional<Integer> page, Model model) {
		// 한 페이지 당 3개만 보여줄거임
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
		// 0: 조회할 페이지 번호, 3: 한 번에 가지고 올 데이터 수
		// url 에 페이지 번호가 있으면은 그 페이지를 보여주고, url 에 번호가 없으면 0 페이지 보여줌

		Page<Item> items = itemService.getAdminItemPage(itemSearchDTO, pageable); // itemSearchDto: 조회 조건 pageable: 페이징
																					// 정보
		model.addAttribute("items", items); // item: 조회한 상품 데이터
		model.addAttribute("itemSearchDTO", itemSearchDTO); // 페이지 전환 시 기존 검색 조건을 유지한 채 이동할 수 있게 뷰에 전달
		model.addAttribute("maxPage", 5); // 최대 5개의 이동할 페이지 번호를 보여줌줌
		return "item/itemList";
	}

	// 상품 상세보기 get
	@GetMapping(value = "/item/{itemId}")
	public String itemDetail(@PathVariable("itemId") Long itemId, Model model) {

		try {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@컨트롤러성공");
			ItemFormDTO itemFormDTO = itemService.getItemDetail(itemId);
			model.addAttribute("item", itemFormDTO);
		} catch (EntityNotFoundException e) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@컨트롤러실패");
			model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
			model.addAttribute("itemFormDto", new ItemFormDTO());
			return "item/itemList";
		}

		return "item/itemDetail";
	}

	// 상품 수정?
//		@PostMapping(value = "/item/itemList/{itemId}")
//	    public String itemUpdate(@Valid ItemFormDTO itemFormDTO, BindingResult bindingResult
//	            , @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model) {
	//
//	        if (bindingResult.hasErrors()) {
//	            return "item/itemForm";
//	        }
	//
//	        if (itemImgFileList.get(0).isEmpty() && itemFormDTO.getId() == null) {
//	            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
//	            return "item/itemForm";
//	        }
	//
//	        try {
//	            itemService.updateItem(itemFormDTO, itemImgFileList); // itemFormDto: 상품 정보, itemImgFileList: 상품 이미지 정보들 리스트
//	        } catch (Exception e) {
//	            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
//	            return "item/itemForm";
//	        }
	//
//	        return "redirect:/";
//	    }

}
