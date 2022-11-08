package com.jjjteam.jmarket.controller;



import com.jjjteam.jmarket.dto.UserAddressDTO;
import com.jjjteam.jmarket.dto.payload.request.SignUpRequest;
import com.jjjteam.jmarket.dto.payload.response.MessageResponse;
import com.jjjteam.jmarket.model.UserAddress;
import com.jjjteam.jmarket.repository.UserAddressRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import com.jjjteam.jmarket.service.UserAddressService;
import com.jjjteam.jmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@Secured("ROLE_USER")
@Slf4j
public class MemberController {

	private final UserService userService;
	private final UserAddressService userAddressService;
	private final UserRepository userRepository;
	private final UserAddressRepository userAddressRepository;

	@GetMapping("/member/shipping-address")
	public String ToShippingAddress() {
		return "/member/shippingAddress";
	}
	@PostMapping("/member/shipping-address")
	public String AddShippingAddress(@AuthenticationPrincipal UserDetailsImpl userDetails, UserAddressDTO userAddressDTO,
	Boolean checkboxValue) {

		userAddressService.saveNewAddress(userAddressDTO, checkboxValue, userDetails.getId());
		return "redirect:/member/mypageAddress";
	}
	@GetMapping("/member/shipping-address/update")
	public String ToUpdateAddressForm(@RequestParam(value="addressNo") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails,Model model) {
		UserAddressDTO userAddressDTO = userAddressService.loadAddressByUserAndId(id,userDetails.getId());
		model.addAttribute("userAddress",userAddressDTO);
		return "/member/updateAddress";
	}
	@PostMapping("/member/shipping-address/update")
	public String ToUpdateAddressProcess(@AuthenticationPrincipal UserDetailsImpl userDetails, UserAddressDTO userAddressDTO) {
		userAddressService.updateUserAddress(userAddressDTO,userDetails.getId());
		return "redirect:/member/mypageAddress";
	}

	@GetMapping("/member/mypageAddress")
	public String ToMyPageAddressList(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		List<UserAddressDTO> addressList = userAddressService.loadAddressListByUserId(userDetails.getId());
		model.addAttribute("addressList",addressList);
		return "/member/mypageAddress";
	}
	@PostMapping("/member/signup")
	public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {
		if (userService.existsByUserId(signUpRequest.getUserid())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userService.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
		}
		userService.registerUser(signUpRequest);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
