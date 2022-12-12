package com.jjjteam.jmarket.controller;


import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.jjjteam.jmarket.dto.*;
import com.jjjteam.jmarket.service.UserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import com.jjjteam.jmarket.service.CartService;

import lombok.RequiredArgsConstructor;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;
    private final UserAddressService userAddressService;
	
	@PostMapping(value="/cart")
	public @ResponseBody ResponseEntity order(@RequestBody @Valid CartItemDTO cartItemDTO, BindingResult bindingResult, @AuthenticationPrincipal UserDetailsImpl principal) {
		System.out.println("ddd@@@@ 카트 컨트롤러 !!!1 ");
		System.out.println("ddd@@@@ cartItemDTO : " + cartItemDTO.getItemId());
		
		//장바구니에 담을 상품정보를 받는 cartItemDTO객체에 데이터 바인딩시 에러가 있는지 검사 
		
		if(bindingResult.hasErrors()) {
			System.out.println("@@@@@1번에러");
			StringBuilder sb = new StringBuilder();
			
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			
			System.out.println("bindingResult.getFieldErrors @@@@ : " + bindingResult.getFieldErrors());
			
			for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
                
            }
			return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
		}
		
	    Long id = principal.getId(); //현재로그인한 회원의 이메일정보를 변수에 저장
	    System.out.println("id" + id);
		Long cartItemId;
		
		try {
			System.out.println(" cartItemDTO  2333333 :" + cartItemDTO.toString());
			System.out.println(" id   121212w1212111:" + id);
			//화면으로부터 넘어온 장바구니에 담을 정보와 현재 로그인한 회원의 이메일 정보를 이용하여 장바구니에 상품을 담는 로직을 호출
			cartItemId = cartService.addCart(cartItemDTO, id);
			System.out.println("cartItemDTO id   @@@  :" + cartItemId);	
			
		}catch(Exception e) {
		System.out.print(" @@@@ e :" + e);
		 return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		//결과값으로 생성된 장바구니 상품 아이디와 요쳥이 성공하였다는 HTTP 응답상태코드를 반환
		return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/cart")
    public String orderHist(@AuthenticationPrincipal UserDetailsImpl principal, Model model){
        
		List<CartDetailDTO> cartDetailList = cartService.getCartList(principal.getId());
        try {
            UserAddressDTO defaultUserAddressDTO = userAddressService.loadDefaultAddressByUserId(principal.getId());
            model.addAttribute("defaultUserAddress", defaultUserAddressDTO);
        }
        catch (Exception e){

        }

        model.addAttribute("cartItems", cartDetailList);

        return "cart/cartList";
    }
	
	
    @PatchMapping(value = "/cartItem/{cartItemId}")
    public @ResponseBody ResponseEntity updateCartItem(@PathVariable("cartItemId") Long cartItemId, int count, @AuthenticationPrincipal UserDetailsImpl principal){

        if(count <= 0){
            return new ResponseEntity<String>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
        } else if(!cartService.validateCartItem(cartItemId, principal.getId())){
            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        cartService.updateCartItemCount(cartItemId, count);
        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }
    
    
    @DeleteMapping(value = "/cartItem/{cartItemId}")
    public @ResponseBody ResponseEntity deleteCartItem(@PathVariable("cartItemId") Long cartItemId, @AuthenticationPrincipal UserDetailsImpl principal){

        if(!cartService.validateCartItem(cartItemId, principal.getId())){
            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        cartService.deleteCartItem(cartItemId);

        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }
    
    
//    @PostMapping(value = "/cart/orders")
//    public @ResponseBody ResponseEntity orderCartItem(@RequestBody CartOrderDTO cartOrderDTO, @AuthenticationPrincipal UserDetailsImpl principal){
//    	System.out.println("@@@@ cart 페이지에서 주문하기 버튼 클릭 1 ");
//
//    	List<CartOrderDTO> cartOrderDTOList = cartOrderDTO.getCartOrderDTOList();
//
//        System.out.println("@@@@ cart 페이지에서 주문하기 버튼 클릭 1: cartOrderDtoList : " + cartOrderDTO.toString() );
//
//
//        if(cartOrderDTOList == null || cartOrderDTOList.size() == 0){
//        	System.out.println("@@@@ cart 페이지에서 주문하기 버튼 클릭 2 ");
//        	return new ResponseEntity<String>("주문할 상품을 선택해주세요", HttpStatus.FORBIDDEN);
//
//        }
//        System.out.println("@@@@ cart 페이지에서 주문하기 버튼 클릭2 메서드 아래  ");
//
//
//        for (CartOrderDTO cartOrder : cartOrderDTOList) {
//            if(!cartService.validateCartItem(cartOrder.getCartItemId(), principal.getId())){
//            	System.out.println("@@@@ cart 페이지에서 주문하기 버튼 클릭 3 ");
//            	return new ResponseEntity<String>("주문 권한이 없습니다.", HttpStatus.FORBIDDEN);
//            }
//        }
//
//        Long orderId = cartService.orderCartItem(cartOrderDTOList, principal.getId());
//        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
//    }

    @PostMapping(value = "/cart/orders")
    @ResponseBody
    public ResponseEntity orderCartItem(@RequestBody CartOrderDTO cartOrderDTO, @AuthenticationPrincipal UserDetailsImpl principal){
        System.out.println("@@@@ cart 페이지에서 주문하기 버튼 클릭 1 ");

        List<CartOrderDTO> cartOrderDTOList = cartOrderDTO.getCartOrderDTOList();

        System.out.println("@@@@ cart 페이지에서 주문하기 버튼 클릭 1: cartOrderDtoList : " + cartOrderDTO.toString() );


        if(cartOrderDTOList == null || cartOrderDTOList.size() == 0){
            System.out.println("@@@@ cart 페이지에서 주문하기 버튼 클릭 2 ");
            return new ResponseEntity<String>("주문할 상품을 선택해주세요", HttpStatus.FORBIDDEN);

        }
        System.out.println("@@@@ cart 페이지에서 주문하기 버튼 클릭2 메서드 아래  ");


        for (CartOrderDTO cartOrder : cartOrderDTOList) {
            if(!cartService.validateCartItem(cartOrder.getCartItemId(), principal.getId())){
                System.out.println("@@@@ cart 페이지에서 주문하기 버튼 클릭 3 ");
                return new ResponseEntity<String>("주문 권한이 없습니다.", HttpStatus.FORBIDDEN);
            }
            log.warn("for문 에러:error{}",cartOrderDTOList);
        }

        Long orderId = cartService.orderCartItem(cartOrderDTOList, principal.getId());
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }
    

}



