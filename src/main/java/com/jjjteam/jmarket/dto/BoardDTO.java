package com.jjjteam.jmarket.dto;

import lombok.Data;

@Data
public class BoardDTO {

	private int uid;
	private String subject;
	private String contents;
	private String name;
	private String viewcont;
	private String regdate;
	
	public BoardDTO() {
		
	}
	
	public BoardDTO(int uid, String subject, String contents, String name, String viewcont , String regdate) {
		this.uid = uid;
		this.subject = subject;
		this.contents = contents;
		this.name = name;
		this.viewcont = viewcont;
		this.regdate = regdate;
	}
	
	
	
}
