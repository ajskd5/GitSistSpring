package com.sist.mapper;
import java.util.*;
import org.apache.ibatis.annotations.Select;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

public interface FoodMapper {
	// 카테고리 출력
	@Select("SELECT cno, title, poster, subject FROM food_category "
			+ "WHERE cno BETWEEN #{start} AND #{end}")
	public List<CategoryVO> categoryListData(Map map);
	
	// 카테고리에서 리스트 출력
	@Select("SELECT fno, name, poster, address, tel, type, score FROM food_house "
			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
	// 카테고리 설명
	@Select("SELECT cno, title, subject FROM food_category "
			+ "WHERE cno=#{cno}")
	public CategoryVO categoryInfoData(int cno);
	
	// 맛집 상세보기
	@Select("SELECT fno, name, poster, address, score, tel, type, time, parking, menu, price "
			+ "FROM food_house WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	
	// 상세보기 => 명소, 쇼핑, 자연, 레시피
	
}