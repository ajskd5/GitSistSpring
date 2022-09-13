package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.SeoulVO;

@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	
	// 리스트
	public List<SeoulVO> seoulListData(Map map){
		return mapper.seoulListData(map);
	}
	// 총페이지
	public int seoulTotalPage(Map map) {
		return mapper.seoulTotalPage(map);
	}
	
	// 상세보기
	public SeoulVO seoulDetailData(Map map) {
		mapper.hitIncrement(map);
		return mapper.seoulDetailData(map);
	}
	
	// TOP 5
	public List<SeoulVO> seoulTop5(Map map){
		return mapper.seoulTop5(map);
	}
}
