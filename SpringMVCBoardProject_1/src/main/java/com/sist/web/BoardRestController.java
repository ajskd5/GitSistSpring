package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.dao.BoardVO;
import com.sist.dao.*;

// JavaScript , 일반 데이터, JSON 으로 넘겨줌
// update.do => update_ok.do (X)  ==>  Boot
@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	/*  produces 한글 변환 방법
	 *  HTML (JavaScript) : text/html
	 *  JSON 			  : text/plain
	 */
	
	// 자바 스크립트 전송
	// 수정하기
	// 단점 => 크롬에서만 사용 가능
	@PostMapping(value = "board/update_ok.do", produces = "text/html;charset=UTF-8") // 한글 깨짐 방지
	public String board_update_ok(BoardVO vo) {
		String result = "";
		boolean bCheck = dao.boardUpdate(vo);
		if(bCheck == true) {
			result = "<script>"
					+ "location.href=\"detail.do?no=" + vo.getNo() + "\";"
					+ "</script>";
		} else {
			result = "<script>"
					+ "alert(\"비밀번호가 틀렸습니다!!\");" // 한글이 깨짐 => 방지 한글변환 해야함
					+ "history.back();"
					+ "</script>";
		}
		
		return result;
	}
	
	@PostMapping(value = "board/delete_ok.do", produces = "text/html;charset=UTF-8")
	public String board_delete_ok(BoardVO vo) {
		String result = "";
		boolean bCheck = dao.boardDelete(vo);
		if(bCheck == true) {
			result = "<script>"
					+ "location.href=\"list.do\";"
					+ "</script>";
		} else {
			result = "<script>"
					+ "alert(\"비밀번호가 틀렸습니다!!\");" // 한글이 깨짐 => 방지 한글변환 해야함
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
}
