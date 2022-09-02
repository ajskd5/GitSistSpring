package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.GoodsMapper;

@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	// 리스트
	public List<GoodsVO> goodsListData(Map map){
		return mapper.goodsListData(map);
	}
	// 총 페이지
	public int goodsTotalpage() {
		return mapper.goodsTotalpage();
	}
	
	// 상세보기
	public GoodsVO goodsDetailData(int no) {
		return mapper.goodsDetailData(no);
	}
}
