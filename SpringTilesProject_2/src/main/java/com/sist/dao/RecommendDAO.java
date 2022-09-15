package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class RecommendDAO {
	@Autowired
	private RecommendMapper mapper;
	
	public List<String> recommendNameData(){
		return mapper.recommendNameData();
	}
	
	// 같은 이름이 여러개면 mapper에서는 list로 받고 여기서 하나만 return 
	public FoodVO recommendDetailData(String name){
		return mapper.recommendDetailData(name).get(0);
	}
}
