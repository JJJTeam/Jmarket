package com.jjjteam.jmarket.controller;



import com.jjjteam.jmarket.dto.UserAddressDTO;
import com.jjjteam.jmarket.security.services.UserDetailsImpl;
import com.jjjteam.jmarket.service.UserAddressService;
import com.jjjteam.jmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.regex.Pattern;


@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypage")
@Secured("ROLE_USER")
@Controller
public class UserController {

	private final UserService userService;
	private final UserAddressService userAddressService;

	private final PasswordEncoder encoder;

	@GetMapping("/address")
	public String ToMyPageAddressList(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		List<UserAddressDTO> addressList = userAddressService.loadAddressListByUserId(userDetails.getId());
		model.addAttribute("addressList",addressList);
		return "/mypage/address";
	}

	@GetMapping("/modify")
	public String ToMyPageInfo(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		List<UserAddressDTO> addressList = userAddressService.loadAddressListByUserId(userDetails.getId());
		model.addAttribute("addressList",addressList);
		return "/mypage/modify";
	}
//	@PostMapping("/info")
//	public String CheckPassInfo(@AuthenticationPrincipal UserDetailsImpl userDetails, String password,Model model){
//		if(encoder.matches(password, userDetails.getPassword())){return "/mypage/modify";}
//		else {return "/mypage/passerror";}
//	}
	@GetMapping("/change-email")
	public String ToChangeEmailPage(@AuthenticationPrincipal UserDetailsImpl userDetails,Model model) {
		model.addAttribute("user",userService.returnUserDetailById(userDetails.getId()));
		return "/mypage/change-email";
	}
//	@PostMapping("/change-email")
//	public String ToChangeEmailProcess(@AuthenticationPrincipal UserDetailsImpl userDetails,
//									   @Valid String newEmail,BindingResult bindingResult, String inputPassword) {
//		@Email String email = newEmail;
//		if(bindingResult.hasErrors()){return "index";}
//		if(encoder.matches(inputPassword, userDetails.getPassword())){
//			userService.changeUserEmail(userDetails.getId(),newEmail);
//			return "/index";
//		} else {
//			return "/mypage/passerror";
//		}
//	}
//	@PostMapping("/change-email")
//	public String ToChangeEmailProcess(@AuthenticationPrincipal UserDetailsImpl userDetails, String newEmail, Model model , String inputPassword) {
//		if(!Pattern.matches("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$", newEmail)){
//			model.addAttribute("msg","올바른형식으로 입력해주시기 바랍니다.");
//			model.addAttribute("user",userService.returnUserDetailById(userDetails.getId()));
//			return "/mypage/change-email";
//		}
//		if(encoder.matches(inputPassword, userDetails.getPassword())){
//			userService.changeUserEmail(userDetails.getId(),newEmail);
//			return "/index";
//		} else {
//			return "/mypage/passerror";
//		}
//	}

	@PostMapping("/change-email")
	public String ToChangeEmailProcess(@AuthenticationPrincipal UserDetailsImpl userDetails, BindingResult bindingResult,String newEmail, Model model , String inputPassword) {
		if(bindingResult.hasErrors()){return "index";}
		if(encoder.matches(inputPassword, userDetails.getPassword())){
			userService.changeUserEmail(userDetails.getId(),newEmail);
			return "/index";
		} else {
			return "/mypage/passerror";
		}
	}

	@GetMapping("/change-password")
	public String ToChangePassword(Model model) {
		return "/mypage/change-password";
	}
	@GetMapping("/change-phone-number")
	public String ToChangePhoneNumber(Model model) {
		return "/mypage/change-phone-number";
	}
	@GetMapping("/delete-account")
	public String ToDeleteAccount(Model model) {
		return "/mypage/delete-account";
	}
}
