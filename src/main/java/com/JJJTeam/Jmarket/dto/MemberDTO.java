package com.JJJTeam.Jmarket.dto;


import java.util.Date;
import lombok.Data;

@Data
public class MemberDTO {
	private String userId;
	private String userPass;
	private String userName;
	private Date regDate;
}
