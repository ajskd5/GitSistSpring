<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	width: 800px;
	height: 750px;
}
#chatArea{
	height: 250px;
	overflow-y: auto;
	border: 1px solid black;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>
<script type="text/javascript">
let websocket;
function connection(){ // 연결
	// websocket => 웹에서 연결이 되게 만드는 소프트웨어
	websocket = new WebSocket("ws://localhost:8080/web/site/chat/chat-ws");
	websocket.onopen = onOpen; // callback함수 (시스템에 의해 자동 호출)
	websocket.onclose=onClose;
	websocket.onmessage = onMessage;
}
function onOpen(event){ // 연결되었을 때 처리
	alert("채팅서버 연결");
}
function onClose(event){ // 퇴장시 처리
	alert("채팅서버 종료");
}
function onMessage(event){ // 채팅 메세지 전송시
	let data = event.data;
	if(data.substring(0, 4)=="msg:"){
		appendMessage(data.substring(4));
	}
}
function disconnection(){ // 종료 => 퇴장 버튼 클릭
	websocket.close(); // onclose() 호출 (chatServer)
}
function send(){ // 메세지 보낼 때
	let name = $('#name').val();
	if(name.trim() == ""){
		$('#name').focus();
		return;
	}
	let msg = $('#sendMsg').val();
	if(msg.trim() == ""){
		$('#sendMsg').focus();
		return;
	}
	websocket.send("msg:[" + name + "]" + msg); // onMessage 호출 (chatServer)
	$('#sendMsg').val(""); // 채팅 입력하면 입력창 공백 초기화
	$('#sendMsg').focus();
}
function appendMessage(msg){ // 문자열 결합 => 채팅 문자열
	$('#recvMsg').append(msg + "<br>");
	 
	 // 문자열 위치
	let ch = $('#chatArea').height();
	let m = $('#recvMsg').height()-ch;
	// textarea 스크롤바 맨 아래로 하게 (원래는 맨 위로 고정)
	$('#chatArea').scrollTop(m);
}
$(function(){
	$('#startBtn').click(function(){ // 입장
		connection();
	})
	
	$('#endBtn').click(function(){ // 퇴장
		disconnection();
	})
	
	$('#sendBtn').click(function(){ // 입력
		send();
	})
	
	$('#sendMsg').keydown(function(key){
		if(key.keyCode==13){ // 엔터키 누르면 전송
			send();
		}
	})
	
})
</script>
</head>
<body>
  <div class="container">
    <div class="row row1">
      <h1 class="text-center">WebSocket 채팅</h1>
      <table class="table">
        <tr>
          <td>
            이름 : <input type="text" id="name" size="15" class="input-sm">
            <input type="button" id="startBtn" value="입장" class="btn btn-sm btn-danger">
            <input type="button" id="endBtn" value="퇴장" class="btn btn-sm btn-primary">
          </td>
        </tr>
        <tr>
          <td>
            <div id="chatArea">
              <div id="recvMsg">
              
              </div>
            </div>
          </td>
        </tr>
        <tr>
          <td>
            <input type="text" id="sendMsg" size="80" class="input-sm">
            <input type="button" id="sendBtn" value="입력" class="btn btn-sm btn-success">
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>