package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;

@Controller
@RequestMapping("goods/") // 53page => 중복 경로명 제거
public class GoodsController {
	@Autowired
	private GoodsService service;
	
	@GetMapping("main.do")
	public String goods_main() {
		return "goods/main";
	}
	
	@GetMapping("goods_all.do")
	public String goods_all_list(String page, Model model) {
		// Model : 결과값 전송 객체
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		int totalpage = service.goodsAllTotalPage();
		
		Map map = new HashMap();
		map.put("start", (curpage*12)-11);
		map.put("end", (curpage*12));
		List<GoodsVO> list = service.goodsAllListData(map);
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage > totalpage) {
			endPage = totalpage;
		}
		
		// 전송
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		
		return "goods/goods_all";
	}
	
	
}
