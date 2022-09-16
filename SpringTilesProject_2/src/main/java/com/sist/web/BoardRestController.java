package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping(value = "board/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_list_vue(String page) {
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		int rowSize = 10;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = rowSize*curpage;
		
		Map map = new HashedMap();
		map.put("start", start);
		map.put("end", end);
		List<BoardVO> list = dao.boardListData(map);
		int totalpage = dao.boardTotalPage();
		
		// JavaScript로 데이터 전송
		/*
		 *  List => Array ==> [] ==> JSONArray
		 *  VO	=> Object ==> {} ==> JSONObject
		 *  
		 *  [{}, {}, {}, ...]
		 */
		
		String result = "";
		try {
			JSONArray arr = new JSONArray(); // list
			int k = 0;
			for(BoardVO vo : list) {
				JSONObject obj = new JSONObject();
				obj.put("no", vo.getNo());
				obj.put("subject", vo.getSubject());
				obj.put("name", vo.getName());
				obj.put("dbday", vo.getDbday());
				obj.put("hit", vo.getHit());
				if(k == 0) {
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
				}
				arr.add(obj);
				k++;
			}
			result = arr.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 글쓰기
	@GetMapping(value = "board/insert_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_insert_vue(BoardVO vo) {
		dao.boardInsertData(vo);
		return "OK";
	}
}
