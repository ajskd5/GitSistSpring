package com.sist.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BoardAspect {
	// 시점 => 메소드를 적용한 위치 => JoinPoint
	// 어떤 메소드를 호출할건지 => PointCut
	// JoinPoint + PointCut => Weaving
	@Before("execution(* com.sist.web.*Controller.*(..))") // 메소드 진입 시 호출  		Controller로 끝나는 모든 메소드에 적용
	public void before(JoinPoint jp) {
		String name = jp.getSignature().getName();
		System.out.println(name + " 진입...");
	}
	
	@After("execution(* com.sist.web.*Controller.*(..))")  // 메소드 => finally에서 호츨  ==> 사이트에 공통으로 출력해야 하는 부분에 사용
	public void after(JoinPoint jp) {
		String name = jp.getSignature().getName();
		System.out.println(name + " 정상 수행 완료...");
	}
	@AfterReturning(value = "execution(* com.sist.web.*Controller.*(..))", returning = "obj") // 정상 수행 시 호출 => return값 받아서 처리
	public void afterReturning(String obj) {
		System.out.println(obj + ".jsp로 이동 완료...");
	}
}
