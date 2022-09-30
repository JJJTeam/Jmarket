package com.JJJTeam.Jmarket.Mapper;

import org.apache.ibatis.annotations.Mapper;
import com.JJJTeam.Jmarket.DTO.MemberDTO;

@Mapper
public interface MemberMapper {
	void saveMember(MemberDTO memberDTO);
}
