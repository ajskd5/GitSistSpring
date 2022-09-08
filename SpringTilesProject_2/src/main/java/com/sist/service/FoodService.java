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
}
