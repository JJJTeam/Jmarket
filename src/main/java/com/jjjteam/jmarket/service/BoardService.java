package com.jjjteam.jmarket.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjjteam.jmarket.model.Board;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.repository.BoardRepository;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board){

        boardRepository.save(board);
    }
    
    //게시글 리스트 처리
    public List<Board> boardList(){
        return  boardRepository.findAll();
    }
    
    
}