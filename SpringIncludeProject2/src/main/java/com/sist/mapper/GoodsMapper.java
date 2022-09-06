package com.sist.mapper;
import java.util.*;
import org.apache.ibatis.annotations.Select;
import com.sist.dao.*;

public interface GoodsMapper {
	/*
	 *  $ => 테이블, 컬럼 => ''변환 X
	 *  # => 일반 데이터 => ''변환
	 */
	@Select("SELECT no, goods_name, goods_price, goods_poster, num "
			+ "FROM (SELECT no, goods_name, goods_price, goods_poster, rownum as num "
			+ "FROM (SELECT no, goods_name, goods_price, goods_poster "
			+ "FROM ${table_name} ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/5.0) FROM ${table_name}")
	public int goodsTotalPage(Map map);
	
	@Select("SELECT * FROM ${table_name} WHERE no=#{no}")
	public GoodsVO goodsDetailData(Map map);
}
