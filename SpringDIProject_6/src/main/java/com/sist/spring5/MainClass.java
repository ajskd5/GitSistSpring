package com.sist.spring5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class MainClass {
	@Autowired
	private MovieDAO dao; // 클래스와 클래스의 관계도
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MovieConfig.class);

		Map map = new HashMap();
		map.put("start", 1);
		map.put("end", 100);
		MainClass mc = (MainClass)app.getBean("mainClass");
		List<MovieVO> list = mc.dao.movieListData(map);
		for(MovieVO vo : list) {
			System.out.println(vo.getMno() + "." + vo.getTitle());
		}

	}

//	수동으로 연결
//	public static void main(String[] args) {
//		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MovieConfig.class);
//		MovieDAO dao = app.getBean("movieDAO", MovieDAO.class); // 형변환 없이 쓰는 방법
//			//MovieDAO dao = (MovieDAO)app.getBean("movieDAO"); // 형변환
//		Map map = new HashMap();
//		map.put("start", 1);
//		map.put("end", 100);
//		List<MovieVO> list = dao.movieListData(map);
//		for(MovieVO vo : list) {
//			System.out.println(vo.getMno() + "." + vo.getTitle());
//		}
//
//	}

}
