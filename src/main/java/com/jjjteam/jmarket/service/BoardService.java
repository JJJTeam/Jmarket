package com.jjjteam.jmarket.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jjjteam.jmarket.DataNotFoundException;
import com.jjjteam.jmarket.model.Board;
import com.jjjteam.jmarket.model.User;
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
	
	//공지사항 입력 후 저장 하는 로직 , 게시판글쓸때 작성자 넣기 
	public void registerForm(String subject, String content) {
		Board board = new Board();
		
		board.setSubject(subject);
		board.setContent(content);
		board.setCreateDate(LocalDateTime.now());
		//board.setAuthor(user);
		this.boardRepository.save(board);
	}
	
	
	//페이징 처리 로직, 작성일시 리스트역순으로 보여지게끔 
	public Page<Board> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.boardRepository.findAll(pageable);
		
	}

}
