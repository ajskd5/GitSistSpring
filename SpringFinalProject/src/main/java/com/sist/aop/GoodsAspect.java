package com.sist.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.GoodsDAO;
import com.sist.manager.*;
import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class GoodsAspect {
	@Autowired
	private NewsManager mgr;
	
	@Autowired
	private GoodsDAO dao;
	
	@Before("execution(* com.sist.web.*Controller.*(..))") // 리턴형(모든 리턴형) / 어떤 파일인지(controller로 끝나는 파일).모든메소드(매개변수) => ..은 매개변수 0개 이상
	public void footer() {
		try {
			// 현재 사용중인 request 가져옴
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			
			String json = mgr.newsFind("신상품");
			JSONParser jp = new JSONParser();
			JSONObject obj = (JSONObject)jp.parse(json);
			JSONArray arr = (JSONArray)obj.get("items");
			List<NewsVO> nList = new ArrayList<NewsVO>(); 
			for(int i=0; i<5; i++) {
				NewsVO vo = new NewsVO();
				JSONObject o = (JSONObject)arr.get(i);
				vo.setTitle((String)o.get("title"));
				vo.setLink((String)o.get("link"));
				nList.add(vo);
			}
			request.setAttribute("newList", nList);
			
			List<GoodsVO> gList = dao.goodsFooterData();
			for(GoodsVO vo : gList) {
				String name = vo.getGoods_name();
				if(name.length() > 13) {
					name = name.substring(0, 13) + "...";
					vo.setGoods_name(name);
				}
			}
			request.setAttribute("gList", gList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
