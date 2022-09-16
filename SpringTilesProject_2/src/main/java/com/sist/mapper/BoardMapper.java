package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.sist.vo.*;

public interface BoardMapper {
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM spring_board ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_board")
	public int boardTotalPage();
	
	@SelectKey(keyProperty = "no",  resultType = int.class, before = true, statement = "SELECT NVL(MAX(no)+1, 1) as no FROM spring_board")
	@Insert("INSERT INTO spring_board VALUES(#{no}, #{name}, #{subject}, #{content}, #{pwd}, SYSDATE, 0)")
	public void boardInsertData(BoardVO vo);
}
