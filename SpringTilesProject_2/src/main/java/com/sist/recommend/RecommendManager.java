package com.sist.recommend;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RecommendManager {
	
	// json 파싱
	/*
	 * "items":[
	 * { 			
			"title":"여의도<b>가볼<\/b>만한곳 더현대<b>서울맛집<\/b> 본보야지 인생샷 건지는... ",
			"link":"https:\/\/blog.naver.com\/kongmistar?Redirect=Log&logNo=222697471218",
			"description":"더현대<b>서울맛집<\/b> 이탈리안레스토랑 본보야지를 소개합니다 본보야지 본보야지는 이전 보나베띠 ifc... 
			윤중로에서 여의도데이트를 즐겼답니다 여의도 가면 와인<b>맛집<\/b> 파스타 <b>맛집<\/b> 본보야지 <b>추천<\/b>드립니다",
			"bloggername":"행복한 여행 동반자 콩미",
			"bloggerlink":"https:\/\/blog.naver.com\/kongmistar",
			"postdate":"20220411"
		}
	 */
	public List<String> jsonParser(String json){
		List<String> list = new ArrayList<String>();
		// description 에 들어있는 것만 파싱
		try {
			JSONParser jp = new JSONParser();
			JSONObject root = (JSONObject)jp.parse(json);
			JSONArray arr = (JSONArray)root.get("items");
			for(int i=0; i<arr.size(); i++) {
				JSONObject obj = (JSONObject)arr.get(i);
				String desc = (String)obj.get("description");
				list.add(desc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
//	public static void main(String[] args) {
//		Scanner scan = new Scanner(System.in);
//		System.out.print("계절 입력(봄, 여름, 가을, 겨울) : ");
//		String fd = scan.next();
//		String json = NaverDataClass.recommendData(fd);
//		
//		List<String> jList = jsonParser(json);
////		for(String s : jList) {
////			System.out.println(s);
////		}
//		
//		FoodRecommendDAO dao = new FoodRecommendDAO();
//		List<String> list = dao.foodAllData();
//		
//		try {
//			Pattern[] p = new Pattern[list.size()]; // java.util.regexp
//			
//			for(int i=0; i<p.length; i++) {
//				p[i] = Pattern.compile(list.get(i));
//			}
//			
//			Matcher[] m = new Matcher[list.size()];
//			List<RecommendVO> rList = new ArrayList<RecommendVO>();
//			int[] count = new int[list.size()];
//			
//			for(String s : jList) {
//				for(int i=0; i<m.length; i++) {
//					m[i] = p[i].matcher(s);
//					if(m[i].find()) {
//						String ss = m[i].group(); // group => 실제 데이터 가져옴
//						//System.out.println(ss);
//						//RecommendVO vo = new RecommendVO();
//						//vo.setName(ss);
//						//vo.setCount(vo.getCount()+1);
//						//rList.add(vo);
//						count[i]++;
//					}
//				}
//			}
//			// 실제 추천할 데이터 출력
//			for(int i=0; i<list.size(); i++	) {
//				String name = list.get(i);
//				if(count[i] > 2) {
//					System.out.println(name + " : " + count[i]);
//				}
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
