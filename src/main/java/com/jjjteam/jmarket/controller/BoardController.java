package com.jjjteam.jmarket.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jjjteam.jmarket.effectiveness.BoardForm;
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
	
	//게시물등록페이지 이동
	
//	@GetMapping("/register_form")
//	public String registerForm(BoardForm boardForm) {
//		return "board/register";
//	}
	
	//게시물등록
//	@PostMapping("/register_form")
//	public String registerForm(@Valid BoardForm boardForm, BindingResult bindingResult) {
//		if(bindingResult.hasErrors()) {
//			return "board/register";
//		}
//		this.boardService.create(boardForm.getSubject(), boardForm.getContent());
//		return "";
//	}
	
	
	    
}
