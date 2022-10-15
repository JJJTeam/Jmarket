package com.jjjteam.jmarket.service;

import com.jjjteam.jmarket.model.Board;
import com.jjjteam.jmarket.repository.BoardRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
 * 
 * Service 단에서는 boardRepository를 이용해 데이터를 컨트롤
 * 등록,목록,상세보기,수정,삭제 
 * 
 */

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	public void register(Board board) {
		boardRepository.save(board);
	}
	
	//findAll을 할 때 idx를 기준으로 내림차순 정렬을 준것
	public List<Board> list(){
		return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "idx"));
	}
	
	public Board detail(int idx) {
		return boardRepository.findById(idx).orElse(null);
	}
	
	public void update(Board board) {
		boardRepository.save(board);
	}
	
	public void delete(int idx) {
		boardRepository.deleteById(idx);
	}

}
