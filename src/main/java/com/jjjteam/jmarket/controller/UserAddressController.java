package com.jjjteam.jmarket.controller;


import com.jjjteam.jmarket.dto.UserAddressDTO;

import com.jjjteam.jmarket.repository.UserAddressRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import com.jjjteam.jmarket.service.UserAddressService;
import com.jjjteam.jmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequiredArgsConstructor
@Secured("ROLE_USER")
@Slf4j
public class UserAddressController {
	private final UserAddressService userAddressService;
	@GetMapping("/member/shipping-address/drop")
	public String DropAddressProcess(@RequestParam(value="addressNo") Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
		userAddressService.dropUserAddress(id,userDetails.getId());
		return "redirect:/mypage/address";
	}
	@GetMapping("/member/shipping-address")
	public String ToShippingAddress() {
		return "/mypage/shippingAddress";
	}
	@PostMapping("/member/shipping-address")
	public String AddShippingAddress(@AuthenticationPrincipal UserDetailsImpl userDetails, UserAddressDTO userAddressDTO) {
		if (userAddressDTO.getDefaultAddress() != null){userAddressService.clearDefaultAddress(userDetails.getId());}
		userAddressService.saveNewAddress(userAddressDTO, userDetails.getId());
		return "redirect:/mypage/address";
	}
	@GetMapping("/member/shipping-address/update")
	public String ToUpdateAddressForm(@RequestParam(value="addressNo") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails,Model model) {
		UserAddressDTO userAddressDTO = userAddressService.loadAddressByUserAndId(id,userDetails.getId());
		model.addAttribute("userAddress",userAddressDTO);
		return "/mypage/updateAddress";
	}
	@PostMapping("/member/shipping-address/update")
	public String ToUpdateAddressProcess(@AuthenticationPrincipal UserDetailsImpl userDetails, UserAddressDTO userAddressDTO) {
		if (userAddressDTO.getDefaultAddress() != null){userAddressService.clearDefaultAddress(userDetails.getId());}
		userAddressService.updateUserAddress(userAddressDTO,userDetails.getId());
		return "redirect:/mypage/address";
	}
}
