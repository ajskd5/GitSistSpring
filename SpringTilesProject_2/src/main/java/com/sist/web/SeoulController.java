package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

import com.sist.dao.ReplyDAO;
import com.sist.service.*;
import com.sist.vo.ReplyVO;
import com.sist.vo.SeoulVO;

@Controller
public class SeoulController {
	@Autowired
	private SeoulService service;
	
	@Autowired
	private ReplyDAO dao;
	
	private String[] tables = {"", "seoul_location", "seoul_nature", "seoul_shop"};
	
	@GetMapping("seoul/list.do")
	public String seoul_location(String page, int tab, Model model) {
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		
		int rowSize = 12;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = rowSize*curpage;
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("table_name", tables[tab]);
		
		List<SeoulVO> list = service.seoulListData(map);
		int totalpage = service.seoulTotalPage(map);
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		
		return "seoul/"+tables[tab];
	}
	
	// 상세보기
	@GetMapping("seoul/detail.do")
	public String seoul_detail(int tab, int no, Model model) {
		Map map = new HashMap();
		map.put("table_name", tables[tab]);
		map.put("no", no);
		SeoulVO vo = service.seoulDetailData(map);
		model.addAttribute("vo", vo);
		model.addAttribute("tab", tab); // tab을 보내야 3개가 따로 작동
		
		ReplyVO rvo = new ReplyVO();
		rvo.setCno(no);
		rvo.setType(tab);
		List<ReplyVO> list = dao.replyListData(rvo);
		model.addAttribute("list", list);
		
		return "seoul/detail";
	}
	
}
