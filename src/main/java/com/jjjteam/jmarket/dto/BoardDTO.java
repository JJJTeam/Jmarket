package com.jjjteam.jmarket.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.jjjteam.jmarket.model.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardDTO {
  
	
	private Integer id;
	
	private String subject;
	
	private String content;
	
	private LocalDateTime createDate;
	
	
    private String userId;


	
}