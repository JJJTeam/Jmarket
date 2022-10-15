package com.jjjteam.jmarket.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jjjteam.jmarket.model.Board;
import com.jjjteam.jmarket.service.BoardService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	//리스트 
		@GetMapping("/list")
		public String list() throws Exception{
			
			return "/board/list";
		}

		
//	//리스트 
//	@GetMapping("/board/list")
//	public String list(Model model) {
//		model.addAttribute("board", boardService.list());
//		return "/board/list";
//	}
	
	
	// 상세
	@GetMapping("/detail/{idx}")
	public String detail(@PathVariable int idx, Model model) {
		model.addAttribute("board", boardService.detail(idx));
		return "detail";
	}
	
	// 글 작성 
	@GetMapping("/register")
	public String registerGet() {
		return "/board/register";
	}
	
	//글작성 
	@PostMapping("/register")
	public String registerPost(Board board) {
		boardService.register(board);
		return "redirect:/board/list";
	}
	
	
	//글 수정 
	@GetMapping("/update/{idx}")
	public String updateGet(@PathVariable int idx, Model model) {
		model.addAttribute("board", boardService.detail(idx));
		return "update";
	}
	
	//글 수정
	@PostMapping("/update")
	public String updatePost(Board board) {
		
		boardService.update(board);
		return "redirect:/board/list";
	}
	
	//글 삭제 
	@GetMapping("/delete/{idx}")
	public String delete(@PathVariable int idx) {
		
		boardService.delete(idx);
		return "redirect:/board/list";
	}
	
	
	
	
	
}
