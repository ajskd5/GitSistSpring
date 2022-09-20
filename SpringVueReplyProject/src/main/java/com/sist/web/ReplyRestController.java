package com.sist.web;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.vo.*;
import com.sist.dao.*;

@RestController // VueJS와 연동
public class ReplyRestController {
	@Autowired
	private ReplyDAO dao;
	
	public String reply_json_data(List<ReplyVO> list, String id) {
		JSONArray arr = new JSONArray();
		int k = 0;
		for(ReplyVO rvo : list) {
			JSONObject obj = new JSONObject();
			obj.put("no", rvo.getNo());
			obj.put("cno", rvo.getCno());
			obj.put("type", rvo.getType());
			obj.put("id", rvo.getId());
			obj.put("name", rvo.getName());
			obj.put("msg", rvo.getMsg());
			obj.put("dbday", rvo.getDbday());
			
			if(k == 0) {
				obj.put("sessionId", id);
			}
			k++;
			arr.add(obj);
		}
		
		return arr.toJSONString();
	}
	
	// 댓글 요청
	@GetMapping(value = "seoul/reply_list.do", produces = "text/plain;charset=UTF-8")
	public String reply_list(int cno, int type, HttpSession session) {
		String id = (String)session.getAttribute("id");
		String result = "";
		ReplyVO vo = new ReplyVO();
		vo.setCno(cno);
		vo.setType(type+3);
		
		List<ReplyVO> list = dao.replyListData(vo);
		result = reply_json_data(list, id);
		
		return result;
	}
	
	
	// 댓글 수정
	@PostMapping(value = "seoul/reply_update.do", produces = "text/html;charset=UTF8")
	public String reply_update(ReplyVO vo, HttpSession session) {
		String result = "<script>location.href=\"../seoul/detail.do?no=" + vo.getCno() + "&type=" + vo.getType() + "\";</script>";
		dao.replyUpdate(vo);
		return result;
	}
	
	// 댓글 삭제
	@GetMapping(value = "seoul/reply_delete.do", produces = "text/plain;charset=UTF-8")
	public String reply_delete(ReplyVO vo, HttpSession session) {
		String result = "";		
		String id = (String)session.getAttribute("id");
		
		dao.replyDelete(vo.getNo());
		vo.setType(vo.getType()+3);
		List<ReplyVO> list = dao.replyListData(vo);
		result = reply_json_data(list, id);

		return result;
	}
	
	// 댓글 쓰기
	@GetMapping(value = "seoul/reply_insert.do", produces = "text/plain;charset=UTF-8")
	public String reply_insert(ReplyVO vo, HttpSession session) {
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		
		vo.setId(id);
		vo.setName(name);
		vo.setType(vo.getType()+3);
		
		dao.replyInsert(vo);
		
		List<ReplyVO> list = dao.replyListData(vo);
		String result = reply_json_data(list, id);
		return result;
	}
}
