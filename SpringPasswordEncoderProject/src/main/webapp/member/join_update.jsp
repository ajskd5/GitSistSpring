<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(function(){
	// 우편번호 검색
	$('#postBtn').click(function(){
		new daum.Postcode({
			oncomplete:function(data)
			{
				$('#post').val(data.zonecode)
				$('#addr1').val(data.address)
			}
		}).open()
	})
	
});
</script>
</head>
<body>
  <div class="container">
    <h1 class="text-center">회원정보 수정</h1>
    <div class="row">
      <form method="post" action="../member/join_update_ok.do">
      <table class="table">
        <tr>
          <th width="10%" class="warning text-right">ID</th>
          <td width="90%">
            <input type="text" class="input-sm" size="20" name="id" readonly="readonly" id="myid">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">이름</th>
          <td width="90%">
            <input type="text" class="input-sm" size="20" name="name">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">성별</th>
          <td width="90%">
            <input type="radio" name="sex" value="남자" checked="checked">남자
            <input type="radio" name="sex" value="여자">여자
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">이메일</th>
          <td width="90%">
            <input type="text" class="input-sm" size="70" name="email">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">우편번호</th>
          <td width="90%">
            <input type="text" class="input-sm" size="10" name="post" readonly="readonly" id="post">
            <input type="button" class="btn btn-sm btn-danger" value="우편번호 검색" id="postBtn">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">주소</th>
          <td width="90%">
            <input type="text" class="input-sm" size="70" name="addr1" id="addr1">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">상세주소</th>
          <td width="90%">
            <input type="text" class="input-sm" size="70" name="addr2">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">전화번호</th>
          <td width="90%">
            <input type="text" class="input-sm" size="15" name="tel1" value="010" readonly="readonly">-
            <input type="text" class="input-sm" size="15" name="tel2">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">자기소개</th>
          <td width="90%">
            <textarea rows="7" cols="70" name="content"></textarea>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="text-center">
            <input type="submit" value="수정하기" class="btn btn-sm btn-primary">
            <input type="button" value="취소" class="btn btn-sm btn-danger" onclick="javascript:history.back()">
          </td>
        </tr>
      </table>
      </form>
    </div>
  </div>
</body>
</html>