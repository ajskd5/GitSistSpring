package com.sist.dao;

import java.util.*;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.BoardMapper;

@Repository
public class BoardDAO {
	// 인터페이스를 구현한 클래스의 주소값 스프링에 요청
	@Autowired
	private BoardMapper mapper;

	// 리스트
	public List<BoardVO> boardListDate(Map map){
		return mapper.boardListDate(map);
	}
	
	// 총 페이지
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	
	// 게시물 추가 return 없음
	public void boardInsertData(BoardVO vo) {
		mapper.boardInsertData(vo);
	}
	
	// 상세보기 조회수 증가
	public BoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
	// 수정하기 폼
	public BoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	
	// 수정하기
	//public void boardUpdateData(BoardVO vo);
	//public String boardGetPassword(int no);
	public boolean boardUpdate(BoardVO vo) {
		boolean bCheck = false;
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck = true;
			mapper.boardUpdateData(vo);
		} else {
			bCheck = false;
		}
		return bCheck;
	}
	
	// 삭제 폼
	public BoardVO boardDeleteData(int no) {
		return mapper.boardDetailData(no);
	}
	
	// 삭제
	public boolean boardDelete(BoardVO vo) {
		boolean bCheck = false;
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck = true;
			mapper.boardDeleteData(vo.getNo());
		} else {
			bCheck = false;
		}
		return bCheck;
	}
	
	// 검색
	public List<BoardVO> boardFindData(Map map){
		return mapper.boardFindData(map);
	}
}
