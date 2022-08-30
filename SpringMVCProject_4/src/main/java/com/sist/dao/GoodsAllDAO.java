package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.GoodsAllMapper;
/*
 *  1. 클래스 메모리 할당 요청
 *  	@Repository
 *  2. 스프링에서 생성된 클래스 중 사용해야 하는 클래스의 주소값 받는다
 *  	@Autowired
 */
@Repository
public class GoodsAllDAO {
	@Autowired
	private GoodsAllMapper mapper;
	
	public List<GoodsVO> goodsAllListData(Map map){
		return mapper.goodsAllListData(map);
	}
	
	public GoodsVO goodsAllDetailData(int no) {
		return mapper.goodsAllDetailData(no);
	}
	
	public int goodsAllTotalpage() {
		return mapper.goodsAllTotalPage();
	}
}
