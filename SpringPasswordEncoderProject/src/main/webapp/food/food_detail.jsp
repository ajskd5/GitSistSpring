<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
  <div class="container">
    <div class="row">
      <table class="table">
        <tr>
          <td v-for="img in food_detail.poster.split('^')" class="text-center">
            <img :src="img" style="width: 100%">
          </td>
        </tr>
      </table>
    </div>
  </div>
  <script>
    new Vue({
    	el : '.container',
    	data : {
    		// Controller에서 model로 보내줌
    		fno : ${fno},
    		food_detail : {}
    	},
    	mounted:function(){
    		// 카카오 등록
    	}
    })
  </script>
</body>
</html>