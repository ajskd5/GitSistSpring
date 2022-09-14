package com.sist.vo;
/*
NO      NOT NULL NUMBER       
CNO              NUMBER       
TYPE             NUMBER       
ID               VARCHAR2(20) 
NAME    NOT NULL VARCHAR2(34) 
MSG     NOT NULL CLOB         
REGDATE          DATE  
 */
import java.util.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVO {
	// no => 댓글 번호 cno => 글 번호 type => 1,2,3(명소,자연,쇼핑)
	private int no, cno, type;
	private String id, name, msg, dbday;
	private Date regdate;
}
