package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.manager.*;
import com.sist.vo.*;
import java.util.*;

@Controller
public class NewsController {
	@Autowired
	private NewsManager mgr;
	
	@RequestMapping("news/find.do")
	public String news_find(String ss, Model model) {
		if(ss == null) {
			ss = "상품";
		}
		String json = mgr.newsFind(ss);
		try {
			JSONParser jp = new JSONParser();
			JSONObject root = (JSONObject)jp.parse(json); // 결과값이 {} => JSONObject
			JSONArray arr = (JSONArray)root.get("items"); // items는 [] => JSONArray
			List<NewsVO> list = new ArrayList<NewsVO>();
			
			for(int i=0; i<arr.size(); i++) {
				NewsVO vo = new NewsVO();
				JSONObject obj = (JSONObject)arr.get(i); // items에 값 = {} (title, link, description, ...)
				vo.setTitle((String)obj.get("title"));
				vo.setLink((String)obj.get("link"));
				vo.setDescription((String)obj.get("description"));
				list.add(vo);
			}
			
			model.addAttribute("list", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("main_jsp", "../news/find.jsp");
		return "main/main";
	}
}
