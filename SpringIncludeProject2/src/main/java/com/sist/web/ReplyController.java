package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.dao.*;
import java.util.*;

import javax.servlet.http.HttpSession;

@Controller
public class ReplyController {
	// GoodsController rdao랑 주소값 동일 (싱글톤)
	@Autowired
	private ReplyDAO dao;
	
	@PostMapping("reply/reply_insert.do")
	public String reply_insert(ReplyVO vo, HttpSession session) {
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		vo.setId(id);
		vo.setName(name);
		dao.replyInsert(vo);
		String uri = "";
		if(vo.getType() == 1) {
			uri = "../goods/all_detail.do?no=" + vo.getBno();
		} else if(vo.getType() == 2) {
			uri = "../goods/new_detail.do?no=" + vo.getBno(); 
		} else if(vo.getType() == 3) {
			uri = "../goods/special_detail.do?no=" + vo.getBno();
		} else if(vo.getType() == 4) {
			uri = "../goods/best_detail.do?no=" + vo.getBno();
		}
		return "redirect:" + uri;
	}
	
	// 댓글 수정
	@PostMapping("reply/reply_update.do")
	public String reply_update(ReplyVO vo) {
		// 수정
		dao.replyUpdate(vo);
		// 화면 이동
		String uri = "";
		if(vo.getType() == 1) {
			uri = "../goods/all_detail.do?no=" + vo.getBno();
		} else if(vo.getType() == 2) {
			uri = "../goods/new_detail.do?no=" + vo.getBno(); 
		} else if(vo.getType() == 3) {
			uri = "../goods/special_detail.do?no=" + vo.getBno();
		} else if(vo.getType() == 4) {
			uri = "../goods/best_detail.do?no=" + vo.getBno();
		}
		return "redirect:" + uri;
	}
	
	//대댓글 추가
	@PostMapping("reply/reply_reply_insert.do")
	public String reply_reply_insert(int pno, ReplyVO vo, HttpSession session) {
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		vo.setId(id);
		vo.setName(name);
		dao.replyReplyInsert(pno, vo);
		
		// 화면이동
		String uri = "";
		if(vo.getType() == 1) {
			uri = "../goods/all_detail.do?no=" + vo.getBno();
		} else if(vo.getType() == 2) {
			uri = "../goods/new_detail.do?no=" + vo.getBno(); 
		} else if(vo.getType() == 3) {
			uri = "../goods/special_detail.do?no=" + vo.getBno();
		} else if(vo.getType() == 4) {
			uri = "../goods/best_detail.do?no=" + vo.getBno();
		}
		return "redirect:" + uri;
	}
	
	//댓글 삭제
	//reply/reply_delete.do?no=${rvo.no }&bno=${rvo.bno}&type=${rvo.type}
	@GetMapping("reply/reply_delete.do")
	public String reply_delete(ReplyVO vo) {
		// DAO
		dao.replyDelete(vo.getNo());
		
		// 화면이동
		String uri = "";
		if(vo.getType() == 1) {
			uri = "../goods/all_detail.do?no=" + vo.getBno();
		} else if(vo.getType() == 2) {
			uri = "../goods/new_detail.do?no=" + vo.getBno(); 
		} else if(vo.getType() == 3) {
			uri = "../goods/special_detail.do?no=" + vo.getBno();
		} else if(vo.getType() == 4) {
			uri = "../goods/best_detail.do?no=" + vo.getBno();
		}
		return "redirect:" + uri;
	}
}
