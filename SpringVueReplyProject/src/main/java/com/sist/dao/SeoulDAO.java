package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 *  1. 스프링 : 클래스를 모아서 관리 => 의존성이 낮은 프로그램 => 유지보수시 다른 클래스에 영향이 없는 프로그램)
 *  			============= 사용자 정의, 라이브러리(컨테이너)
 *  			클래스 : 컴포넌트 (기능을 가지고 있는 클래스)
 *  	컨테이너 종류, 역할
 *  		종류
 *  				BeanFactory
 *  					|
 *  			ApplicationContext  => AnnotationConfigApplicationContext (자바로 환경설정)
 *  					|
 *  			WebApplicationContext => AnnotationConfigWebApplicationContext
 *  
 *  2. 클래스를 모아서 관리
 *  	사용자가 요청하면 => 클래스 찾기 (DL) => getBean()
 *  	클래스 관리를 하기 위해 필요한 데이터 첨부 => DI
 *  	
 *  3. MVC (스프링 라이브러리)
 *  
 *  ==========================================================================
 *  자바 / 오라클 / JSP / 스프링 / AWS
 *  스프링 생명주기 (객체 생명주기 관리 : 생성 ~ 소멸)
 *  									생성시 필요한 데이터가 있을 수 있음 (DI)
 *  	IoC (제어의 역전) : 스프링을 통해 객체를 가지고 온다
 *  		지금은 DI로 통합 (객체와 객체의 연관 관계 설계)
 *  
 *  	1. 클래스 메모리 할당 (모든 클래스)
 *  		1) 한개씩 생성
 *  			<bean id="구분자" class="패키지.클래스명">
 *  		2) 패키지 단위로 생성
 *  			<context:component-scan base-package="패키지명">
 *  									---------------------
 *  									메모리 할당 대상 (선택)
 *  									@Controller : 웹 화면 변경 / 사요자 요청 처리 / JSP에 출력할 데이터 전송
 *  													forward : request를 유지하면서 새로운 데이터 추가
 *  														=> (request 대신 Model (전송 객체) => addAttribute()
 *  														=> return "경로명/파일명";
 *  													redirect : request를 초기화하고 기존에 만든 파일로 이동시
 *  														=> Model 사용 불가 (재전송)
 *  														=> return "redirect:~.do";
 *  										*** 핵심 : 매개변수 (사용자가 요청한 데이터)
 *  										*** 웹 : C/S (Client / Server) => 요청 / 응답 => 리턴형
 *  									@RestController : 사용자 요청 처리 / 다른 프로그램에 데이터 전송
 *  														=> 자바스크립트 연동, Kotlin, ...
 *  														=> JSON (자바스크립트 객체 표현법)	: {키:값, 키:값, ...} => 키 : 멤버변수
 *  																let sa = {"sabun":1, "name":'홍길동"} => sa.sabun, sa.name
 *  														=> 일반데이터, VO단위=> {}, List단위 => [{}, {}, ...]
 *  																		JSONObject		JSONArray
 *  															=> Spring-Boot는 자동으로 JSON이 만들어짐
 *  													Get => @GetMapping
 *  													Post => @PostMapping
 *  													Get + Post => @RequetMapping
 *  													@DeleteMapping(삭제), @PutMapping(업데이트)
 *  									@Component : 일반 클래스 (AOP, Intercepter, MainClass, Manager, ...)
 *  									@Repository	 : DAO(데이터베이스)
 *  									@Service :  BI (DAO 통합)
 *  									@ControllerAdvice : 통합 예외처리
 *  
 *  									메모리 할당 제외
 *  										~VO : 사용자 정의 데이터형
 *  										~Mapper : 인터페이스
 *  	2. Setter DI 
 *  		어노테이션으로 메모리 할당 시 DI를 사용할 수 없다
 *  		--------------------------------------------------
 *  		DI)
 *  			= 일반 데이터 주입 (불가능) => XML로 선언
 *  			= 객체 주소 주입 => @Autowired
 *  			<bean id="" class="" p:~="">
 *  			일반변수 : p:name=""
 *  			주소값 : p:ds-ref="id명"
 *  	3. 대기
 *  	---------------------- 객체 생성 (사용자가 호출시)
 *  	4. 사용자가 필요한 위치에서 객체 요청
 *  	---------------------------------------------------
 *  	5. 객체 소멸
 *  
 *  기능
 *  	1) DI : setter DI, constructor DI
 *  			methodDI (객체 생성시 : init-method
 *  						객체 소멸시 : destroy-method)
 *  	2) AOP : 공통모듈 (모든 Web에서 사용) => 자동 호출
 *  			Join Point : 첨부할 위치
 *  				Before
 *  				After
 *  				After-Returning
 *  				After-Throwing
 *  				Around
 *  			Point Cut : 메소드 대상
 *  			========= + Advice
 *  			=================== Aspect
 *  	3) MVC : 									HandlerMapping
 *  		사용자 요청 ~.do ===> DispatcherServlet 호출 ====> @Controller / @RestController
 *  													구분자 => GetMapping / PostMapping
 *  														| 사용자가 보내준 요청 데이터를 매개변수로 받음
 *  														| 처리 결과값을 보낸다
 *  													ViewResolver가 받음 : JSP를 찾는 역할
 *  														==> 경로명/파일명 보내야함
 *  														|
 *  														JSP
 *  	4) ORM (MyBatis 연결)
 *  		= XML버전 많이 사용
 *  		= Annotation 버전으로 넘어가는 중
 *  			4버전 => 혼합
 *  			5버전 => 순수 자바
 */
// MyBatis

import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	
//	@Select("SELECT no, poster, name, num "
//			+ "FROM (SELECT no, poster, name, rownum as num "
//			+ "FROM (SELECT no, poster, name "
//			+ "FROM ${table_name} ORDER BY no ASC)) "
//			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<SeoulVO> seoulListData(Map map){
		return mapper.seoulListData(map);
	}
	
//	@Select("SELECT CEIL(COUNT(*)/12.0)FROM ${table_name}")
	public int seoulTotalPage(Map map) {
		return mapper.seoulTotalPage(map);
	}
	
	// 상세보기 조회수 증가
//	@Update("UPDATE ${table_name} SET hit = hit+1 "
//			+ "WHERE no=#{no}")
//	@Select("SELECT * FROM ${table_name} WHERE no=#{no}")
	public SeoulVO seoulDetailData(Map map) {
		mapper.hitIncrement(map);
		return mapper.seoulDetailData(map);
	}
	
	// 로그인
	// 로그인 처리
//	@Select("SELECT COUNT(*) FROM spring_member "
//			+ "WHERE id=#{id}")
//	@Select("SELECT pwd, name FROM spring_member "
//			+ "WHERE id=#{id}")
	public MemberVO isLogin(String id, String pwd) {
		MemberVO vo = new MemberVO();
		System.out.println("asdf");
		int count = mapper.memberIdCheck(id);
		System.out.println("qwer");
		if(count == 0) {
			vo.setMsg("NOID");
		} else {
			MemberVO rvo = mapper.memberInfoData(id);
			if(pwd.equals(rvo.getPwd())) {
				vo.setMsg("OK");
				vo.setName(rvo.getName());
				vo.setId(id);
			} else {
				vo.setMsg("NOPWD");
			}
		}
		
		return vo;
	}
}
