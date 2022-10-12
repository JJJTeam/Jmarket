package com.JJJTeam.Jmarket.dto;




import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.JJJTeam.Jmarket.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

@Inject 
SqlSession sql;

@Override
public void join(MemberDTO dto) throws Exception{
		
		sql.insert("memberMapper.register", dto);
}

}
