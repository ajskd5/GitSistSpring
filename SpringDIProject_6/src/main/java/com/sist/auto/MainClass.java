package com.sist.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
/*
 *  	1. 클래스 메모리 할당 ==> 어노테이션 확인
 *  		@Component
 *  		@Repository
 *  		@Service
 *  		@Controller
 *  		@RestController
 *  		@ControllerAdvice
 *  		얘네가 클래스 위에 있으면 메모리 할당
 *  
 *  	2. 생성된 객체 주소값을 받을 경우 ==> 어노테이션 확인
 *  		@Autowired
 *  		@Qualifier
 *  		@Inject
 *  	
 *  	스프링 => XML + Annotation 중심
 */
@Component("mc")
public class MainClass {
	@Autowired
	@Qualifier("ms") // 특정 객체 지정 => 객체의 주소값을 얻어온다
	private DataBase db;
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app2.xml");
		MainClass mc = (MainClass)app.getBean("mc");
		mc.db.getConnection();
		mc.db.disConnection();
	}
}
