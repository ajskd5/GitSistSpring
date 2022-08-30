package com.sist.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // 공통 모듈 => 메모리 할당을 못함 (로그처리) => 트랜잭션, 보안
@Component // 메모리 할당
public class GoodsAOP {
	//				 리턴형			모든 controller로 끝나는 클래스  모든 메소드  매개변수 있든 없든 모든것
	@Around("execution(* com.sist.web.*Controller.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		Object obj = null;
		try {
			long start = System.currentTimeMillis();
			obj = jp.proceed(); // 메소드 호출
			long end = System.currentTimeMillis();
			System.out.println("=========== 공통 기반 처리 ===========");
			System.out.println("1. 사용자 요청 메소드 : " + jp.getSignature().getName());
			System.out.println("2. 수행 시간 : " + (end - start));
			System.out.println("======================================");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return obj;
	}
	
	// 모니터링 ==> 견고한 프로그램 (견고성(서버), 가독성(유지보수), 최적화(속도))
	@AfterReturning(value="execution(* com.sist.web.*Controller.*(..))", returning = "obj")
	public void afterReturning(Object obj) throws Throwable {
		System.out.println("======== 화면 이동 ========");
		System.out.println(obj.toString());
		System.out.println("===========================");
	}
}
