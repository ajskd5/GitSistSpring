package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.vo.*;
import com.sist.service.*;

@Controller
public class RecipeController {
	@Autowired
	private RecipeService service;
	
	@GetMapping("recipe/list.do")
	public String recipe_list(String page, Model model) {
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		int rowSize = 12;
		int start = (curpage*rowSize)-(rowSize-1);
		int end = curpage*rowSize;
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<RecipeVO> list = service.recipeListData(map);

		for(RecipeVO vo : list) {
			String s = vo.getTitle();
			if(s.length() > 23) {
				s = s.substring(0, 23) + "...";
				vo.setTitle(s);
			}
		}
		
		int totalpage = service.recipeTotalPage();
		
		// 블록
		final int BLOCK = 10;
		
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1; // 1, 11, 21, 31, 41, ...
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK; // 10, 20, 30, 40
		if(endPage>totalpage) {
			endPage = totalpage;
		}
		
		System.out.println(startPage);
		System.out.println(endPage);
		
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "recipe/list";
	}
}
