package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import com.sist.dao.BoardVO;

import lombok.Delegate;

/*
 *  1. XML
 *  2. Annotation
 *  3. XML + Anntation => 가장 많이 사용하는 형식
 *  		간단한 SQL : Annotation
 *  		복잡한 SQL : XML
 */

public interface BoardMapper {
	// 목록출력
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM spring_board ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListDate(Map map);
	
	// 총 페이지
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_board")
	public int boardTotalPage();
	
	// 상세보기
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit "
			+ "FROM spring_board WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	// 게시물 추가
	@SelectKey(keyProperty = "no", resultType = int.class, before = true, statement = "SELECT NVL(MAX(no)+1,1) as no FROM spring_board")
	@Insert("INSERT INTO spring_board VALUES(#{no}, #{name}, #{subject}, #{content}, #{pwd}, SYSDATE, 0)")
	public void boardInsertData(BoardVO vo);
	
	// 게시물 삭제
	@Delete("DELETE FROM spring_board WHERE no=#{no}")
	public void boardDeleteData(int no);
	
	// 게시물 수정
	@Update("UPDATE spring_board SET name=#{name}, subject=#{subject}, content=#{content} WHERE no=#{no}")
	public void boardUpdateData(BoardVO vo);
	
	// 조회수 증가
	@Update("UPDATE spring_board SET hit = hit+1 WHERE no=#{no}")
	public void hitIncrement(int no);
	
	// 비밀번호 검색
	@Select("SELECT pwd FROM spring_board WHERE no=#{no}")
	public String boardGetPassword(int no);
	
	// 동적쿼리
	// 아이디명 결과값, 매개변수  일치해야 함
	// <select id="boardFindData" resultType="BoardVO" parameterType="hashmap">
	public List<BoardVO> boardFindData(Map map);
	
	// 동적쿼리를 이렇게 만들 수 있음 / 근데 너무 불편 => XML 이용이 좋음
//	@Select("<script>" + 
//			"SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit\r\n" + 
//			"		FROM spring_board\r\n" + 
//			"		WHERE\r\n" + 
//			"		<trim prefix=\"(\" suffix=\")\" prefixOverrides=\"OR\"> <!-- 여기서 맨 앞에 OR 제거 -->\r\n" + 
//			"			<foreach collection=\"fsArr\" item=\"fd\">\r\n" + 
//			"				<trim prefix=\"OR\"><!-- WHERE 다음에 OR ~~ LIKE ~ 가 붙여지는데  -->\r\n" + 
//			"					<choose>\r\n" + 
//			"						<when test=\"fd == 'N'.toSTring()\">\r\n" + 
//			"							name LIKE '%'||#{ss}||'%'\r\n" + 
//			"						</when>\r\n" + 
//			"						<when test=\"fd == 'S'.toSTring()\">\r\n" + 
//			"							subject LIKE '%'||#{ss}||'%'\r\n" + 
//			"						</when>\r\n" + 
//			"						<when test=\"fd == 'C'.toSTring()\">\r\n" + 
//			"							content LIKE '%'||#{ss}||'%'\r\n" + 
//			"						</when>\r\n" + 
//			"					</choose>\r\n" + 
//			"				</trim>\r\n" + 
//			"			</foreach>\r\n" + 
//			"		</trim>"
//			+ "</script>")
	
}
