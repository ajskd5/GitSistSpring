package com.sist.di;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MainClass {
	public static void main(String[] args) {
		//Spring 세팅 (MovieDAO 첨부)
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		// MovieDAO dao = new MovieDAO() ==>> Null   app.xml에서 ssf dao로 받음(spring안에 저장된 객체가 가지고 있음)
		
		Scanner scan = new Scanner(System.in);
		System.out.print("페이지 입력 : ");
		int page = scan.nextInt();
		
		int rowSize = 10;
		int start = (rowSize*page) - (rowSize-1);
		int end = rowSize * page;
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		MovieDAO dao = (MovieDAO)app.getBean("dao");
		List<MovieVO> list = dao.movieListData(map);
		
		// 출력
		for(MovieVO vo : list) {
			System.out.println(vo.getMno() + "." + vo.getTitle());
		}
		
	}
}
