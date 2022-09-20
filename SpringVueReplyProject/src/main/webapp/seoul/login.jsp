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
	margin-top: 30px;
}
.row{
	margin: 0px auto;
	width: 420px;
}
.images:hover{
	cursor: pointer
}
</style>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
  <div class="container">
    <h1 class="text-center">Login</h1>
    <%--
    	1. 비밀번호 암호화 / 복호화
    	2. 권한 부여
    	3. 자동 로그인
    	4. 메소드 보안
    	5. Task
    	6. Validation
    --%>
    <div class="row">
      <table class="table">
        <tr>
          <th width="30%" class="text-right">ID</th>
          <td width="70%">
            <input type="text" size="15" ref="id" class="input-sm" v-model="id">
          </td>
        </tr>
        <tr>
          <th width="30%" class="text-right">PWD</th>
          <td width="70%">
            <input type="password" size="15" ref="pwd" class="input-sm" v-model="pwd">
          </td>
        </tr>
        <tr>
          <td class="text-center" colspan="2">
            <input type="button" value="로그인" class="btn btn-sm btn-danger" @click="login()">
            <input type="button" value="회원가입" class="btn btn-sm btn-primary" @click="join()">
          </td>
        </tr>
      </table>
    </div>
  </div>
  <%--
  	Vue, React => 자바스크립트 (문법, 데이터, 함수)
  	자바스크립트
  		연산자 => 비교연산자 ===, !==
  		제어문 => map, forEach ==> for문
  		함수
  			function func_name(매개변수...){} => 매개변수는 이름만 (let a) ==> a
  			let func_name = function(매개변수){}
  			let function=>(){}
  						리턴형 / function을 제거
  --%>
  <script>
    new Vue({
    	el:'.container',
    	data:{
    		id:'',
    		pwd:'',
    		res:''
    	},
    	methods:{
    		// login(){} 이렇게 써도 됨
    		login:function(){
    			if(this.id.trim()===""){
    				this.$refs.id.focus();
    				return;
    			}
    			if(this.pwd.trim()===""){
    				this.$refs.pwd.focus();
    				return;
    			}
    			//데이터 전송 (스프링 서버) => 전송 / 응답
    			let _this = this;
    			axios.get("http://localhost:8080/web/member/login_vue.do", {
    				params:{
    					id:_this.id,
    					pwd:_this.pwd
    				}
    			}).then(function(result){ // callback(시스템에 의해 자동 호출)
    				_this.res = result.data;
    			
    				if(_this.res === 'NOID'){
    					alert("존재하지 않는 아이디입니다!!");
    					_this.id = "";
    					_this.pwd = "";
    					_this.$refs.id.focus();
    				} else if(_this.res === 'NOPWD'){
    					alert("비밀번호가 틀립니다!!");
    					_this.pwd = "";
    					_this.$refs.pwd.focus();
    				} else{
    					location.href="../seoul/list.do";
    				}
    			})
    		}
    	}
    })
  </script>
</body>
</html>