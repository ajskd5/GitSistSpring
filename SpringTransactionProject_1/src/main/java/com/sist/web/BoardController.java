package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import com.sist.dao.*;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("board/list.do")
	public String board_list(String page, Model model) {
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		List<BoardVO> list = dao.boardListData(curpage);
		int totalpage = dao.boardTotalPage();
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		return "board/list";
	}
	
	// 글쓰기 창만 띄워줌
	@GetMapping("board/insert.do")
	public String board_insert() {
		return "board/insert";
	}
	
	// 글쓰기
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		dao.boardInsert(vo);
		return "redirect:list.do"; // 재전송 (request를 전송하지 않음)
	}
	
	// 상세보기
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model) {
		BoardVO vo =dao.boardDetail(no);
		model.addAttribute("vo", vo);
		return "board/detail";
	}
	
	// 답변
	@GetMapping("board/reply.do")
	public String board_reply(int no, Model model) {
		model.addAttribute("no", no);
		return "board/reply";
	}
}
