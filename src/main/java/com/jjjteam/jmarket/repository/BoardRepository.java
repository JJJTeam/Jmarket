package com.jjjteam.jmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jjjteam.jmarket.model.Board;
//DB에 접근하는 메서드들을 사용하기 위한 인터페이스
//DAO
//자동으로 bean등록이 되어 @Repository 생략가능
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}