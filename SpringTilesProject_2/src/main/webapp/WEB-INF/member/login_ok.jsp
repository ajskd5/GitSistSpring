<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
  <c:when test="${vo.msg=='NOID' }">
    <script>
      alert("존재하지 않는 아이디입니다!!");
      history.back();
    </script>
  </c:when>
  <c:when test="${vo.msg=='NOPWD' }">
    <script>
      alert("비밀번호가 틀렸습니다!!");
      history.back();
    </script>
  </c:when>
  <c:otherwise>
    <script>
      location.href="../main/main.do";
    </script>
  </c:otherwise>
</c:choose>