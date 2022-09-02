<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 100%;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
      <c:forEach var="vo" items="${list }">
        <div class="col-md-3">
          <div class="thumbnail">
            <a href="goods_all_detail.do?no=${vo.no }">
              <img src="${vo.poster }" alt="Lights" style="width:100%">
              <div class="caption">
                <p>${vo.name }</p>
                <p>${vo.price }</p>
              </div>
            </a>
          </div>
        </div>
      </c:forEach>
    </div>
    <div style="height: 20px;"></div>
    <div class="row">
      <div class="text-center">
		<ul class="pagination">
		
		  <c:if test="${startPage>1 }">
		    <li><a href="goods_all.do?page=${startPage-1 }">&lt;</a></li>
		  </c:if>
		  
		  <c:forEach var="i" begin="${startPage }" end="${endPage }">
		    <c:if test="${curpage == i }">
		      <c:set var="style" value="class=active"/>
		      <li ${style }><a href="goods_all.do?page=${i }">${i }</a></li>
		    </c:if>
		    <c:if test="${curpage != i }">
		      <c:set var="style" value=""/>
		      <li ${style }><a href="goods_all.do?page=${i }">${i }</a></li>
		    </c:if>
		  </c:forEach>
		  
		  <c:if test="${endPage<totalpage }">
		    <li><a href="goods_all.do?page=${endPage+1 }">&gt;</a></li>
		  </c:if>
		  
		</ul>
      </div>
    </div>
  </div>
</body>
</html>