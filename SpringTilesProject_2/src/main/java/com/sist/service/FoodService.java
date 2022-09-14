package com.sist.service;
import java.util.*;
import com.sist.vo.*;
public interface FoodService {
	public List<CategoryVO> categoryListData(Map map);
	
	// 카테고리 리스트
	public CategoryVO categoryInfoData(int cno);
	public List<FoodVO> foodListData(int cno);
	
	// 상세보기
	public FoodVO foodDetailData(int fno);
	
	// 검색
	public List<FoodVO> foodFindData(Map map);
	// 검색 총 페이지
	public int foodLocationTotalPage(String address);
	
	// Vue 상세보기
	public FoodVO foodDetailVueData(int fno);
}
