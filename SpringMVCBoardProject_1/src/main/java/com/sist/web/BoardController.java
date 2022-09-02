package com.sist.web;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.dao.*;

//request 에 값을 담아서 JSP로 전송
@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	// 목록
	@GetMapping("board/list.do")
	public String board_list(String page, Model model) {
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		int totalpage = dao.boardTotalPage();
		
		Map map = new HashMap();
		map.put("start", (curpage*10)-9);
		map.put("end", (curpage*10));
		
		List<BoardVO> list = dao.boardListDate(map);
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		
		return "board/list";
	}
	
	// 추가 -> 입력 폼 보여주기
	@GetMapping("board/insert.do")
	public String board_insert() {
		return "board/insert";
	}
	
	// @RequestMapping => GET/POST 
	// 사용자가 전송하는 모든 데이터는 DispatcherServlet => 매개변수를 이용해서 데이터 주입
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		dao.boardInsertData(vo);
		return "redirect:list.do";
	}
	
	// detail.do?no=${vo.no}
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model) {
		BoardVO vo = dao.boardDetailData(no);
		model.addAttribute("vo", vo);
		return "board/detail";
	}
	
	// 수정하기 -> 수정하기 폼
	@GetMapping("board/update.do")
	public String board_update(int no, Model model) {
		BoardVO vo = dao.boardUpdateData(no);
		model.addAttribute("vo", vo);
		return "board/update";
	}
	
	
	// 검색 	체크박스 : 배열 	검색어 : 문자열
	@PostMapping("board/find.do")
	public String board_find(String[] fd, String ss, Model model) {
		Map map = new HashMap();
		map.put("fsArr", fd);
		map.put("ss", ss);
		List<BoardVO> list = dao.boardFindData(map);
		model.addAttribute("list", list);
		return "board/find";
	}
	
	// 삭제하기 에서 취소
	@GetMapping("board/delete.do")
	public String board_delete(int no, Model model) {
		return "board/delete";
	}
	

}
