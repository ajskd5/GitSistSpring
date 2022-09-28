package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.GoodsVO;

public interface GoodsMapper {
	// 상품 리스트
	@Select("SELECT no, goods_name, goods_price, goods_poster, num "
			+ "FROM (SELECT no, goods_name, goods_price, goods_poster, rownum as num "
			+ "FROM (SELECT no, goods_name, goods_price, goods_poster "
			+ "FROM ${table_name} ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND ${end}")
	public List<GoodsVO> goodsListData(Map map);
	// 총페이지
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM ${table_name}")
	public int goodsTotalPage(Map map);
	
	// 상품 상세보기
	@Update("UPDATE ${table_name} SET "
			+ "hit=hit+1 WHERE no=#{no}")
	public void goodsHitIncrement(Map map);
	@Select("SELECT * FROM ${table_name} "
			+ "WHERE no=#{no}")
	public GoodsVO goodsDetailData(Map map);
	
	@Select("SELECT no, goods_name, goods_price, goods_poster, rownum "
			+ "FROM (SELECT no, goods_name, goods_price, goods_poster "
			+ "FROM ${table_name} ORDER BY no ASC) "
			+ "WHERE rownum<=6")
	public List<GoodsVO> goodsMainData(Map map);
}
