package com.jjjteam.jmarket.controller;



import com.jjjteam.jmarket.constant.ValidText;
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
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor

@Slf4j
public class MemberController {

	private final UserService userService;
	private final UserAddressService userAddressService;
	private final UserRepository userRepository;
	private final UserAddressRepository userAddressRepository;

	private boolean phoneAuth = false;
	private String phoneNumberTemp;
	private String phoneNumberAuth;
	private Boolean checkId = null;
	private Boolean checkEmail = null;


	@Secured("ROLE_USER")
	@GetMapping("/member/mypageAddress")
	public String ToMyPageAddressList(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		List<UserAddressDTO> addressList = userAddressService.loadAddressListByUserId(userDetails.getId());
		model.addAttribute("addressList",addressList);
		return "/member/mypageAddress";
	}



	@PostMapping("/signup")
	public String registerUser(@Valid SignUpRequest signUpRequest, BindingResult bindingResult, Model model) {
		log.info(signUpRequest.toString());
		log.info("phoneNumberAuth : {}",phoneNumberAuth);
		log.info("signUpRequest.getUserPhoneNumber() : {}",signUpRequest.getUserPhoneNumber());
		log.info("phoneAuth: {}",phoneAuth);
		log.info("checkId: {}",checkId);
		log.info("checkEmail: {}",checkEmail);
		if(bindingResult.hasErrors()){
			return "signup";
		}
//		리펙터링 필요,
		String checkIdMessege = ValidText.getValidText("checkId",userService.existsByUserId(signUpRequest.getUserid()));
		String checkEmailMessege = ValidText.getValidText("checkEmail",userService.existsByEmail(signUpRequest.getEmail()));
		String phoneAuthMessege = ValidText.getValidText("phoneAuth",phoneAuth&&signUpRequest.getUserPhoneNumber().equals(phoneNumberAuth));
		List<String> messege = Arrays.asList(checkIdMessege,checkEmailMessege,phoneAuthMessege);
		List<String>resultMessege = messege.stream().filter(i -> !i.equals("pass")).collect(Collectors.toList());
		if(resultMessege.size()>0){
			model.addAttribute("messege",resultMessege);
			return "signup";
		}
//		if(checkEmail==null){
//			model.addAttribute("messege","이메일 중복확인을 해주세요");
//			return "signup";
//		}
//		if(checkId==null){
//			model.addAttribute("messege","아이디 중복확인을 해주세요.");
//			return "signup";
//		}
//		if(phoneAuth){
//			model.addAttribute("messege","전화번호 인증을 해주세요");
//			return "signup";
//		}
//		if(checkEmail!=true){
//			model.addAttribute("messege","중복된 이메일입니다.");
//			return "signup";
//		}
//		if(checkId!=true){
//			model.addAttribute("messege","중복된 아이디입니다.");
//			return "signup";
//		}
//		if(phoneAuth!=true){
//			model.addAttribute("messege","인증되지 않은 전화번호입니다.2");
//			return "signup";
//		}
//		if(!signUpRequest.getUserPhoneNumber().equals(phoneNumberAuth)){
//			model.addAttribute("messege","인증되지 않은 전화번호입니다.");
//			return "signup";
//		}
//		if (userService.existsByUserId(signUpRequest.getUserid())) {
//			model.addAttribute("messege","Username is already taken!.");
//			return "signup";
//		}
//		if (userService.existsByEmail(signUpRequest.getEmail())) {
//			model.addAttribute("messege","Email is already taken.");
//			return "signup";
//		}
//		userService.registerUser(signUpRequest);
		return "/index";
	}
	@GetMapping("/api/checkId")
	@ResponseBody
	public boolean checkId(@RequestParam(value="userId") String userId)	{
		return checkId = !userService.existsByUserId(userId);
	}
	@GetMapping("/api/checkEmail")
	@ResponseBody
	public boolean checkEmail(@RequestParam(value="email") String email){
		return checkEmail= !userService.existsByEmail(email);
	}
	@PostMapping("/api/phoneAuth")
	@ResponseBody
	public Boolean phoneAuth(@RequestBody String userPhoneNumber,HttpSession session) {
		phoneNumberTemp = userPhoneNumber.replaceAll("\"","");
		try { // 이미 가입된 전화번호가 있으면
			if(userService.memberTelCount(userPhoneNumber))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		String code = userService.sendRandomMessage(userPhoneNumber);
		session.setAttribute("rand", code);
		return false;
	}
	@PostMapping("/api/phoneAuthOk")
	@ResponseBody
	public Boolean phoneAuthOk(HttpSession session, HttpServletRequest request) {
		phoneAuth = false;
		String rand = (String) session.getAttribute("rand");
		String code = (String) request.getParameter("code");

		System.out.println(rand + " : " + code);
		if (rand.equals(code)) {
			session.removeAttribute("rand");
			phoneAuth = true;
			phoneNumberAuth= phoneNumberTemp;
			return false;
		}
		return true;
	}
}
