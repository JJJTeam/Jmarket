package com.jjjteam.jmarket.effectiveness;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BoardForm {
/*
 * 화면에 전달되는 입력값을 검증하기 위해 폼 클래스필요 
 * 화면의 입력항목 subject, content에 대응하는 BoardForm 클래스
 * */
	
	@NotEmpty(message = "제목을 입력하세요.")
	@Size(max=200)
	private String subject;
	
	@NotEmpty(message="공지사항에 알맞은 내용을 넣어주세요.")
	private String content;
	
	
	
	

	
}
