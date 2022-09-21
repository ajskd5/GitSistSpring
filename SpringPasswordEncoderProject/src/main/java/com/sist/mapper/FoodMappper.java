package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import com.sist.vo.*;
import com.sist.dao.*;

public interface FoodMappper {
	@Select("SELECT cno, title, subject, poster FROM food_category ORDER BY cno ASC")
	public List<CategoryVO> foodCategoryAllData();
	
	
}
