package com.jjjteam.jmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjjteam.jmarket.model.Board;

/*
 * BoardRepository 인터페이스는
 * JpaRepository를 상속 받아서 객체가되는 클래스인
 * Board와 Board테이블에 기본키인 idx의 자로형을 
 * 래퍼 클래스 형태로 넣어주면 된다.
 * 
 * 
 * 이 인터페이스를 이용해서 JPA를 custom도 할 수 있지만 여기서는 기본적인
 * CRUD만 할 거기 때문에 이상태로 
 * */
public interface BoardRepository extends JpaRepository<Board, Integer> {

	
}
