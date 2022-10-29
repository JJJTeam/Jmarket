package com.jjjteam.jmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jjjteam.jmarket.model.Board;
//DB에 접근하는 메서드들을 사용하기 위한 인터페이스
//DAO
//자동으로 bean등록이 되어 @Repository 생략가능
public interface BoardRepository extends JpaRepository<Board, Integer>{

	//이렇게생성하고, 테스트에서 내용물 잘 들어가는지 확인
	
	Board findBySubject(String subject);
	Board findBySubjectAndContent(String subject, String content);
	
	
	//페이징처리 아래에 로직 입력 
	
}