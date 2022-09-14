package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import com.sist.dao.FoodDAO;
import com.sist.service.*;
import com.sist.vo.*;
@Controller
public class FoodController {
	@Autowired
	private FoodService service;
	
	@Autowired
	private RecipeService rServ;
	
	@GetMapping("food/food_list.do")
	public String food_list(int cno, Model model) {
		List<FoodVO> list = service.foodListData(cno);
		CategoryVO vo = service.categoryInfoData(cno);
		
		model.addAttribute("list", list);
		model.addAttribute("cvo", vo);
		return "food/food_list";
	}
	
	@GetMapping("food/food_detail.do")
	public String food_detail(int fno, Model model) {
		FoodVO vo = service.foodDetailData(fno);
		model.addAttribute("vo", vo);
		
		// 레시피
		String ss = vo.getType();
		ss = ss.replace("/", "|");
		List<RecipeVO> rList = rServ.recipeFindData(ss);
		model.addAttribute("rList", rList);
		
		return "food/food_detail";
	}
	
	@RequestMapping("food/food_find.do")
	public String food_find(String page, String ss, Model model) {
		if(ss == null) {
			ss = "강남";
		}
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
		map.put("address", ss);
		List<FoodVO> list = service.foodFindData(map);
		
		int totalpage = service.foodLocationTotalPage(ss);
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("ss", ss);
		return "food/food_find";
	}
	
	// Vue 검색
	@GetMapping("food/food_find_vue.do")
	public String food_find_vue() {
		return "food/food_find_vue";
	}
	
	@GetMapping("food/food_detail_vue.do")
	public String food_detail_vue(int fno, Model model) {
		model.addAttribute("fno", fno);
		return "food/food_detail_vue";
	}
	
}
