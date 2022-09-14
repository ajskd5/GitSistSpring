package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

/*
ID   NOT NULL VARCHAR2(20) 
PWD  NOT NULL VARCHAR2(10) 
NAME NOT NULL VARCHAR2(34) 
ROLE NOT NULL VARCHAR2(20) 
 */
@Getter
@Setter
public class MemberVO {
	private String id, pwd, name, role, msg;
}
