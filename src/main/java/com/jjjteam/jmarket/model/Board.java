package com.jjjteam.jmarket.model;

import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


//테이블과 맵핑되는 Board클래스
/*
 * 1.
 * lombok으로 getter,setter constructor를 생성하는데
 * JPA에서는 @NoArgsConstructor는 필수로 들어가 있어야 한다.
 * 
 * 2.
 * @Entity 애노테이션은 이 클래스가 @Entity라는걸 알려주기 위해 필수
 * 
 * 3.
 * @Id 애노테이션은 idx가 기본키라는걸 명시
 *
 * */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Board{
	
	@Id
	@GeneratedValue
	private int idx;
	
	private String title;
	private String content;
	private String writer;
	
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
}

