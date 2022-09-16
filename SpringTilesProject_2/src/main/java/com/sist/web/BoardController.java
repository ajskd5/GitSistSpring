package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	// 리스트
	@GetMapping("board/list.do")
	public String board_list(){
		return "board/list";
	}
	
	@GetMapping("board/insert.do")
	public String board_insert() {
		return "board/insert";
	}
	
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model) {
		model.addAttribute("no", no);
		return "board/detail";
	}
	
	// 수정
	@GetMapping("board/update.do")
	public String board_update(int no, Model model) {
		model.addAttribute("no", no);
		return "board/update";
	}
	
	//삭제
	@GetMapping("board/delete.do")
	public String board_delete(int no, Model model) {
		model.addAttribute("no", no);
		return "board/delete";
	}
}
