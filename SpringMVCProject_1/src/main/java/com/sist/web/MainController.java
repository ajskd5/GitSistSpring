package com.sist.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 모델인지 알려줌
@Controller
@RequestMapping("main/") // 이렇게 하면 밑에서 경로명 중복 제거 가능
public class MainController {
	@RequestMapping("input.do")
	public String main_input(HttpServletRequest request, HttpServletResponse response) {
		return "main/input";
	}
	@RequestMapping("output.do")
	public String main_output(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String loc = request.getParameter("loc");
		String content = request.getParameter("content");
		String[] hobby = request.getParameterValues("hobby");
		
		request.setAttribute("name", name);
		request.setAttribute("sex", sex);
		request.setAttribute("loc", loc);
		request.setAttribute("content", content);
		request.setAttribute("hobby", hobby);
		return "main/output"; // .jsp 생략
	}
	
	@RequestMapping("output1.do")
	public String main_output1(String name, String sex, String loc, String content, String[] hobby, Model model) {
		// Model => 데이터 전송 객체 (request 첨부되어 있음)
		// request를 사용하지 않고 매개변수를 이용해서 DispatcherServlet으로부터 데이터를 받을 수 있음
		// 가급적 request, response 사용하지 않음 => 매개변수를 이용해서 데이터값 받음
		/* 		한글 변환은 web.xml에서 설정함
		 *  request 사용 시기 => Cookie
		 *  response 사용 시기 => 파일 업로드, Cookie전송
		 */
		
		model.addAttribute("name", name); // request.setAttribute("name", name);
		/*
		 *  public void addAttribute(String key, Object obj	{
		 *  	request.setattribute(key, obj);
		 *  }
		 */
		model.addAttribute("sex", sex);
		model.addAttribute("loc", loc);
		model.addAttribute("content", content);
		model.addAttribute("hobby", hobby);
		return "main/output";
	}
	
	// 주의점 => 받는 이름과 vo변수 이름이 같아야 함
	// vo 단위로 받는걸 (커맨드 객체)라고 함
	@RequestMapping("output2.do")
	public String main_output2(MemberVO vo, Model model) {
		model.addAttribute("vo", vo);
		
		return "main/output1"; // output에서 ${name}으로 받음 이걸로 받으려면 ${vo.name}으로 받아야함
	}
	
	/*
	 *  Spring 4.3
	 *  ========== 구분
	 *  @RequestMapping => GET/POST 동시에 처리 (검색, 페이징)
	 *  	= @GetMapping => GET
	 *  	= @PostMapping => POST
	 */
}
