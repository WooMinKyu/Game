<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>회원가입</title>
    <!-- 부트스트랩 -->
    <link href="/Game/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script language="javascript">
    
  		function inputCheck() {
  			
  			var m_id = document.getElementById("m_id");
  			var m_pw = document.getElementById("m_pw");
  			var m_pwchk = document.getElementById("m_pwchk");
  			var m_hint = document.getElementById("m_hint");
  			var m_name = document.getElementById("m_name");
  			var m_mobile = document.getElementById("m_mobile");
  			
  			var m_idCheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
  			
  			if(join.m_id.value==""){
  				alert("아이디를 입력해 주세요");
  				document.join.m_id.focus();
  				return false;
  			}		
  			if(!m_idCheck.test(join.m_id.value)){
  				alert("이메일 형식에 맞지 않는 아이디입니다");
  				document.join.m_id.focus();
  				return false;
  			}
  			
  			var m_pwCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=-])(?=.*[0-9]).{8,20}$/;
  			
  			if(join.m_pw.value==""){
  				alert("비밀번호를 입력해 주세요");
  				document.join.m_pw.focus();
  				return false;
  			}		
  			if(!m_pwCheck.test(join.m_pw.value)){
  				alert("영문,숫자,특수기호 포함하여 8~20자리를 입력해주세요");
  				document.join.m_pw.focus();
  				return false;
  			}
  			
  			if(join.m_pwchk.value==""){
  			alert("비밀번호 확인을 해주세요");
  			document.join.m_pwchk.focus();
  				return false;
	  		}
  			if(join.m_pwchk.value!=join.m_pw.value){
	  			alert("비밀번호가 일치하지 않습니다");
	  			document.join.m_pwchk.focus();
	  			return false;
	  		}
  			
	  		if(join.m_hint.value==""){
	  			alert("비밀번호 힌트를 입력해 주세요");
	  			document.join.m_hint.focus();
	  			return false;
	  		}
	  		
	  		if(join.m_name.value==""){
	  			alert("이름을 입력해 주세요");
	  			document.join.m_name.focus();
	  			return false;
	  		}
	  		
	  		if(join.m_nick.value==""){
	  			alert("닉네임을 입력해 주세요");
	  			document.join.m_nick.focus();
	  			return false;
	  		}
	  		
	  		var m_mobileCheck = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
	  		
	  		if(join.m_mobile.value==""){
	  			alert("휴대폰번호를 입력해 주세요");
	  			document.join.m_mobile.focus();
	  			return false;
	  		}
	  		if(!m_mobileCheck.test(join.m_mobile.value)){
	  			alert("'-'를 포함하여 13자리의 휴대폰번호를 입력해주세요'");
	  			document.join.m_mobile.value.focus();
	  			return false;
	  		}
	  		document.join.submit();
  		}
  		
  		function idCheck(m_id){
  		   if(m_id==""){
  				alert("아이디를 먼저 입력하세요");
  				document.join.m_id.focus();//document.폼객체명.입력양식객체명.함수명(~)
  		   }else{//입력했다면 jsp파일에게 매개변수로 전달->checkId(id)
  				url="IdCheck.jsp?m_id="+m_id;//request.getParameter("mem_id");//null
  				window.screen.width
  				window.screen.height
  				var popupWidth=300;
  				var popupHeight=200;
  				var popupX=(window.screen.width / 2) - (popupWidth / 2);
  				var popupY=(window.screen.height / 2) - (popupHeight / 2);
  				window.open(url,"post",'status=no,height='+popupHeight+',width='+popupWidth+',left='+popupX+',top='+popupY);
  		   }
  		}	
  </script>
  <style>
      .col-xs-1, .col-sm-1, .col-md-1, .col-lg-1, 
      .col-xs-2, .col-sm-2, .col-md-2, .col-lg-2, 
      .col-xs-3, .col-sm-3, .col-md-3, .col-lg-3, 
      .col-xs-4, .col-sm-4, .col-md-4, .col-lg-4, 
      .col-xs-5, .col-sm-5, .col-md-5, .col-lg-5, 
      .col-xs-6, .col-sm-6, .col-md-6, .col-lg-6, 
      .col-xs-7, .col-sm-7, .col-md-7, .col-lg-7, 
      .col-xs-8, .col-sm-8, .col-md-8, .col-lg-8, 
      .col-xs-9, .col-sm-9, .col-md-9, .col-lg-9, 
      .col-xs-10, .col-sm-10, .col-md-10, .col-lg-10, 
      .col-xs-11, .col-sm-11, .col-md-11, .col-lg-11, 
      .col-xs-12, .col-sm-12, .col-md-12, .col-lg-12 {
          border:0px solid black;
          padding:10px;
    }
    body {
    background-color:#2b2b2b;
    }
    nav {
    padding-left:8px;
    padding-right:14px;
    }
    a, a:hover {
    text-decoration:none;
    }
    .rows{
        margin-bottom:4px;
        margin-top:4px;
        color:white;
    }
    .form-control{
    height:26px;
    }
    .at1 img {
    width:90%;
    height:auto;
    }
    .itemlinker a img {
    width:100%;
    height:auto;
    }
    .itemlinker a img:hover {
    width:100%;
    height:auto;
    border:1px solid #00ffd8;
    }
    .itemlinker table th {
    color:white;
    font-size:15pt;
    padding-left:12px;
    padding-right:12px;
    }
    .more, .more:hover {
    color:#aeaeae;
    font-size:12pt;
    }
    .more:hover {
    color:white;
    }
    .ft{
    clear:both;
    padding-top:50px;
    }
    .refund img{
    width:100%;
    height:auto;
    }
    /* 회원가입 */
    .container {
         width: 470px;
         margin: 40px auto;
         line-height: 16px;
    }
        h3 {
            text-align: center;
        }
        #signup,#reset {
            background-color: #3C5A91;
            color: white;
            border: 0;
            border-radius: 5px;
            padding: 20px 0px;
            width:430px;
        }
        i {
            color: lightgray;
        }
        #imail {
            position: absolute;
            top: 155px;
            margin: 0 290px;
        }
        #ipw {
            position: absolute;
            top: 210px;
            margin: 0 400px;
        }
        #ipwchk {
            position: absolute;
            top: 267px;
            margin: 0 400px;
        }
        #ihint {
            position: absolute;
            top: 325px;
            margin: 0 400px;
        }
        #iname {
            position: absolute;
            top: 380px;
            margin: 0 400px;
        }
        #inick {
            position: absolute;
            top: 435px;
            margin: 0 400px;
        }
        #imobile {
            position: absolute;
            top: 492px;
            margin: 0 400px;
        }
        input {
            border: 1px solid lightgray;
            border-radius: 3px;
            color:black;
        }
        #join > input {
        height:30px; 
        width: 430px;
        margin-bottom:10px;
        }
        #join > div > input {
         width:90px;
         height:30px;
         float:right;
         margin-top:-55px;
         margin-right:10px;
        }
        .btn1 {
		width:430px;
		height:50px;
		color:white;
		font-weight:bold;
		border:1px solid #5bb85d;
		border-radius:5px;
		background-color:#5bb85d;
	}
	.btn1:hover {
		border:none;
		border:1px solid green;
		background-color:green;
	}
	.btn2 {
		width:430px;
		height:50px;
		color:white;
		font-weight:bold;
		border:1px solid grey;
		border-radius:5px;
		background-color:#2b2b2b;
	}
	.btn2:hover {
		border:none;
		border:1px solid grey;
		background-color:grey;
	}
    </style>
