package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;
import org.apache.ibatis.annotations.Select;

public interface RecipeMapper {
	// 페이지 나누기
	@Select("SELECT no, title, poster, chef, num "
			+ "FROM (SELECT no, title, poster, chef, rownum as num "
			+ "FROM (SELECT /*+INDEX_ASC(recipe, recipe_no_pk)*/no, title, poster, chef "
			+ "FROM recipe)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeListData(Map map);
	
	// 총 페이지
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe")
	public int recipeTotalPage();
	
	// 맛집 상세에서 음식종류로 title에 비교해서 찾음
		/*
		 * 기호
		 *  * : 0 이상
		 *  + : 1 이상
		 *  | : 선택(OR) A|B|C LIKE '%A%' OR LIKE '%B%' OR LIKE ...
		 *  ^ : 제외, 시작
		 *  	[^A-Z] => 알파벳 대문자 제외
		 *  	^[A-Z] => 알파벳으로 시작
		 *  & : 마지막 글자
		 *  . : 임의의 1글자
		 *  
		 *  [] : 범위
		 *  {} : 갯수 [0-9]{3} => 3자리 숫자
		 *  
		 */
	@Select("SELECT no, title, poster, rownum FROM recipe "
			+ "WHERE REGEXP_LIKE(title,#{ss}) "
			+ "AND rownum<=6")
	public List<RecipeVO> recipeFindData(String ss);
}
