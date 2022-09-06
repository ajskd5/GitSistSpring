package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/*
 *  Model
 *  DAO
 *  Service
 *  ============= singleton
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;

import org.snu.ids.ha.index.*;

@Controller
@RequestMapping("databoard/") // 공통으로 적용되는 URI주소 설정
public class DataBoardController {
	@Autowired
	private DataBoardDAO dao;
	
	@GetMapping("list.do")
	public String databoard_list(String page, Model model) {
		// Model => 전송 객체 ==> request를 list.jsp로 전송
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);

		// 해당 페이지의 데이터 값
		Map map = new HashMap();
		int rowSize = 10;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = rowSize*curpage;
		map.put("start", start);
		map.put("end", end);
		List<DataBoardVO> list = dao.boardListData(map);
		
		// 총 페이지
		int totalpage = dao.boardTotalPage();
		
		// list.jsp로 받은 데이터 전송 ==> 화면에 출력
		/*
		 *  서버 프로그램 : 소켓 (C언어)
		 *  웹 프로그램
		 *  모바일 프로그램 : Kotlin
		 *  인공지능 프로그램 : 알고리즘, AL ==> 데이터 분석 (파이썬)
		 *  데이터베이스 프로그램 : 오라클 (어드민) => ERP
		 */
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		return "databoard/list";
	}
	
	// 글쓰기 창
	@GetMapping("insert.do")
	public String databoard_insert() {
		return "databoard/insert";
	}
	
	// 글쓰기
	@PostMapping("insert_ok.do")
	public String databoard_insert_ok(DataBoardVO vo) {
		List<MultipartFile> list = vo.getFiles();
		String path = "c:\\download\\";
		try {
			if(list == null) { // 업로드 파일이 없는 경우
				vo.setFilename("");
				vo.setFilesize("");
				vo.setFilecount(0);
			} else { // 업로드 파일 존재하는 경우
				String temp1 = "";
				String temp2 = "";
				
				for(MultipartFile mf : list) {
					String filename = mf.getOriginalFilename(); // 사용자가 선택한 파일명
					mf.transferTo(new File(path + filename)); // 업로드 => path위치에 업로드
					temp1 += filename + ",";
					File f = new File(path + filename);
					long len = f.length();
					temp2 += len + ",";
				}
				temp1 = temp1.substring(0, temp1.lastIndexOf(","));
				temp2 = temp2.substring(0, temp2.lastIndexOf(","));
				vo.setFilename(temp1);
				vo.setFilesize(temp2);
				vo.setFilecount(list.size());
			}
			dao.boardInsert(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:list.do";
	}
	
	// 상세보기
	@GetMapping("detail.do")
	public String databoard_detail(int no, Model model) {
		//DAO 연동 ==> 다운로드
		DataBoardVO vo = dao.databoardDetailData(no);
		model.addAttribute("vo", vo);
		
		// 업로드가 된 경우에만 전송
		if(vo.getFilecount() != 0) {
			List<String> fList = new ArrayList<String>(); // filename
			List<String> sList = new ArrayList<String>(); // filesize
			
			StringTokenizer st = new StringTokenizer(vo.getFilename(), ",");
			while(st.hasMoreTokens()) {
				fList.add(st.nextToken());
			}
			st = new StringTokenizer(vo.getFilesize(), ",");
			while(st.hasMoreTokens()) {
				sList.add(st.nextToken());
			}
			model.addAttribute("fList", fList);
			model.addAttribute("sList", sList);
		}
		
		// 구글 파이차트
		// 명사만 가져오기
		String data = vo.getContent();
		// 숫자, 영어 제거
		data = data.replaceAll("[0-9]", ""); 
		data = data.replaceAll("[a-zA-z]", "");
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList kl = ke.extractKeyword(data, true);
		List<DataVO> list = new ArrayList<DataVO>();
		for(int i=0; i<kl.size(); i++) {
			Keyword kwrd = kl.get(i);
			if(kwrd.getCnt() > 1) { // 두번이상 언급된 명사
				DataVO dvo = new DataVO();
				dvo.setWord(kwrd.getString());
				dvo.setCount(kwrd.getCnt());
				list.add(dvo);
			}
		}
		model.addAttribute("list", list);
		
		return "databoard/detail";
	}
	
	/*
	 *  Model ==> @Controller, @RestController
	 *  1. 매개변수
	 *  2. 리턴형
	 *  3. 파일 => redirect:(sendRedirect), 경로 / 파일 (forward)
	 *  		=> request를 초기화 : 재전송  => JSP request전송
	 */
	// 파일 다운로드
	@GetMapping("download.do")
	public void databoard_download(String fn, HttpServletResponse response) { // response => 파일전송
		 try {
			File file = new File("c:\\download\\" + fn);
			// 다운로드창 보여줌
			response.setContentLength((int)file.length());
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fn, "UTF-8"));
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); // 서버 => 서버에서 파일을 읽음
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream()); // 클라이언트 => 클라이언트로 전송
			byte[] buffer = new byte[1024];
			int i=0; // 읽은 바이트수
			while((i=bis.read(buffer, 0, 1024)) != -1) { // -1 => EOF (End Of File)
				bos.write(buffer, 0, i);
			}
			
			bis.close();
			bos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 수정하기
	@GetMapping("update.do")
	public String databoard_update(int no, Model model) {
		DataBoardVO vo = dao.databoardUpdateData(no);
		
		model.addAttribute("vo", vo);
		return "databoard/update";
	}
	
	// 삭제하기
	@GetMapping("delete.do")
	public String databoard_delete(int no, Model model) {
		model.addAttribute("no", no);
		return "databoard/delete";
	}
	
}
