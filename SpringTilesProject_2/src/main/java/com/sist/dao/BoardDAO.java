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
	
	// 상세보기
	public BoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
	// 수정창
	public BoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	
	// 수정하기
	public String boardUpdateData(BoardVO vo) {
		String result = "no";
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			result = "yes";
			mapper.boardUpdateData(vo);
		}
		return result;
	}
	
	// 삭제하기
	public String boardDeleteData(int no, String pwd) {
		String result = "no";
		String db_pwd = mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			result = "yes";
			mapper.boardDeleteData(no);
		}
		return result;
	}
}
