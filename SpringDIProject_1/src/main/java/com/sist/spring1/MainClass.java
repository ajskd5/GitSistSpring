package com.sist.spring1;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		GenericXmlApplicationContext gc = new GenericXmlApplicationContext("app2.xml");
		Student s = (Student)gc.getBean("std");
		// s.init()
		s.print();
		gc.close();
		// s.destroy()

	}

}
