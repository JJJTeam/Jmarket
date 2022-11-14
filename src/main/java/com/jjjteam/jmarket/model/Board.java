package com.jjjteam.jmarket.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Board {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//@ManyToOne(fetch= FetchType.EAGER) //기본패치전략,반드시 들고와야함 
	//@JoinColumn(name="id") //role 아이디 => 관리자아이디 
	//private Role roles; //db에서는 안되지만 ORM에서는 object를 사용할수잇음 
	
	//@JoinColumn(name="id") //role 아이디 => 관리자아이디 
    //private User users; //db에서는 안되지만 ORM에서는 object를 사용할수잇음 
		
	
	
	@Column(length =200)
	//private String title;
	private String subject;
	
	//@Column(columnDefinition ="TEXT")
	@Lob // 섬머노트 라이브러리로 <html>태그 섞여 디자인 될것이니 대용량데이터 
	private String content;

	
	private LocalDateTime createDate;
	
	
	//게시판에 글쓴이 추가
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name="userId")
//	private User user;


	
}