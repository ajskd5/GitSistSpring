package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class MemberController {
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder encoder; // 암호화 클래스(bean태그로 메모리할당 함)
	
	@GetMapping("member/join.do")
	public String member_join(Model model) {
		model.addAttribute("main_jsp", "../member/join.jsp");
		return "main/main";
	}
	
	//회원가입
	@PostMapping("member/join_ok.do")
	public String member_join_ok(MemberVO vo) {
		vo.setTel(vo.getTel1() + "-" + vo.getTel2());
		String en = encoder.encode(vo.getPwd());
		vo.setPwd(en);
		dao.memberJoinInsert(vo);
		return "redirect:../main/main.do";
	}
	
	// 아이디 중복체크
	@PostMapping("member/idcheck.do")
	@ResponseBody // body태그 안에 값을 넣어줌 => 일반 데이터, JSON 전송 ===> @RestController
	public String member_idcheck(String id, Model model) {
		String result = "";
		int count = dao.memberIdCheck(id);
		if(count == 0) {
			result = "YES";
		} else {
			result = "NO";
		}
		return result;
	}
	
	// 로그인 창
	@GetMapping("member/login.do")
	public String member_login(Model model) {
		model.addAttribute("main_jsp", "../member/login.jsp");
		return "main/main";
	}
	
	//로그인
	@PostMapping("member/login_ok.do")
	@ResponseBody
	public String member_login_ok(String id, String pwd, boolean ck, HttpSession session, HttpServletResponse response) {
		String result = "";
		int count = dao.memberIdCheck(id);
		if(count == 0) {
			result = "NOID";
		} else {
			MemberVO vo = dao.memberJoinInfoData(id);
			if(encoder.matches(pwd, vo.getPwd())) {
				session.setAttribute("id", id);
				session.setAttribute("name", vo.getName());
				session.setAttribute("role", vo.getRole());
				
				// 쿠키
				if(ck == true) {
					Cookie cookie = new Cookie("id", id);
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
					
					cookie = new Cookie("name", vo.getName());
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
					
					cookie = new Cookie("role", vo.getRole());
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
				}
				result = "OK";
				
			} else {
				result = "NOPWD";
			}
		}
		
		return result;
	}
	
	// 로그아웃
	@GetMapping("member/logout.do")
	public String member_logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:../main/main.do";
	}
}
