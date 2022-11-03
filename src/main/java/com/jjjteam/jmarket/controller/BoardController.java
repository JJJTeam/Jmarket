package com.jjjteam.jmarket.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.jjjteam.jmarket.model.Board;
import com.jjjteam.jmarket.repository.BoardRepository;
import com.jjjteam.jmarket.service.BoardService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	
	//생성자주입
	private final BoardRepository boardRepository;
	private final BoardService boardService;
	
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Board> boardList = this.boardRepository.findAll();
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	
	
	@RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
		Board board = this.boardService.getBoard(id);

		model.addAttribute("board", board);
		return "board/board_detail";
    }
	
	//게시물 등록 페이지 이동
	@GetMapping("/register_form")
	public String registerForm() {
		return "board/register";
	}
	
	
	
	@PostMapping("/register_form")
	public String registerForm(@RequestParam String subject, @RequestParam String content) {
	    // 게시판입력값 저장 로직 
		this.boardService.registerForm(subject, content);	
		return "redirect:/board/list"; //질문 저장 후 이동 
	}
	
	

	
	    
}
