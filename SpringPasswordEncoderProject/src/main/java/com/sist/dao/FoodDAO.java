package com.sist.dao;

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
}
