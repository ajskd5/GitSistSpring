package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
//	@Select("SELECT no, goods_name, goods_price, goods_poster, num "
//			+ "FROM (SELECT no, goods_name, goods_price, goods_poster, rownum as num "
//			+ "FROM (SELECT no, goods_name, goods_price, goods_poster "
//			+ "FROM ${table_name} ORDER BY no ASC)) "
//			+ "WHERE num BETWEEN #{start} AND ${end}")
	public List<GoodsVO> goodsListData(Map map){
		return mapper.goodsListData(map);
	}
//	@Select("SELECT CEIL(COUNT(*)/12.0) FROM ${table_name}")
	public int goodsTotalPage(Map map) {
		return mapper.goodsTotalPage(map);
	}
	
//	@Select("SELECT * FROM ${table_name} "
//			+ "WHERE no=#{no}")
	public GoodsVO goodsDetailData(Map map) {
		mapper.goodsHitIncrement(map);
		return mapper.goodsDetailData(map);
	}
	
//	@Select("SELECT no, goods_name, goods_price, goods_poster, rownum "
//			+ "FROM (SELECT no, goods_name, goods_price, goods_poster "
//			+ "FROM ${table_name} ORDER BY no ASC) "
//			+ "WHERE rownum<=6")
	public List<GoodsVO> goodsMainData(Map map){
		return mapper.goodsMainData(map);
	}
	
	public List<GoodsVO> goodsFooterData(){
		return mapper.goodsFooterData();
	}
}
