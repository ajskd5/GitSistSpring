package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
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
		vo.setTel1(vo.getTel1() + "-" + vo.getTel2());
		String en = encoder.encode(vo.getPwd());
		vo.setPwd(en);
		dao.memberJoinInsert(vo);
		return "redirect:../main/main.do";
	}
	
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
}
