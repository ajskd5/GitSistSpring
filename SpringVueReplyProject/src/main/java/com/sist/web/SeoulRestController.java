package com.sist.web;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 자바스크립트로 값 전송
@RestController
public class SeoulRestController {
	@Autowired
	private SeoulDAO sDao;
	
	@GetMapping(value = "seoul/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String seoul_list_vue(String page, String type, Model model) {
		if(page == null) {
			page = "1";
		}
		if(type == null) {
			type = "1";
		}
		int index = Integer.parseInt(type);
		String[] table_name = {"", "seoul_location", "seoul_nature", "seoul_shop"};
		
		int curpage = Integer.parseInt(page);
		int rowSize = 12;
		int start = (curpage*rowSize)-(rowSize-1);
		int end = rowSize*curpage;
		Map map = new HashMap();
		map.put("table_name", table_name[index]);
		map.put("start", start);
		map.put("end", end);
		List<SeoulVO> list = sDao.seoulListData(map);
		
		int totalpage = sDao.seoulTotalPage(map);
		
		String result = "";
		JSONArray arr = new JSONArray();
		int k = 0;
		for(SeoulVO vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("no", vo.getNo());
			obj.put("title", vo.getTitle());
			obj.put("poster", vo.getPoster());
			obj.put("msg", vo.getMsg());
			obj.put("address", vo.getAddress());
			obj.put("hit", vo.getHit());
			if(k==0) {
				obj.put("curpage", curpage);
				obj.put("totalpage", totalpage);
				obj.put("type", type);
			}
			arr.add(obj);
			k++;
		}
		
		result = arr.toJSONString();
		return result;
	}
}
