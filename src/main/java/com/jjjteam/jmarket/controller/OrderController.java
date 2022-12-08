package com.jjjteam.jmarket.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import com.jjjteam.jmarket.service.OrderService;

import lombok.RequiredArgsConstructor;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

	//생성자주입
	private final OrderService orderService;

    @PostMapping(value = "/order")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderDTO orderDTO, BindingResult bindingResult,  @AuthenticationPrincipal UserDetailsImpl principal){

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

     
        Long id = principal.getId();
        Long orderId;

        try {
            orderId = orderService.order(orderDTO, id);
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }
	
	
	//구매이력을 조회하는 호출하는 메서드
	@GetMapping(value= {"/orders", "/orders/{page}"})
	public String orderHist(@PathVariable("page") Optional<Integer> page, @AuthenticationPrincipal UserDetailsImpl principal, Model model) {
		System.out.println(" 구매이력을 조회하는 호출하는 메서드" );
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get(): 0, 4); //한번에 가져올 주문의 개수는 4개로 설정
		
		//현재 로그인한 회원은 이메일과 페이징 객체를 파라미터로 전달하여 화면에 전달할 주문 목록데이터를 리턴 값으로 받는다.
		Page<OrderHistDTO> ordersHistDTOList = orderService.getOrderList(principal.getId(), pageable);
		log.warn("ordersHistDTOList : {} ",ordersHistDTOList);
        log.warn("page : {} ",pageable.getPageNumber());

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
