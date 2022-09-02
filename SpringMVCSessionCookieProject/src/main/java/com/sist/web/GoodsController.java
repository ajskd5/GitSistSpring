package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.*;

@Controller
public class GoodsController {
	@Autowired
	private GoodsDAO dao;
	
	/*
	 *  1. Cookie사용 (Spring)
	 */
	
	// 사용자 요청 => 처리
	
	/*
	 *  매개변수 사용
	 *  	1. 사용자 보낸 데이터형 (int, String, double, ...)
	 *  	2. Servlet 이 가지고 있는 내장 객체
	 *  		request, response, session, application, pageContext, ...
	 *  
	 *  		Cookie => request.getCookie()
	 *  
	 *  	3. 기타 : Error, RedirectAttributes
	 *  		JSP값을 전송
	 *  			Model ==================== forward (request)
	 *  			RedirectAttributes ======= sendRedirect()
	 */
	
	@GetMapping("goods/list.do")
	public String goods_list(String page, Model model, HttpServletRequest request) {
		if(page == null	) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		
		Map map = new HashMap();
		int rowSize = 12;
		int start = (rowSize*curpage) - (rowSize-1);
		int end = rowSize * curpage;
		map.put("start", start);
		map.put("end", end);
		
		List<GoodsVO> list = dao.goodsListData(map);
		for(GoodsVO vo : list) {
			String name = vo.getGoods_name();
			if(name.length() > 25) {
				name = name.substring(0, 25) + "...";
				vo.setGoods_name(name);
			}
			vo.setGoods_name(name);
		}
		int totalpage = dao.goodsTotalpage();
		
		// Cookie
		Cookie[] cookies = request.getCookies();
		List<GoodsVO> cList = new ArrayList<GoodsVO>();
		if(cookies != null) {
			for(int i=cookies.length-1; i>=0; i--) {
				if(cookies[i].getName().startsWith("goods")) {
					String no = cookies[i].getValue();
					// no에 해당하는 데이터 읽기
					GoodsVO vo = dao.goodsDetailData(Integer.parseInt(no));
					cList.add(vo);
				}
			}
		}
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("cList", cList);
		model.addAttribute("size", cList.size());
		return "goods/list";
	}
	
	// 쿠키 저장
	@GetMapping("goods/detail_before.do")
	public String goods_detail_before(int no, HttpServletResponse response) {
		Cookie cookie = new Cookie("goods"+no, String.valueOf(no)); // 클라이언트 브라우저에 저장 - 단점 : 문자열만 저장 가능
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		return "redirect:detail.do?no="+no;
	}
	
	// 상세보기
	@GetMapping("goods/detail.do")
	public String goods_detail(int no, Model model) {
		GoodsVO vo = dao.goodsDetailData(no);
		vo.setPrice(Integer.parseInt(vo.getGoods_price().replaceAll("[^0-9]", "").trim())); // 숫자를 제외한 나머지를 공백으로 바꿈
		model.addAttribute("vo", vo);
		return "goods/detail";
	}
	
	// 쿠키 삭제 (상품 하나)
	@GetMapping("goods/cookie_delete.do")
	public String goods_cookie_delete(int no, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("goods"+no)) {
				cookies[i].setPath("/");
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
				break;
			}
		}
		return "redirect:list.do";
	}
	
	// 전체 쿠키 삭제
	@GetMapping("goods/cookie_all_delete.do")
	public String goods_cookie_all_delete(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().startsWith("goods")) {
				cookies[i].setPath("/");
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
		return "redirect:list.do";
	}
	
	// session
	@GetMapping("goods/cart_list.do")
	public String good_cart_list(int no, HttpSession session, Model model) {
		List<CartVO> list = (List<CartVO>)session.getAttribute("cart");
		model.addAttribute("list", list);
		model.addAttribute("no", no);
		return "goods/cart_list";
	}
	
	@PostMapping("goods/session_insert.do")
	public String goods_session_insert(int no, int account, HttpSession session, Model model) {
		// 기존 세션에 저장된 값 list에 추가
		List<CartVO> list = (List<CartVO>)session.getAttribute("cart");
		
		// 세션에 저장된 값이 없을 때
		if(list == null) {
			list = new ArrayList<CartVO>();
		}

		GoodsVO vo = dao.goodsDetailData(no);
		CartVO cvo = new CartVO();
		cvo.setNo(no);
		cvo.setName(vo.getGoods_name());
		cvo.setPoster(vo.getGoods_poster());
		cvo.setPrice(vo.getGoods_price());
		cvo.setAccount(account);
		
		// 장바구니에 추가하려는 상품이 기존 장바구니 상품과 같을 경우 수량만 더하고 list에 추가하지 않음
		boolean bCheck = false;
		for(CartVO avo : list) {
			if(avo.getNo() == cvo.getNo()) {
				int acc = avo.getAccount() + cvo.getAccount();
				avo.setAccount(acc);
				bCheck = true;
				break;
			}
		}
		
		if(bCheck == false) {
			list.add(cvo);
			session.setAttribute("cart", list);
		}

		return "redirect:cart_list.do?no="+no;
	}
	
	// 장바구니 상품 삭제
	@GetMapping("goods/cart_cancel.do")
	public String goods_cart_cancel(int no, HttpSession session) {
		List<CartVO> list = (List<CartVO>)session.getAttribute("cart");
		for(int i=0; i<list.size(); i++) {
			CartVO vo = list.get(i);
			if(vo.getNo() == no) {
				list.remove(i);
				break;
			}
		}
		return "redirect:cart_list.do?no="+no;
	}
	
	// 장바구니 전체 삭제
	@GetMapping("goods/cart_total_delete.do")
	public String cart_total_delete(int no, HttpSession session) {
		// 이렇게 하면 세션 전체삭제라 세션에 저장된 로그인값도 사라짐
		//session.invalidate();
		
		session.removeAttribute("cart");
		return "redirect:cart_list.do?no="+no;
	}
}
