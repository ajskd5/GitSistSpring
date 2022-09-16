<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	width: 850px;
	margin: 0px auto;
}
</style>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
  <div class="container">
    <div class="row row1">
      <h1 class="text-center">VueJS를 이용한 게시판 목록 출력</h1>
      <table class="table">
        <tr>
          <td>
            <a href="../board/insert.do" class="btn btn-sm btn-primary">새글</a>
          </td>
        </tr>
      </table>
      <table class="table">
        <tr>
          <th width="10%" class="text-center">번호</th>
          <th width="45%" class="text-center">제목</th>
          <th width="15%" class="text-center">이름</th>
          <th width="20%" class="text-center">작성일</th>
          <th width="10%" class="text-center">조회수</th>
        </tr>
        <tr v-for="vo in board_list">
          <td width="10%" class="text-center">{{vo.no}}</td>
          <%-- Vue 링크 --%>
          <td width="45%"><a :href="'../board/detail.do?no='+vo.no">{{vo.subject}}</a></td>
          <td width="15%" class="text-center">{{vo.name}}</td>
          <td width="20%" class="text-center">{{vo.dbday}}</td>
          <td width="10%" class="text-center">{{vo.hit}}</td>
        </tr>
      </table>
      <table class="table">
        <tr>
          <td class="text-center">
            <input type="button" value="이전" class="btn btn-sm btn-danger">
              {{curpage}} page / {{totalpage}} pages
            <input type="button" value="다음" class="btn btn-sm btn-danger">
          </td>
        </tr>
      </table>
    </div>
  </div>
  <script>
    new Vue({
    	el:'.container',
    	data:{
    		board_list:[],
    		curpage:1,
    		totalpage:0
    	},
    	mounted:function(){ // mounted => 시작하자마자 동작
    		let _this=this; // => 안쓰는 방법
    		axios.get("http://localhost:8080/web/board/list_vue.do",{
    			params:{
    				page:_this.curpage
    			}
    		}).then(function(result){ // .then(function(result){ 이렇게 쓰려면 let _this=this; 이거 써야함
    			console.log(result.data);
    			_this.board_list = result.data;
    			_this.curpage = result.data[0].curpage;
    			_this.totalpage = result.data[0].totalpage;
    		})
    	}
    })
  </script>
</body>
</html>