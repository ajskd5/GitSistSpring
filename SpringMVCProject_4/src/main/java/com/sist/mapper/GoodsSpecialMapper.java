package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sist.dao.GoodsVO;

public interface GoodsSpecialMapper {
	@Select("SELECT no, goods_name as name, goods_poster as poster, goods_price as price, num "
			+ "FROM (SELECT no, goods_name, goods_poster, goods_price, rownum as num "
			+ "FROM (SELECT /*+ INDX_ASC(goods_special ga_no_pk)*/no, goods_name, goods_poster, goods_price "
			+ "FROM goods_special)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsSpecialListData(Map map);
	
	@Results({
		@Result(property = "name", column = "goods_name"),
		@Result(property = "sub", column = "goods_sub"),
		@Result(property = "price", column = "goods_price"),
		@Result(property = "discount", column = "goods_discount"),
		@Result(property = "first_price", column = "goods_first_price"),
		@Result(property = "delivery", column = "goods_delivery"),
		@Result(property = "poster", column = "goods_poster")
	})
	@Select("SELECT * FROM goods_special "
			+ "WHERE no=#{no}")
	public GoodsVO goodsSpecialDetailData(int no);
}
