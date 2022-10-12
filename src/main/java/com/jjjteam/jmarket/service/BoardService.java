package com.jjjteam.jmarket.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

	private BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	@Transactional
	public List<BoardDTO> boards= boardRepository.findAll();
	List<BoardDTO> boardDTOList = new ArrayList<>();
	
	for(Board board: boards) {
		BoardDTO dto = BoardDTO.builder()
				.id(board.getId())
				.title(board.getTitle())
				.content(board.getContent())
				.writer(board.getWriter())
				.createdDate(board.getCreatedDate())
				.build();
		
		boardDTOList.add(dto);
				
	}
	return boardDTOList;

}
