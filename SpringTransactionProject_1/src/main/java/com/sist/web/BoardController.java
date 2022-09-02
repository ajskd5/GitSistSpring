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
		
		int count = dao.boardCount();
		count = count-((10*curpage)-10); // 페이지 넘기면 10개씩 출력하니까 다음페이지에서 10을 뺀 상태로 출력
		model.addAttribute("count", count);
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
	
	// 답변 페이지
	@GetMapping("board/reply.do")
	public String board_reply(int no, Model model) {
		model.addAttribute("no", no);
		return "board/reply";
	}
	
	// 답변 입력
	@PostMapping("board/reply_ok.do")
	public String board_reply_ok(int pno, BoardVO vo) {
		dao.boardReplyInsert(pno, vo);
		return "redirect:list.do";
	}
	
	// 수정페이지
	@GetMapping("board/update.do")
	public String board_update(int no, Model model) {
		BoardVO vo = dao.boardUpdateData(no);
		model.addAttribute("vo", vo);
		return "board/update";
	}
	
	// 수정하기
	@PostMapping("board/update_ok.do")
	public String board_update_ok(BoardVO vo, Model model) {
		boolean bCheck = dao.boardUpdate(vo);
		model.addAttribute("bCheck", bCheck);
		model.addAttribute("no", vo.getNo());
		return "board/update_ok";
	}
	
	// 삭제창
	@GetMapping("board/delete.do")
	public String board_delete(int no, Model model) {
		model.addAttribute("no", no);
		return "board/delete";
	}
	
	@PostMapping("board/delete_ok.do")
	public String board_delete_ok(int no, String pwd, Model model) {
		boolean bCheck = dao.boardDelete(no, pwd);
		model.addAttribute("bCheck", bCheck);
		return "board/delete_ok";
	}
}
