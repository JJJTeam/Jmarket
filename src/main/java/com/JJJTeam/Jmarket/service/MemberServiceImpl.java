package com.JJJTeam.Jmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.JJJTeam.Jmarket.dao.MemberDAO;
import com.JJJTeam.Jmarket.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	 @Autowired 
	MemberDAO dao;
	@Override
	public void join(MemberDTO dto) throws Exception{
		dao.join(dto);
	}
}
