package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	// 리스트
	public List<BoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	// 총 페이지
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	
	// 글쓰기
	public void boardInsertData(BoardVO vo) {
		mapper.boardInsertData(vo);
	}
}
