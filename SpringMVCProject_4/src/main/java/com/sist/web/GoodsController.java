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
		
		for(GoodsVO vo : list) {
			String name = vo.getName();
			if(name.length() > 15) {
				name = name.substring(0, 15) + "...";
				vo.setName(name);
			}
		}
		
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
		// model => request
		/*
		 *  	public void addAttribute(String key, Object obj){
		 *  		request.setAttribute(key, obj);
		 *  	}
		 *  request를 사용하지 않도록 권장
		 *  
		 *  Model = 전송 객체
		 */
		return "goods/goods_all";
	}
	
	// goods_all_detail.do?no=${vo.no }
	@GetMapping("goods_all_detail.do")
	public String goods_all_detail(int no, Model model) {
		GoodsVO vo = service.goodsAllDetailData(no);
		model.addAttribute("vo", vo);
		return "goods/goods_all_detail";
	}
	
	// 베스트 상품
	@RequestMapping("goods_best.do")
	public String goods_best(String page, Model model) {
		if(page == null) {
			page = "1";
		}
		
		int curpage = Integer.parseInt(page);
		int totalpage = service.goodsBestTotalPage();
		
		Map map = new HashMap();
		map.put("start", (curpage*12)-11);
		map.put("end", (curpage*12));
		List<GoodsVO> list = service.goodsBestListData(map);
		
		for(GoodsVO vo : list) {
			String name = vo.getName();
			if(name.length() > 15) {
				name = name.substring(0, 15) + "...";
				vo.setName(name);
			}
		}

		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		
		return "goods/goods_best";
	}
	
	@RequestMapping("goods_best_detail.do")
	public String goods_best_detail(int no, Model model) {
		GoodsVO vo = service.goodsBestDetailData(no);
		model.addAttribute("vo", vo);
		return "goods/goods_best_detail";
	}
	
}
