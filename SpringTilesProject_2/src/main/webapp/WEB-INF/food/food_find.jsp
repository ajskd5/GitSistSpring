<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	height: 800px;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
      <form method="post" action="../food/food_find.do">
        맛집 : <input type="text" name="ss" size="20" class="input-sm" value="${ss }"><%-- SearchString:ss --%>
        <input type="submit" value="검색" class="btn btn-sm btn-primary">
      </form>
    </div>
    
    <div style="height: 50px;"></div>
    <div class="row">
      <c:forEach var="vo" items="${list }">
        <div class="col-md-4">
          <div class="thumbnail">
            <a href="#">
              <img src="${vo.poster }" alt="Lights" style="width:100%">
              <div class="caption">
                <p>${vo.name }</p>
              </div>
            </a>
          </div>
        </div>
      </c:forEach>
    </div>
    <div style="height: 20px;"></div>
    <div class="row">
      <div class="text-center">
        <a href="../food/food_find.do?ss=${ss }&page=${curpage>1?curpage-1:curpage}" class="btn btn-sm btn-danger">이전</a>
          ${curpage } page / ${totalpage } pages
        <a href="../food/food_find.do?ss=${ss }&page=${curpage<totalpage?curpage+1:curpage}" class="btn btn-sm btn-success">다음</a>
      </div>
    </div>
    
  </div>
</body>
</html>