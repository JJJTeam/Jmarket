package com.jjjteam.jmarket.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jjjteam.jmarket.service.BoardService;


@Controller
public class BoardController {
	
	private BoardService boardService;
	
	public BoardController(BoardService boarderService) {
		this.boardService = boardService;
	}
	
	@GetMapping("/")
	public String list(Model model) {
		List<BoardDTO> boardDTOList = boardService.getBoardList();
		model.addAttribute("boardList", boardDTOList);
		return "list.html";
	}
	
}
