package com.sist.spring;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.*;
public class ApplicationContext {
	private Map map = new HashMap();
	String path = "C:\\springDev\\springStudy\\SpringDIProject_3\\src\\main\\java\\com\\sist\\spring\\";
	public ApplicationContext(String filename) {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLParser xml = new XMLParser();
			sp.parse(new File(path+filename), xml);
			map = xml.map;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Object getBean(String id) {
		return map.get(id);
	}
}