</head>

  <body onload="document.join.m_id.focus();">
   <c:if test="${ joinResult == 0}">
			<script>
			alert("아이디가 중복됩니다.");
			</script>
	</c:if>
  <jsp:include page="/1.Main/Top.jsp" />
   	<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	<div class="col-md-10 wrap"> <!-- Main (※90%)-->
    <div class="container">
        <div id="imail">
            <i class="material-icons">person_outline</i>
        </div>
        <div id="ipw">
            <i class="material-icons">lock_outline</i>
        </div>
        <div id="ipwchk">
            <i class="material-icons">lock_outline</i>
        </div>
        <div id="ihint">
            <i class="material-icons">person_outline</i>
        </div>
        <div id="iname">
            <i class="material-icons">person_outline</i>
        </div>
        <div id="inick">
            <i class="material-icons">person_outline</i>
        </div>
        <div id="imobile">
            <i class="material-icons">person_outline</i>
        </div>
        <h3>회원 가입</h3>
        <hr /><br />
        <form id="join" name="join" method="post" action="/Game/RegisterProc.do">
            <input type="text" placeholder="아이디(이메일형식)" name="m_id" required style="width:325px;margin-bottom:25px;"/>
            <div><input type="button" value="ID중복확인" onClick="idCheck(this.form.m_id.value);" class="btn2"></div>
            <input type="password" placeholder="비밀번호(영문,숫자,특수기호 포함 8~25자리)" name="m_pw" required /><br /><br />
            <input type="password" placeholder="비밀번호 확인" name="m_pwchk" required /><br /><br />
            <input type="text" placeholder="비밀번호 힌트" name="m_hint" required /><br /><br />
            <input type="text" placeholder="이름" name="m_name" required /><br /><br />
            <input type="text" placeholder="닉네임" name="m_nick" required /><br /><br />
            <input type="text" placeholder="휴대폰번호('-'를 포함하여 13자리)" name="m_mobile" required /><br /><br />
            <div class="form-group" style="margin-bottom:25px;">
				<table class="member_table"> 
					<tr>
						<td>
						<select class="form-control" name="m_birth1" required style="height:30px; width: 140px;margin-right:5px;">
							<%for(int i=2015;i>=1915;i--){%>
							<option value="<%=i%>"><%=i%></option>
							<%}%></select></td>
						<td>
						<select class="form-control" name="m_birth2" required style="height:30px; width: 140px;margin-right:5px;">
							<%for(int i=1;i<=12;i++){%>
							<option value="<%=i%>"><%=i%></option>
							<%}%></select></td>
						<td>
						<select class="form-control" name="m_birth3" required style="height:30px; width: 140px;">
							<%for(int i=1;i<=31;i++){%>
							<option value="<%=i%>"><%=i%></option>
							<%}%></select></td>
					</tr>
				</table>
			</div>
            <p>
            <button type="button" class="btn1" onclick="inputCheck()">회원가입</button><br /><br />
            <button type="reset" class="btn2">다시입력</button><br /><br />
            </p>
        </form>
        <hr />
    </div>
</div>
<div class="col-md-1"></div> <!-- Margin right -->
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="/Game/js/bootstrap.min.js"></script>
  </body>
  <footer>
  	<jsp:include page="/1.Main/Footer.jsp" />
  </footer>
</html>