package com.jjjteam.jmarket.controller;


import com.jjjteam.jmarket.dto.CartDetailDTO;
import com.jjjteam.jmarket.dto.CartItemDTO;
import com.jjjteam.jmarket.dto.CartOrderDTO;
import com.jjjteam.jmarket.dto.UserAddressDTO;
import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import com.jjjteam.jmarket.service.CartService;
import com.jjjteam.jmarket.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;
    private final UserAddressService userAddressService;
	
	@PostMapping(value="/cart")
	public @ResponseBody ResponseEntity order(@RequestBody @Valid CartItemDTO cartItemDTO, BindingResult bindingResult, @AuthenticationPrincipal UserDetailsImpl principal) {
		//장바구니에 담을 상품정보를 받는 cartItemDTO객체에 데이터 바인딩시 에러가 있는지 검사 
		
		if(bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
                
            }
			return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
		}
		
	    Long id = principal.getId(); //현재로그인한 회원의 이메일정보를 변수에 저장
		Long cartItemId;
		
		try {
			//화면으로부터 넘어온 장바구니에 담을 정보와 현재 로그인한 회원의 이메일 정보를 이용하여 장바구니에 상품을 담는 로직을 호출
			cartItemId = cartService.addCart(cartItemDTO, id);
		}catch(Exception e) {
		 return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		//결과값으로 생성된 장바구니 상품 아이디와 요쳥이 성공하였다는 HTTP 응답상태코드를 반환
		return new ResponseEntity<>(cartItemId, HttpStatus.OK);
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
            return new ResponseEntity<>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
        } else if(!cartService.validateCartItem(cartItemId, principal.getId())){
            return new ResponseEntity<>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        cartService.updateCartItemCount(cartItemId, count);
        return new ResponseEntity<>(cartItemId, HttpStatus.OK);
    }
    
    
    @DeleteMapping(value = "/cartItem/{cartItemId}")
    public @ResponseBody ResponseEntity deleteCartItem(@PathVariable("cartItemId") Long cartItemId, @AuthenticationPrincipal UserDetailsImpl principal){

        if(!cartService.validateCartItem(cartItemId, principal.getId())){
            return new ResponseEntity<>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        cartService.deleteCartItem(cartItemId);

        return new ResponseEntity<>(cartItemId, HttpStatus.OK);
    }


    @PostMapping(value = "/cart/orders")
    @ResponseBody
    public ResponseEntity orderCartItem(@RequestBody CartOrderDTO cartOrderDTO, @AuthenticationPrincipal UserDetailsImpl principal){
        List<CartOrderDTO> cartOrderDTOList = cartOrderDTO.getCartOrderDTOList();
        System.out.println("cartOrderDTOList :?? " + cartOrderDTOList);
        
        
        if(cartOrderDTOList == null || cartOrderDTOList.size() == 0){
            return new ResponseEntity<>("주문할 상품을 선택해주세요", HttpStatus.FORBIDDEN);
        }
        
        for (CartOrderDTO cartOrder : cartOrderDTOList) {
            if(!cartService.validateCartItem(cartOrder.getCartItemId(), principal.getId())){
                return new ResponseEntity<>("주문 권한이 없습니다.", HttpStatus.FORBIDDEN);
            }
        }

        Long orderId = cartService.orderCartItem(cartOrderDTOList,principal.getId(),cartOrderDTO.getAddressNum());
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }
    

}



