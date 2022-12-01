package com.jjjteam.jmarket.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjjteam.jmarket.dto.CartDetailDTO;
import com.jjjteam.jmarket.dto.CartItemDTO;
import com.jjjteam.jmarket.service.CartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;
	
	@PostMapping(value="/cart")
	public @ResponseBody ResponseEntity order(@RequestBody @Valid CartItemDTO cartItemDTO, BindingResult bindingResult, Principal principal) {
		
		//장바구니에 담을 상품정보를 받는 cartItemDTO객체에 데이터 바인딩시 에러가 있는지 검사 
		if(bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			
			for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
			
			return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
		}
		
		String email = principal.getName(); //현재로그인한 회원의 이메일정보를 변수에 저장
		Long cartItemId;
		
		try {
			//화면으로부터 넘어온 장바구니에 담을 정보와 현재 로그인한 회원의 이메일 정보를 이용하여 장바구니에 상품을 담는 로직을 호출
			cartItemId = cartService.addCart(cartItemDTO, email);
		}catch(Exception e) {
		
		 return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		//결과값으로 생성된 장바구니 상품 아이디와 요쳥이 성공하였다는 HTTP 응답상태코드를 반환
		return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/cart")
    public String orderHist(Principal principal, Model model){
        List<CartDetailDTO> cartDetailList = cartService.getCartList(principal.getName());
        model.addAttribute("cartItems", cartDetailList);
        return "cart/cartList";
    }
	
	
	
	
	
	
}


