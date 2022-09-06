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
      <c:forEach var="vo" items="${list }">
        <div class="col-md-4">
	      <div class="thumbnail">
	        <a href="../goods/new_detail.do?no=${vo.no }">
	          <img src="${vo.goods_poster }" alt="Lights" style="width:100%">
	          <div class="caption">
	            <p>${vo.goods_name }</p>
	            <p>${vo.goods_price }</p>
	          </div>
	        </a>
	      </div>
	    </div>
	  </c:forEach>
    </div>
    <div class="row">
      <div class="text-center" style="margin-top: 20px; margin-bottom: 20px;">
        <a href="../goods/new.do?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-info">이전</a>
          ${curpage } page / ${totalpage } pages
        <a href="../goods/new.do?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-info">다음</a>
      </div>
    </div>
  </div>
</body>
</html>