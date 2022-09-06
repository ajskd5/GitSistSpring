package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;

@Repository
public class DataBoardDAO {
	// 필요한 객체 주소 설정 (스프링에서 관리)
	@Autowired
	private DataBoardMapper mapper;
	
	// 리스트
	public List<DataBoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	// 총페이지
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	
	// 글쓰기 (데이터 추가)
	public void boardInsert(DataBoardVO vo) {
		mapper.boardInsert(vo);
	}
	
	// 상세보기
	public DataBoardVO databoardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}
	
	// 수정하기 페이지
	public DataBoardVO databoardUpdateData(int no) {
		return mapper.databoardDetailData(no);
	}
	
	// 수정하기 (비밀번호 확인 => RestController)
	public boolean databoardUpdate(DataBoardVO vo) {
		boolean bCheck = false;
		String db_pwd = mapper.databoardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck = true;
			mapper.databoardUpdate(vo);
		}
		
		return bCheck;
	}
	
	// 삭제하기 (비밀번호 확인 => RestController)
	public boolean databoardDelete(int no, String pwd) {
		boolean bCheck = false;
		String db_pwd = mapper.databoardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck = true;
			mapper.databoardDelete(no);
		}
		return bCheck;
	}
	
	public DataBoardVO databoardInfoData(int no) {
		return mapper.databoardInfoData(no);
	}
}
