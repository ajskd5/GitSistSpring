<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 500px;
}
h1{
	text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#deleteBtn').on('click', function(){
		let pwd = $('#pwd').val();
		if(pwd.trim() == ""){
			$('#pwd').focus();
			return;
		}
		$('#frm').submit();
	})
})
</script>
</head>
<body>
  <div class="container">
    <h1>삭제 비밀번호 확인</h1>
    <div class="row">
      <form method="post" action="delete_ok.do" id="frm">
      <table class="table">
        <tr>
          <td class="text-center">
            비밀번호 : <input type="password" name="pwd" id="pwd">
            <input type="hidden" name="no" value="${vo.no }">
          </td>
        </tr>
        <tr>
          <td class="text-center">
		    <input type="button" value="삭제" class="btn btn-sm btn-warning" id="deleteBtn">
		    <input type="button" value="취소" class="btn btn-sm btn-info" onclick="javascript:history.back()">
		  </td>
        </tr>
      </table>
      </form>
    </div>
  </div>
</body>
</html>