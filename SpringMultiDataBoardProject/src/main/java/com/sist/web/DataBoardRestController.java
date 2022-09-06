package com.sist.web;

import java.io.*;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.dao.*;

@RestController
public class DataBoardRestController {
	@Autowired
	private DataBoardDAO dao;
	
	// 비밀번호 입력하고 수정 누르면 비밀번호 확인 후 스크립트 리턴
	@PostMapping(value = "databoard/update_ok.do", produces = "text/html;charset=UTF-8") // 한글 깨짐 방지
	public String databoard_update_ok(DataBoardVO vo) {
		String result = "";
		boolean bCheck = dao.databoardUpdate(vo);
		if(bCheck == true) {
			result = "<script>"
					+ "location.href=\"detail.do?no=" + vo.getNo() + "\";"
					+ "</script>";
		} else {
			result = "<script>"
					+ "alert(\"비밀번호가 틀립니다!!\");"
					+ "history.back();"
					+ "</script>";
		}
		
		return result;
	}
	
	@PostMapping(value = "databoard/delete_ok.do", produces = "text/html;charset=UTF-8")
	public String databoard_delete_ok(int no, String pwd) {
		String result = "";
		DataBoardVO vo = dao.databoardInfoData(no);
		boolean bCheck = dao.databoardDelete(no, pwd);
		
		if(bCheck == true) {
			result = "<script>"
					+ "location.href=\"list.do\";"
					+ "</script>";
			
			// 다운받은 파일 삭제
			try {
				if(vo.getFilecount() > 0) {
					StringTokenizer st = new StringTokenizer(vo.getFilename(), ",");
					while(st.hasMoreTokens()) {
						File file = new File("c:\\download\\" + st.nextToken());
						file.delete();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			result = "<script>"
					+ "alert(\"비밀번호가 틀립니다!!\");"
					+ "history.back();"
					+ "</script>";
		}
		
		return result;
	}
}
