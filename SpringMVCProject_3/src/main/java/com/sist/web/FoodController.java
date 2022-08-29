package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.dao.*;
/*
 *  모델 제작 (요청에 처리)
 *  
 *  사용자 요청 ================ DispatcherServlet =================== HandlerMapping
 *  									|									| Model 클래스 찾아줌 (요청처리=프로그래머)
 *  								ViewResolver (경로명, 확장자)
 *  								=========== View(JSP) : 화면 출력
 *  
 *  1. @Controller : Model 을 못찾거나 (404)일 경우 
 *  				Controller 유무 확인
 *  				return값 확인
 *  2. 요청처리에 필요한 모든 객체를 스프링으로부터 받아서 저장 =>  @Autowired
 *  3. 요청 URI를 받는다 => 에러 발생 시 405 출력
 *  	@RequestMapping
 *  	@GetMapping
 *  	@PostMapping
 *  4. 요청 처리 메소드 만든다
 *  	1) 리턴형 : (String), void
 *  	2) 매개변수 : 사용자가 보내준 모든 데이터형 처리 가능, VO, List, [] 단위로 받을 수 있음
 *  				=> 사용자가 보내준거 외에 내장 객체 요청해서 받을 수 있음
 *  					request, response, session, Model, RedirectAttributes
 *  	3) 매개변수로 등록된 모든 데이터는 DispatcherServlet 에서 값을 주입해준다
 *  	4) 순서는 상관 없음
 *  	5) 잘못된 데이터형 사용시 => 400(bad request) 출력
 *  =============================================================================================> Spring-boot에서도 계속 나옴
 *   
 */
@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	// 필요한 객체를 멤버로 생성해서 주소값 받는다
	// 스프링에서 생성된 객체 받아서 사용 => 자동으로 주소값 얻어옴 Autowired
	
	@GetMapping("food/list.do")
	public String food_list(String page, Model model) {
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		int rowSize = 12;
		int start = (rowSize * curpage) - (rowSize - 1);
		int end = rowSize * curpage;
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<FoodVO> list = dao.foodListData(map);
		int totalpage = dao.foodTotalPage();
		
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		model.addAttribute("list", list);
		
		return "list";
	}
	
	// detail.do?fno=${vo.fno }
	@GetMapping("food/detail.do")
	public String food_detail(int fno, Model model) {
		FoodVO vo = dao.foodDetailData(fno);
		
		model.addAttribute("vo", vo);
		return "detail";
	}
	
	//
	@GetMapping("food/find.do")
	public String food_find() {
		return "find";
	}
	
	
}
