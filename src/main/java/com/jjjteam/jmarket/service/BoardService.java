package com.jjjteam.jmarket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jjjteam.jmarket.DataNotFoundException;
import com.jjjteam.jmarket.model.Board;
import com.jjjteam.jmarket.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	//생성자로 주입
	private final BoardRepository boardRepository;
	
	
	//리스트 보여주기
	public List<Board> getList(){
		return this.boardRepository.findAll();
	}
	
	
	
	//상세에 내용 보여주기 
	public Board getBoard(Integer id) {
		Optional<Board> board = this.boardRepository.findById(id);
		
		if(board.isPresent()) {
			return board.get();
		}else {
			throw new DataNotFoundException("board not found");
		}
		
		
	}
	
	//게시물 등록
//	public void create(String subject, String content) {
//		Board board = new Board();
//		board.setSubject(subject);
//		board.setContent(content);
//		board.setCreateDate(LocalDateTime.now());
//		this.boardRepository.save(board);
//	}
	

	
	//질문상세보기 리스트
	
	
	
}
