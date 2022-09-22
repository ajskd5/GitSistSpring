package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMappper mapper;
	
//	@Select("SELECT cno, title, subject, poster FROM food_category")
	public List<CategoryVO> foodCategoryAllData(){
		return mapper.foodCategoryAllData();
	}
	
//	@Select("SELECT cno, title, subject FROM food_category WHERE cno=#{cno}")
	public CategoryVO categoryInfo(int cno){
		return mapper.categoryInfo(cno);
	}
	
//	@Select("SELECT cno, name, tel, poster, type FROM food_house "
//			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodCategoryListData(int cno) {
		return mapper.foodCategoryListData(cno);
	}
	// 관련 레시피
//	@Select("SELECT poster, rownum FROM recipe "
//			+ "WHERE REGEXP_LIKE(title, #{type}) "
//			+ "AND rownum<=12")
	public List<String> foodLikeRecipe(String type){
		return mapper.foodLikeRecipe(type);
	}
	
	// 상세보기
//	@Select("SELECT * FROM food_house "
//			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
}
