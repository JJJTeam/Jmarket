package com.jjjteam.jmarket.model;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//실제 DB의 테이블과 매칭되는 클래스이다.
//따라서 엔티티의 내부 구현을 캡슐화하고 UI계층에 노출시키지 않아야하는 것은 충분히 데이터 전달 역할로 DTO를 사용해야 할 이유로 볼 수 있다.

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Board {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) //

    private Integer id;
    private String title;
    private String content;


}