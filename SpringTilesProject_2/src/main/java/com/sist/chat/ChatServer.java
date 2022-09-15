package com.sist.chat;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.*;

// 연결 주소
@ServerEndpoint("/site/chat/chat-ws")
public class ChatServer {
	// 접속자 중복을 허용하지 않음 ==> 동기화로 저장
	
	private Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session session) {
		users.add(session);
		System.out.println("클라이언트 접속... : " + session.getId()); // getId -> 0, 1, 2, ...
	}
	
	@OnClose
	public void onClose(Session session) {
		users.remove(session);
		System.out.println("클라이언트 퇴장... : " + session.getId());
	}
	
	@OnMessage
	public void onMessage(String message, Session session) throws Exception{
		// Thread 동기화 프로그램
		synchronized (users) {
			for(Session s : users) {
				s.getBasicRemote().sendText(message);
			}
		}
	}
}
