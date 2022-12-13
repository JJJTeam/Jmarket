package com.jjjteam.jmarket.controller;

import com.jjjteam.jmarket.dto.OrderHistDTO;
import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import com.jjjteam.jmarket.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

	//생성자주입
	private final OrderService orderService;


	//구매이력을 조회하는 호출하는 메서드
	@GetMapping(value= {"/orders", "/orders/{page}"})
	public String orderHist(@PathVariable("page") Optional<Integer> page, @AuthenticationPrincipal UserDetailsImpl principal, Model model) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get(): 0, 4); //한번에 가져올 주문의 개수는 4개로 설정
		//현재 로그인한 회원은 이메일과 페이징 객체를 파라미터로 전달하여 화면에 전달할 주문 목록데이터를 리턴 값으로 받는다.
		Page<OrderHistDTO> ordersHistDTOList = orderService.getOrderList(principal.getId(), pageable);
		model.addAttribute("orders", ordersHistDTOList);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage",5);
		
		return "order/orderHist";
	}
	
	//주문취소
	@PostMapping("/order/{orderId}/cancel")
    public @ResponseBody ResponseEntity cancelOrder(@PathVariable("orderId") Long orderId , @AuthenticationPrincipal UserDetailsImpl userDetails){

		//자바스크립에서 취소할 주문 번호는 조작이 가능하므로 다른사람의 주문을 취소하지 못하도록 주문취소 권한을 검사
        if(!orderService.validateOrder(orderId,userDetails.getId())){
            return new ResponseEntity<String>("주문 취소 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        orderService.cancelOrder(orderId);
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }
	
	
	
	
}
