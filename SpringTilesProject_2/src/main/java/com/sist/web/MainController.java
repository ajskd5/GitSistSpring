package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.vo.*;
import com.sist.service.*;
@Controller
public class MainController {
	@Autowired
	private FoodService service;
	// 사용자 요청 => 클라이언트 서버가 받을 수 있는 곳 => 주소창
	/*
	@GetMapping("")
	@PutMapping("") => 수정 요청
	@PostMapping("")
	@DeleteMapping("") => 삭제 요청
	*/
	@GetMapping("main/main.do") // main/main.do => URI
	public String main_main(String no, Model model) {
		if(no == null) {
			no = "1";
		}
		int cno = Integer.parseInt(no);
		int start = 0, end = 0;
		if(cno == 1) {
			start = 1;
			end = 12;
		} else if(cno == 2) {
			start = 13;
			end = 18;
		} else if(cno == 3) {
			start = 19;
			end = 30;
		}
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<CategoryVO> list = service.categoryListData(map);
		
		model.addAttribute("list", list);
		return "main";
	}
}
