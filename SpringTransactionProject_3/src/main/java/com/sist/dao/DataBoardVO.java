package com.sist.dao;
/*
NO        NOT NULL NUMBER         
NAME      NOT NULL VARCHAR2(34)   
SUBJECT   NOT NULL VARCHAR2(1000) 
CONTENT   NOT NULL CLOB           
PWD       NOT NULL VARCHAR2(10)   
REGDATE            DATE           
HIT                NUMBER         
FILENAME           VARCHAR2(2000) 
FILESIZE           VARCHAR2(1000) 
FILECOUNT          NUMBER   
 */
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DataBoardVO {
	private int no, hit, filecount;
	private String name, subject, content, pwd, filename, filesize;
	private Date regdate;
	private List<MultipartFile> files; // 업로드된 파일 받을 때 사용 (여러개 => List)
	
}
