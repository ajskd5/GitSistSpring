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
<footer class="container text-center">
  <div class="row">
    <div class="col-sm-6">
      <h3>최신 상품 뉴스</h3>
      <table class="table">
        <c:forEach var="vo" items="${newList }" varStatus="s">
          <tr>
            <td class="text-left"><a href="${vo.link }" target="_blank">${s.index+1 }.&nbsp;${vo.title }</a></td>
          </tr>
        </c:forEach>
      </table>
    </div>
    <div class="col-sm-3">
      <h3>최신 게시물</h3>
    </div>
    <div class="col-sm-3">
      <h3>가장 많이 본 상품</h3>
      <table class="table">
        <c:forEach var="vo" items="${gList }" varStatus="s">
          <tr>
            <td class="text-left"><a href="../goods/detail.do?no=${vo.no }&type=1">${s.index+1 }.&nbsp;${vo.goods_name }</a></td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </div>
  <div style="height: 30px;"></div>
  <div class="row">
    <p>Made By <a href="" title="Visit w3schools">쌍용 강남 교육센터 3강의장</a></p>
  </div>
</footer>
</body>
</html>