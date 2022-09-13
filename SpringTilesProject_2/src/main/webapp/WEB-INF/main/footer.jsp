<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="container">
    <div class="row">
      <div class="col-sm-4">
        <h3>인기 명소</h3>
        <hr>
        <ul>
          <c:forEach var="vo" items="${sList }">
            <li>${vo.title }(${vo.hit })</li>
          </c:forEach>
        </ul>
      </div>
      <div class="col-sm-4">
        <h3>인기 자연</h3>
        <hr>
        <ul>
          <c:forEach var="vo" items="${nList }">
            <li>${vo.title }(${vo.hit })</li>
          </c:forEach>
        </ul>
      </div>
      <div class="col-sm-4">
        <h3>인기 맛집</h3>
        <hr>
      </div>
    </div>
  </div>
</body>
</html>