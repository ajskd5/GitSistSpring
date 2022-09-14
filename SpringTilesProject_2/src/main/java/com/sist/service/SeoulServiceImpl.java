package com.sist.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sist.vo.*;
import com.sist.dao.*;

@Service
public class SeoulServiceImpl implements SeoulService{
	@Autowired
	private SeoulDAO dao;
	
	@Override
	public List<SeoulVO> seoulListData(Map map) {
		return dao.seoulListData(map);
	}

	@Override
	public SeoulVO seoulDetailData(Map map) {
		return dao.seoulDetailData(map);
	}

	@Override
	public int seoulTotalPage(Map map) {
		return dao.seoulTotalPage(map);
	}

	@Override
	public List<SeoulVO> seoulTop5(Map map) {
		return dao.seoulTop5(map);
	}

}
