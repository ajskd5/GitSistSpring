package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import com.sist.vo.*;
import com.sist.dao.*;

public interface FoodMappper {
	@Select("SELECT cno, title, subject, poster FROM food_category ORDER BY cno ASC")
	public List<CategoryVO> foodCategoryAllData();
	
	
	// 카테고리별 맛집 출력
		// 카테고리 정보
	@Select("SELECT cno, title, subject FROM food_category WHERE cno=#{cno}")
	public CategoryVO categoryInfo(int cno);
		// 카테고리별 맛집 리스트
	@Select("SELECT fno, name, tel, poster, address, type, score FROM food_house "
			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodCategoryListData(int cno);
	// 음식 종류 누르면 레시피 출력
	@Select("SELECT poster, rownum FROM recipe "
			+ "WHERE REGEXP_LIKE(title, #{type}) "
			+ "AND rownum<=12")
	public List<String> foodLikeRecipe(String type);
	
	// 상세보기 => Vue 지도 출력
	@Select("SELECT * FROM food_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	// 명소 / 자연 / 쇼핑
	
	
}
