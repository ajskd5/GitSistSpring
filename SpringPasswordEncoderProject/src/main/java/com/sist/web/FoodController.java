package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping("food/food_list.do")
	public String food_list(int cno, Model model) {
		List<FoodVO> list = dao.foodCategoryListData(cno);
		for(FoodVO vo : list) {
			String addr = vo.getAddress();
			addr = addr.substring(0, addr.lastIndexOf("지"));
			vo.setAddress(addr);
			
			String poster = vo.getPoster();
			poster = poster.substring(0, poster.indexOf("^"));
			vo.setPoster(poster);
		}
		
		CategoryVO vo = dao.categoryInfo(cno);
		
		model.addAttribute("cvo", vo);
		model.addAttribute("list", list);
		model.addAttribute("main_jsp", "../food/food_list.jsp");
		return "main/main";
	}
	
	@GetMapping("food/food_detail.do")
	public String food_detail(int fno, Model model) {
		model.addAttribute("fno", fno);
		model.addAttribute("main_jsp", "../food/food_detail.jsp");
		return "main/main";
	}
	
}
