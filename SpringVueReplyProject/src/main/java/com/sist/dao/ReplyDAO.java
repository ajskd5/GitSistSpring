package com.sist.dao;

import org.springframework.stereotype.Repository;

import com.sist.vo.ReplyVO;

import oracle.jdbc.OracleTypes;

import java.util.*;
import java.sql.*;

// Procedure
@Repository
public class ReplyDAO {
	private Connection conn;
	private CallableStatement cs;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	public ReplyDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disConnection() {
		try {
			if(cs != null) {
				cs.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 댓글 읽기
	public List<ReplyVO> replyListData(ReplyVO vo){
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		try {
			getConnection();
			String sql = "{CALL replyListData(?, ?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, vo.getCno());
			cs.setInt(2, vo.getType());
			// 1. int ==> OracleTypes.INTEGER
			// 2. String ==> OracleTyeps.VARCHAR
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.executeQuery();
			ResultSet rs = (ResultSet)cs.getObject(3);
			// Cursor == ResultSet
			// type => 4 : location, 5 : nature, 6 : shop => 1, 2, 3 (+3)
			while(rs.next()) {
				ReplyVO rvo = new ReplyVO();
				rvo.setNo(rs.getInt(1));
				rvo.setCno(rs.getInt(2));
				rvo.setType(rs.getInt(3));
				rvo.setId(rs.getString(4));
				rvo.setName(rs.getString(5));
				rvo.setMsg(rs.getString(6));
				rvo.setDbday(rs.getString(7));
				list.add(rvo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}
	
	// 댓글 쓰기
	public void replyInsert(ReplyVO vo) {
		try {
			getConnection();
			String sql = "{CALL replyInsert(?, ?, ?, ?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, vo.getCno());
			cs.setInt(2, vo.getType());
			cs.setString(3, vo.getId());
			cs.setString(4, vo.getName());
			cs.setString(5, vo.getMsg());
			cs.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	// 댓글 수정
	public void replyUpdate(ReplyVO vo) {
		try {
			getConnection();
			String sql = "{CALL replyUpdate(?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, vo.getNo());
			cs.setString(2, vo.getMsg());
			cs.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	// 댓글 삭제
	public void replyDelete(int no) {
		try {
			getConnection();
			String sql = "{CALL replyDelete(?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
}
