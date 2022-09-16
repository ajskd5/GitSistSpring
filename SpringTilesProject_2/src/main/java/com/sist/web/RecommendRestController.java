package com.sist.web;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.RecommendDAO;
import com.sist.recommend.NaverDataClass;
import com.sist.recommend.RecommendManager;
import com.sist.vo.FoodVO;
/*
상황
휴식 드라이브 산책 집 출/퇴근길 휴가/여행 운동 하우스파티 시상식 일/공부 카페 거리 클럽 고백 해변 공연 라운지 애도 집중

감성
외로움 기분전환 슬픔 힘찬 이별 지침/힘듦 설렘 오후 위로 밤 새벽 저녁 아침 사랑 스트레스/짜증 그리움 추억 우울 행복 불안 분노 기쁨 축하

스타일
밝은 신나는 웅장한 따뜻한 편안한 그루브한 부드러운 로맨틱한 매혹적인 영화음악 잔잔한 댄서블한 달콤한 몽환적인 시원한 애절한 어두운 연주음악 발렌타인데이 화이트데이

날씨/계절
봄 여름 가을 겨울 맑은날 추운날 흐린날 비오는날 더운날 안개낀날 눈오는날
 */
@RestController
public class RecommendRestController {
	@Autowired
	private NaverDataClass nds;
	@Autowired
	private RecommendManager rm;
	@Autowired
	private RecommendDAO dao;
	
	@GetMapping(value = "recommend/recommend_sub.do", produces = "text/plain;charset=UTF-8")
	public String recommend_sub(int type) {
		String[] type1 = {"봄", "여름", "가을", "겨울", "맑은날", "추운날", "흐린날", "비오는날", "더운날", "눈오는날",};
		String[] type2 = {"휴식", "산책", "퇴근길", "휴가", "여행", "운동", "고백"};
		String[] type3 = {"외로움", "기분전환", "슬픔", "이별", "지침", "위로", "짜증", "그리움", "우울", "행복", "불안", "기쁨", "축하"};
		String[] type4 = {"밝은", "신나는", "따뜻한", "편안한", "달콤한", "시원한", "애절한", "어두운"};
		List<String> list = new ArrayList<String>();
		
		if(type == 1) {
			list = Arrays.asList(type1);
		} else if(type == 2) {
			list = Arrays.asList(type2);
		} else if(type == 3) {
			list = Arrays.asList(type3);
		} else if(type == 4) {
			list = Arrays.asList(type4);
		}
		String result = "";
		try {
			JSONArray arr = new JSONArray();
			for(String s : list) {
				arr.add(s);
			}
			result = arr.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@PostMapping(value = "recommend/recommend_data.do", produces = "text/plain;charset=UTF-8")
	public String recommend_data(String fd) {
		String result = "";
		String json = nds.recommendData(fd);
		List<String> list = rm.jsonParser(json);
		List<String> fList = dao.recommendNameData();
		
		try {
			Pattern[] p = new Pattern[fList.size()]; // java.util.regexp
			
			for(int i=0; i<p.length; i++) {
				p[i] = Pattern.compile(fList.get(i));
			}
			
			Matcher[] m = new Matcher[fList.size()];
			
			int[] count = new int[fList.size()];
			
			for(String s : list) {
				for(int i=0; i<m.length; i++) {
					m[i] = p[i].matcher(s);
					if(m[i].find()) {
						String ss = m[i].group(); // group => 실제 데이터 가져옴
						//System.out.println(ss);
						//RecommendVO vo = new RecommendVO();
						//vo.setName(ss);
						//vo.setCount(vo.getCount()+1);
						//rList.add(vo);
						count[i]++;
					}
				}
			}
			
			List<FoodVO> sList = new ArrayList<FoodVO>();
			
			// 실제 추천할 데이터 출력
			for(int i=0; i<fList.size(); i++) {
				String name = fList.get(i);
				if(count[i] >= 2) {
					System.out.println(name + " : " + count[i]);
					FoodVO vo = dao.recommendDetailData(name);
					sList.add(vo);
				}
			}
			
			JSONArray arr = new JSONArray();
			for(FoodVO vo : sList) {
				JSONObject obj = new JSONObject();
				obj.put("fno", vo.getFno());
				obj.put("name", vo.getName());
				obj.put("poster", vo.getPoster().substring(0, vo.getPoster().indexOf("^")));
				arr.add(obj);
			}
			result = arr.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
