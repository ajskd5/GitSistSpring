package com.sist.spring1;

public class Student {
	private int hakbun;
	private String name;
	private int kor, eng, math;
	public int getHakbun() {
		return hakbun;
	}
	public void setHakbun(int hakbun) {
		this.hakbun = hakbun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	/////////////////////////////////////////////////////// Spring에서 처리 가능
	public void init() {
		System.out.println("======== 성적표 ========");
		
	}
	public void destroy() {
		System.out.println("========================");
	}
///////////////////////////////////////////////////////////
	public void print() { // 사용자에 의해 호출
		System.out.println("학번 :" + hakbun);
		System.out.println("이름 :" + name);
		System.out.println("국어 :" + kor);
		System.out.println("영어 :" + eng);
		System.out.println("수학 :" + math);
		System.out.println("총점 : " + (kor + eng + math));
		System.out.printf("평균 : %.2f\n", (kor + eng + math)/3.0);
	}
}
