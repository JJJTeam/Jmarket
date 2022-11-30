package com.jjjteam.jmarket.controller;

import com.jjjteam.jmarket.constant.ERole;
import com.jjjteam.jmarket.dto.ItemListDTO;
import com.jjjteam.jmarket.dto.ItemSearchDTO;
import com.jjjteam.jmarket.dto.UserDTO;
import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import com.jjjteam.jmarket.service.ItemService;
import com.jjjteam.jmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
	private ItemService itemService;
	private final UserService userService;

	@GetMapping("/login")
	public String ToLoginPage() {
		return "/login";
	}

	@GetMapping("/logihandler")
	public String ToLoginHandler(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		if (userDetails.getAuthorities().stream().map(x -> x.toString()).collect(Collectors.toList())
				.contains(ERole.ROLE_ADMIN.name())) {
			return "/admin/index";
		} else {
			return "/index";
		}

	}

	@GetMapping("/signup")
	public String ToJoinPage(UserDTO userDTO) {
		return "signup";
	}

	@GetMapping("/")
	public String toLoginPage() {
		return "index";
	}

	@GetMapping("/index")
	public String toLoginPage2() {
		return "index";
	}

	@PostMapping("/signup")
	public String registerUser(@Valid UserDTO userDTO, BindingResult bindingResult, Model model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "signup";
		}
		List<String> DoubleCheckTextList = userService.DoubleCheckTextList(userDTO,
				(boolean) session.getAttribute("phoneAuth"), (String) session.getAttribute("phoneNumberAuth"));
		if (DoubleCheckTextList.size() > 0) {
			model.addAttribute("messege", DoubleCheckTextList);
			return "signup";
		}
		session.removeAttribute("phoneAuth");
		session.removeAttribute("phoneNumberAuth");
		userService.registerUser(userDTO);
		return "/index";
	}

	@GetMapping("/api/checkId")
	@ResponseBody
	public boolean checkId(@RequestParam(value = "userId") String userId) {
		return userService.existsByUserId(userId);
	}

	@GetMapping("/api/checkEmail")
	@ResponseBody
	public boolean checkEmail(@RequestParam(value = "email") String email) {
		return userService.existsByEmail(email);
	}

	@PostMapping("/api/phoneAuth")
	@ResponseBody
	public Boolean phoneAuth(@RequestBody String userPhoneNumber, HttpSession session) {
		try { // 이미 가입된 전화번호가 있으면
			if (userService.memberTelCount(userPhoneNumber))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		String code = userService.sendRandomMessage(userPhoneNumber);
		session.setAttribute("rand", code);
		session.setAttribute("phoneAuth", false);
		session.setAttribute("phoneNumberTemp", userPhoneNumber.replaceAll("\"", ""));
		return false;
	}

	@PostMapping("/api/phoneAuthOk")
	@ResponseBody
	public Boolean phoneAuthOk(HttpSession session, HttpServletRequest request) {

		String rand = (String) session.getAttribute("rand");
		String code = (String) request.getParameter("code");

		System.out.println(rand + " : " + code);
		if (rand.equals(code)) {
			session.removeAttribute("rand");
			session.setAttribute("phoneAuth", true);
			session.setAttribute("phoneNumberAuth", session.getAttribute("phoneNumberTemp"));
			return false;
		}
		session.removeAttribute("phoneNumberTemp");
		return true;
	}

	// index 뷰로 가기
//	@GetMapping(value = "/index2")
//	public String main(ItemSearchDTO itemSearchDTO, Optional<Integer> page, Model model) {
//		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
//		// 0: 조회할 페이지 번호, 3: 한 번에 가지고 올 데이터 수
//		// url 에 페이지 번호가 있으면은 그 페이지를 보여주고, url 에 번호가 없으면 0 페이지 보여줌
//
//		Page<ItemListDTO> items = itemService.getMainItemPage(itemSearchDTO, pageable);
//		model.addAttribute("items", items); // item: 조회한 상품 데이터
//		model.addAttribute("itemSearchDTO", itemSearchDTO); // 페이지 전환 시 기존 검색 조건을 유지한 채 이동할 수 있게 뷰에 전달
//		model.addAttribute("maxPage", 5); // 최대 5개의 이동할 페이지 번호를 보여줌줌
//		return "index";
//	}

}
