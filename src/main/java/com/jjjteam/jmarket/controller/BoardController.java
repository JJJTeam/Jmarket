package com.jjjteam.jmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jjjteam.jmarket.model.Board;
import com.jjjteam.jmarket.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
//	@GetMapping("/")
//	public String index(Model model, @PageableDefault(size = 3, direction = Sort.Direction.DESC) Pageable pageable) {
//		Page<Board> paging = service.findAll(pageable);
//		model.addAttribute("board",paging);
//		// /WEB-INF/views/index.jsp
//		return "index";
//	}
	

	@GetMapping("/list")
	public String list(Model model, @PageableDefault(size = 3, direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Board> paging = service.findAll(pageable);
		model.addAttribute("board",paging);
		// /WEB-INF/views/index.jsp
		return "/board/list";
	}
	
	@GetMapping("/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	@GetMapping("/{b_no}")
	public String findById(@PathVariable int b_no, Model model) {
		model.addAttribute("board",service.findById(b_no));
		return "board/detail";
	}
	
	@GetMapping("/{b_no}/updateForm")
	public String updateForm(@PathVariable int b_no, Model model) {
		model.addAttribute("board",service.findById(b_no));
		return "board/updateForm";
	}
}

