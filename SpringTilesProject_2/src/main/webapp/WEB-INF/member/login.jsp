<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.row1{
	margin: 0px auto;
	width: 420px;
	height: 800px;
}
</style>
</head>
<body>
<%--
	VueJS 	생명주기
				beforeCreate
				created
				=============== 메모리에 올라가는 상태 (가상메모리) (mount)
				beforeMount
				mounted			=> window.onload ( $(function()랑 같음 )
				===============
				beforeUpdate
				updated
				beforeDestroy
				destroyed
				------------------
			가상돔 : 더블 버퍼링 (가상 메모리)
			MVVM
			
	VueJS / React 공통점 차이점
		공통점 => dom형식
		vue3 	=> react와 거의 동일 (vuex, vue-cli)
		
		우리는 vue2
		
	React 	생명주기
				componentWillCreate
				componentDidCreate
		= 디렉티브
			v-()
				제어문
					v-for
					v-if
					v-else
					v-show
				연결
					v-model ==> :을 생략할 수 있다
				이벤트
					v-on:click, v-on:change
		= 컴포넌트 : 재사용
		
	형식)
		<script>
		  = 여러개 사용 (제어하는 위치가 틀릴경우)
		  new Vue({
		    el:=> class:. , id:# , '.table' => 제어할 수 있는 영역
		    data:{
		      멤버변수 : Vue에서 제어하는 변수
		      a:[] ==> 배열 (스프링 => ArrayList=>JSONArray)
		      b:{} ==> 객체 (스프링 => VO => JSONObject)
		      c:true ==> boolean
		      d:'' ==> 문자
		      e:0 ==> 정수형
		    }
		    생명주기 함수
		    mounted:function(){}
		    사용자 정의 함수 (이벤트 처리)
		    methods:{
		    
		    }
		    필터
		    filters:{
		    
		    }
		  })
		</script>
	
	AJAX : 단방향, Vue : 양방향
	
	=> 데이터 화면 출력 => {{변수명}}
		한번에 출력 => {{$data}}
			<img src="{{poster}}"> => 오류
			<img :src="poster">
--%>
  <div class="container">
    <div class="row row1">
      <form method="post" action="../member/login_ok.do">
      <table class="table">
        <caption><h3 class="text-center">Login</h3></caption>
        <tr>
          <td width="30%" class="text-right">ID</td>
          <td width="70%">
            <input type="text" name="id" size="15" class="input-sm">
          </td>
        </tr>
        <tr>
          <td width="30%" class="text-right">PWD</td>
          <td width="70%">
            <input type="password" name="pwd" size="15" class="input-sm">
          </td>
        </tr>
        <tr>
          <td colspan="2" class="text-center">
            <input type="submit" class="btn btn-sm btn-danger" value="로그인">
          </td>
        </tr>
      </table>
      </form>
    </div>
  </div>
</body>
</html>