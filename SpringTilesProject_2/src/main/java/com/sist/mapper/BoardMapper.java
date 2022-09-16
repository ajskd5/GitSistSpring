package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

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
	
	// 상세보기 (조회수 증가)
	@Update("UPDATE spring_board SET hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	@Select("SELECT no, name, subject, content, hit, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday FROM spring_board WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	// 수정하기
		// 비밀번호 확인
	@Select("SELECT pwd FROM spring_board WHERE no=#{no}")
	public String boardGetPassword(int no);
		// 수정
	@Update("UPDATE spring_board SET "
			+ "name=#{name}, subject=#{subject}, content=#{content} "
			+ "WHERE no=#{no}")
	public void boardUpdateData(BoardVO vo);
	
	// 삭제
	@Delete("DELETE FROM spring_board WHERE no=#{no}")
	public void boardDeleteData(int no);
	
}
