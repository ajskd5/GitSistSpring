package com.sist.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 *  AOP
 *  	Aspect : 공통 모듈 
 *  	JoinPoint : 시점 (언제 호출할지)
 *  	 = Before
 *  	 = After
 *  	 = AfterThrowing
 *  	 = AfterReturning
 *  	 = Around
 *  		
 *  		public String movieListData(){
 *  			=> Before
 *  			try{
 *  				==> Around => 수행 시간, setAutoCommit(false)
 *  				소스코딩
 *  				==> Around ==> commit
 *  			} catch(Exception e){
 *  				==> AfterThrowing ==> rollback()
 *  			} finally {
 *  				==> After ==> setAutoCommit(true)
 *  			}
 *  			return 값; ==> AfterReturning
 *  		}
 *  
 *  	PointCut : 어떤 메소드에 적용
 *  			execution("* com.sist.main.MovieDAO.* (..)")
 *  					   -						-  --
 *  					리턴형				모든 메소드	 모든 매개변수
 *  			within("com.sist.main.*") => 패키지에 있는 모든 클래스 적용
 *  	Advice : 기능 (JoinPoint + PointCut) => 어떤 메소드, 어느 시점
 *  	Weaving : 통합 => Proxy패턴 (대리자)
 *  
 */
public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app1.xml");
		MovieDAO dao = (MovieDAO)app.getBean("dao");
		dao.movieListData();
		dao.movieDetailData();
		dao.movieFindData();

	}

}
