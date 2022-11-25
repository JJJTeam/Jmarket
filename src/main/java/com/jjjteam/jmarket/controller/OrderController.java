package com.jjjteam.jmarket.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjjteam.jmarket.dto.OrderDTO;
import com.jjjteam.jmarket.dto.OrderHistDTO;
import com.jjjteam.jmarket.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

	//생성자주입
	private final OrderService orderService;
	
	
	
	/*
	 * 스프링에서 비동기처리할때 @ResponseBody, @RequestBody 어노테이션을 사용
	 * - @ResponseBody : HTTP 요청의 본문에 body에 담긴 내용을 자바 객체로 전달
	 * - @RequestBody : 자바 객체를 HTTP요청의 body로 전달
	 */
	@PostMapping(value="/order")
	public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderDTO orderDTO, BindingResult bindingResult, Principal principal) {
		
		
		//주문정보 orderDTO 객체에 데이터 바인딩시 에러가 있는지 검사
		if(bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			
			for(FieldError fieldError : fieldErrors) {
				sb.append(fieldError.getDefaultMessage());
			}
			//에러 정보를 ResponseEntity객체에 담아서 반환	
			return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
		}
		
		/*
		 * 현재로그인 유저의 정보를 얻기 위해서 
		 * @Controller 어노테이션이 선언된 클래스에서 메서드 인자로 
		 * principal 객체를 넘겨 줄 경우 해당 객체에 직접접근 할수 있습니다.
		 * 
		 * principal객체에서 현재 로그인한 회원의 이메일 정보를 조회합니다.
		 * */
		String email = principal.getName();
		Long orderId;
		
		try {
			//화면으로부터 넘어오는 주문정보와 회원의 이메일 정보를 이용하여 주문로직을 호출
			orderId = orderService.order(orderDTO, email);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		//결과값으로 생성된 주문번호와 요청이 성공했다는 HTTP 응답 상태 코드를 반환
		return new ResponseEntity<Long>(orderId, HttpStatus.OK);
	}
	
	
	//구매이력을 조회하는 호출하는 메서드
//	@GetMapping(value= {"/orders", "orders/{page}"})
//	public String orderHist(@PathVariable("page") Optional<Integer> page, Principal principal, Model model) {
//		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,4);
//		
//		Page<OrderHistDTO> ordersHistDTOList = orderService.getOrderList(principal.getName(), pageable);
//		model.addAttribute("orders", ordersHistDTOList);
//		model.addAttribute("page", pageable.getPageNumber());
//		model.addAttribute("maxPage",5);
//		
//		return "order/orderHist";
//	}
	
	
	
	
}
