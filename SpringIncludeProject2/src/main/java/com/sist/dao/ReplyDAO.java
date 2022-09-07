package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.sist.mapper.*;

@Repository
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;
	
	// 리스트 출력
	public List<ReplyVO> replyListData(ReplyVO vo){
		return mapper.replyListData(vo);
	}
	
	// 댓글 쓰기
	public void replyInsert(ReplyVO vo) {
		mapper.replyInsert(vo);
	}
	
	// 댓글 수정
	public void replyUpdate(ReplyVO vo) {
		mapper.replyUpdate(vo);
	}
	
	// 대댓글 (트랜잭션)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void replyReplyInsert(int pno, ReplyVO vo) {
		ReplyVO pvo = mapper.replyParentInfoData(pno);
		mapper.replyGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(pvo.getRoot());
		mapper.replyReplyInsert(vo);
		mapper.replyDepthIncrement(pno);
	}
	
	// 댓글 삭제
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void replyDelete(int no) {
		// 1. depth, root 정보 가져오기
		ReplyVO vo = mapper.replyInfoData(no);
		if(vo.getDepth()==0) {
			mapper.replyDelete(no);
		} else {
			mapper.replyMsgUpdate(no);
		}
		mapper.replyDepthDecrement(vo.getRoot());
	}
}
