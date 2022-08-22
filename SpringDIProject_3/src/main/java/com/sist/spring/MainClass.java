package com.sist.spring;

public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app = new ApplicationContext("app.xml");
		Sawon s = (Sawon)app.getBean("sa");
		s.print();

	}

}
