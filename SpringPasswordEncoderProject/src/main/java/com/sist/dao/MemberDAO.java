package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	
	// 아이디 중복 체크 
//	@Select("SELECT COUNT(*) FROM spring_join WHERE id=#{id}")
	public int memberIdCheck(String id) {
		return mapper.memberIdCheck(id);
	}
	
	// 회원가입
//	@Insert("INSERT INTO spring_join VALEUS("
//			+ "#{id}, #{pwd}, #{name}, #{sex}, #{birthday}, #{email}, #{post}, #{addr1}, #{addr2}, #{tel}, #{content}, #{sessionId}, #{limited}, 'ROLE_USER'")
	public void memberJoinInsert(MemberVO vo) {
		mapper.memberJoinInsert(vo);
	}
	
	// 로그인 ==> 복호화
//	@Select("SELECT pwd, name, role FROM spring_join WHERE id=#{id}")
	public MemberVO memberJoinInfoData(String id) {
		return mapper.memberJoinInfoData(id);
	}
}
