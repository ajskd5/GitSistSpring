package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*
 *  1. @Controller
 *  2. @RestController
 *  3. @Repository
 *  4. @Service
 *  5. @Conponent
 *  6. @ControllerAdvice : 통합 예외처리
 *  
 *  DI
 *  @Autowired
 *  @Inject
 */
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

@Service
public class FoodServiceImpl implements FoodService{
	@Autowired
	private FoodDAO dao;
	
	@Override
	public List<CategoryVO> categoryListData(Map map) {
		return dao.categoryListData(map);
	}

	// 카테고리 리스트
	@Override
	public CategoryVO categoryInfoData(int cno) {
		return dao.categoryInfoData(cno);
	}
	@Override
	public List<FoodVO> foodListData(int cno) {
		return dao.foodListData(cno);
	}

	// 상세보기
	@Override
	public FoodVO foodDetailData(int fno) {
		return dao.foodDetailData(fno);
	}

	// 검색
	@Override
	public List<FoodVO> foodFindData(Map map) {
		return dao.foodFindData(map);
	}
	// 검색 총페이지
	@Override
	public int foodLocationTotalPage(String address) {
		return dao.foodLocationTotalPage(address);
	}

	// Vue 상세보기
	@Override
	public FoodVO foodDetailVueData(int fno) {
		return dao.foodDetailVueData(fno);
	}
	
	
	
}
