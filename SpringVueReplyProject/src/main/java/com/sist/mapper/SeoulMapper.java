package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface SeoulMapper {
	@Select("SELECT no, poster, title, num "
			+ "FROM (SELECT no, poster, title, rownum as num "
			+ "FROM (SELECT no, poster, title "
			+ "FROM ${table_name} ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<SeoulVO> seoulListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0)FROM ${table_name}")
	public int seoulTotalPage(Map map);
	
	// 상세보기 조회수 증가
	@Update("UPDATE ${table_name} SET hit = hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(Map map);
	@Select("SELECT * FROM ${table_name} WHERE no=#{no}")
	public SeoulVO seoulDetailData(Map map);
}
