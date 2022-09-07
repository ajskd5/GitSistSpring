package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
/*
NO         NOT NULL NUMBER       
BNO                 NUMBER    ==> 상품번호    
ID                  VARCHAR2(20) 
NAME       NOT NULL VARCHAR2(34) 
MSG        NOT NULL CLOB         
REGDATE             DATE         
GROUP_ID            NUMBER       
GROUP_STEP          NUMBER       
GROUP_TAB           NUMBER       
ROOT                NUMBER       
DEPTH               NUMBER       
TYPE                NUMBER
 */
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sist.dao.*;

public interface ReplyMapper {
	
	// 댓글 리스트
	@Select("SELECT no, bno, id, name, msg, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday, group_tab, type "
			+ "FROM spring_reply WHERE bno=#{bno} AND type=#{type} "
			+ "ORDER BY group_id DESC, group_step ASC")
	public List<ReplyVO> replyListData(ReplyVO vo);
	
	// 댓글 추가
		// 시퀀스 => SelectKey 는 Primary Key만 적용 (하나만 됨)
	@SelectKey(keyProperty = "no", resultType = int.class, before = true, statement = "SELECT NVL(MAX(no)+1,1) as no FROM spring_reply")
	@Insert("INSERT INTO spring_reply(no, bno, id, name, msg, group_id, type) "
			+ "VALUES(#{no}, #{bno}, #{id}, #{name}, #{msg}, (SELECT NVL(MAX(group_id)+1, 1) as group_id FROM spring_reply), #{type})")
	public void replyInsert(ReplyVO vo);
	
	// 수정
	@Update("UPDATE spring_reply SET msg=#{msg} "
			+ "WHERE no=#{no}")
	public void replyUpdate(ReplyVO vo);
	
	
	// 삭제 => 트랜잭션
		// 1. depth, root 가져옴   depth => 댓글 수
	@Select("SELECT depth, root FROM spring_reply WHERE no=#{no}")
	public ReplyVO replyInfoData(int no);
		// 2-1 depth > 0 => UPDATE,  depth == 0 => DELETE
	@Update("UPDATE spring_reply SET msg='관리자가 삭제한 글입니다' WHERE no=#{no}")
	public void replyMsgUpdate(int no);
		// 2-2 삭제
	@Delete("DELETE FROM spring_reply WHERE no=#{no}")
	public void replyDelete(int no);
		// 3. depth 감소
	@Update("UPDATE spring_reply SET depth=depth-1 "
			+ "WHERE no=#{no}")
	public void replyDepthDecrement(int no);
	
	
	
	
	// 대댓글 => 트랜잭션
		// pno = group_id, group_step, group_tab
	@Select("SELECT group_id, group_step, group_tab FROM spring_reply "
			+ "WHERE no=#{no}")
	public ReplyVO replyParentInfoData(int no);
		// group_step 변경
	@Update("UPDATE spring_reply SET group_step=group_step+1 "
			+ "WHERE group_id=#{group_id} AND group_step>#{group_step}")
	public void replyGroupStepIncrement(ReplyVO vo);
		// insert
	@SelectKey(keyProperty = "no", resultType = int.class, before = true, statement = "SELECT NVL(MAX(no)+1,1) as no FROM spring_reply")
	@Insert("INSERT INTO spring_reply(no, bno, id, name, msg, group_id, type, group_step, group_tab, root) "
			+ "VALUES(#{no}, #{bno}, #{id}, #{name}, #{msg}, #{group_id}, #{type}, #{group_step}, #{group_tab}, #{root})")
	public void replyReplyInsert(ReplyVO vo);
		// depth 변경
	@Update("UPDATE spring_reply SET depth = depth+1 "
			+ "WHERE no=#{no}")
	public void replyDepthIncrement(int no);
	
	
}
