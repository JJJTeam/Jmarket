package com.jjjteam.jmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

	// 게시판리스트
	@GetMapping("/list")
	public String list() {
		return "/board/list";
	}

	// 게시판 글쓰기
	@GetMapping("/register")
	public String write() {
		return "/board/register";
	}

}
