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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jjjteam.jmarket.dto.ItemFormDTO;
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
	public String itemNew(@Valid ItemFormDTO itemFormDTO, BindingResult bindingResult, Model model) {

		System.out.println(
				"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 컨트롤러 시작점 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		if (bindingResult.hasErrors()) {
			System.out.println("@@@@@@@@@@@@@@@@@ 1번째 오류 @@@@@@@@@@@@@@@@@@");
			return "item/itemForm";
		}

		try {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@ 오류없이 서비스 시작 @@@@@@@@@@@@@@@@@@");
			itemService.saveItem(itemFormDTO);
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

	// 상품 수정 get 페이지
	@GetMapping(value = "/admin/item/{itemId}")
	public String itemDtl(@PathVariable("itemId") Long itemId, Model model) {

		try {
			ItemFormDTO itemFormDto = itemService.getItemDetail(itemId);
			model.addAttribute("itemFormDTO", itemFormDto); // 조회한 상품 데이터를 model 에 담아서 뷰로 전달함
		}

		catch (EntityNotFoundException e) { // 상품 엔티티가 존재하지 않으면은 에러메세지 + 상품 등록페이지로 다시 ㄱㄱ
			model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
			model.addAttribute("itemFormDto", new ItemFormDTO());
			return "item/itemForm";
		}

		return "item/itemForm";
	}

	// 상품 수정 post
	@PostMapping(value = "/admin/item/{itemId}")
	public String itemUpdate(@Valid ItemFormDTO itemFormDTO, BindingResult bindingResult, Model model) {

		// 상품 수정 시 필수 값이 없을 때 애러 발생
		if (bindingResult.hasErrors()) {
			return "item/itemForm"; // 에러가 발생하면 상품 수정 get 페이지로 이동
		}

		try {// 상품 수정 로직 호출
			itemService.updateItem(itemFormDTO); // itemFormDto: 상품 정보, itemImgFileList: 상품 이미지 정보들 리스트
		} catch (Exception e) {
			model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
			return "item/itemForm";
		}

		return "redirect:/"; // 메인 페이지로 리다이렉트
	}

	// 상품 삭제
	@GetMapping(value = "/admin/item/delete/{itemId}")
	public String delete(@PathVariable("itemId") Long id) {
		itemService.deleteItem(id);
		return "redirect:/item/itemList";
	}

}
