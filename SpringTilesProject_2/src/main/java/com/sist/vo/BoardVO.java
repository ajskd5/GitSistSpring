package com.sist.vo;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private int no, hit;
	private String name, subject, content, dbday, pwd;
	private Date regdate;
}
