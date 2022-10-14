package com.jjjteam.jmarket.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jjjteam.jmarket.service.BoardService;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	
	//리스트 
	@GetMapping("/list")
	public String list() {
		return "list";
	}
	
	// 상세
	@GetMapping("/detail/{idx)")
	public String detail() {
		return "detail";
	}
	
	// 글 작성 
	@GetMapping("/register")
	public String registerGet() {
		return "register";
	}
	
	//글작성 
	@PostMapping("/register")
	public String registerPost() {
		return "redirect:/board/list";
	}
	
	
	//글 수정 
	@GetMapping("/update/{idx}")
	public String updateGet() {
		return "update";
	}
	
	//글 수정
	@PostMapping("/update")
	public String updatePost() {
		return "redirect:/board/list";
	}
	
	//글 삭제 
	@GetMapping("/delete/{idx}")
	public String delete() {
		return "list";
	}
	
	
	
	
	
}
