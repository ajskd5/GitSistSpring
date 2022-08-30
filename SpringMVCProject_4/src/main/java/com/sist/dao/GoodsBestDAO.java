package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.GoodsBestMapper;

@Repository
public class GoodsBestDAO {
	@Autowired
	private GoodsBestMapper mapper;
	
	public List<GoodsVO> goodsBestListData(Map map){
		return mapper.goodsBestListData(map);
	}
	
	public GoodsVO goodsBestDetailData(int no) {
		return mapper.goodsBestDetailData(no);
	}
}
