package com.jjjteam.jmarket.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jjjteam.jmarket.model.Board;
import com.jjjteam.jmarket.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	//생성자로 주입
	private final BoardRepository boardRepository;
	
	//게시물 등록
	public void create(String subject, String content) {
		Board board = new Board();
		board.setSubject(subject);
		board.setContent(content);
		board.setCreateDate(LocalDateTime.now());
		this.boardRepository.save(board);
	}
	
	//게시물 리스트
	public List<Board> getList(){
		return this.boardRepository.findAll();
	}
	
	//질문상세보기 리스트
	
	
	
}
