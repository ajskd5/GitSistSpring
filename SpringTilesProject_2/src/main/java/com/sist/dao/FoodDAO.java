package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<CategoryVO> categoryListData(Map map){
		return mapper.categoryListData(map);
	}
	
	//카테고리 리스트
	public CategoryVO categoryInfoData(int cno) {
		return mapper.categoryInfoData(cno);
	}
	public List<FoodVO> foodListData(int cno){
		return mapper.foodListData(cno);
	}
	
	// 상세보기
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
	
}
