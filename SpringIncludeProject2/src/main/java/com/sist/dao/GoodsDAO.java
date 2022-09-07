package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.*;
import java.util.*;
/*
 *  1. Controller (Model) ==> @Controller, @RestController
 *  2. 데이터베이스 (DAO, Service) ==> @Repository, @Service
 *  3. 데이터분석 (Manager) ==> @Component
 */
// 순수하게 데이터베이스 연결 ==> 결과값을 받아서 JSP로 전송 (@Controller)
@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	//리스트
	public List<GoodsVO> goodsListData(Map map){
		return mapper.goodsListData(map);
	}
	
	// 총 페이지
	public int goodsTotalPage(Map map) {
		return mapper.goodsTotalPage(map);
	}
	
	// 상세보기
	public GoodsVO goodsDetailData(Map map) {
		return mapper.goodsDetailData(map);
	}
	
	
	// 로그인
	public MemberVO memberLogin(String id, String pwd) {
		MemberVO vo = new MemberVO();
		int count = mapper.idCount(id);
		if(count == 0	) {
			vo.setMsg("NOID");
		} else {
			MemberVO dbVO = mapper.memberGetPassword(id);
			if(pwd.equals(dbVO.getPwd())) {
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(dbVO.getName());
				vo.setMsg("OK");
			} else {
				vo.setMsg("NOPWD");
			}
		}
			
		return vo;
	}
	
	// 검색
	public List<GoodsVO> goodsFindData(Map map){
		return mapper.goodsFindData(map);
	}
}
