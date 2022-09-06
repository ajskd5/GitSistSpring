package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.dao.*;

@Controller
public class MainController {
	@Autowired
	private GoodsDAO dao;
	
	// 요청 종류 => 요구사항 분석 => 구분
	@GetMapping("main/main.do")
	public String main_main(String page, Model model) {
		CommonsController.commonsData("goods_all", page, model, dao);
		// 메인에서 화면 변경
		model.addAttribute("main_jsp", "../goods/home.jsp");
		return "main/main";
	}
	
	
}
