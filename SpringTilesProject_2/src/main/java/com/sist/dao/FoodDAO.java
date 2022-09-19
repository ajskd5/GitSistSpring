package com.sist.dao;

import org.apache.ibatis.annotations.Select;
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
	
	// 검색
	public List<FoodVO> foodFindData(Map map){
		return mapper.foodFindData(map);
	}
	// 검색 총 페이지
	public int foodLocationTotalPage(String address) {
		return mapper.foodLocationTotalPage(address);
	}
	
	// Vue 상세보기
	public FoodVO foodDetailVueData(int fno) {
		return mapper.foodDetailVueData(fno);
	}
	
	
	// list
	public List<FoodVO> foodAllData(Map map){
		return mapper.foodAllData(map);
	}
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
	
}
