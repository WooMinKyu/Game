<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>로그인</title>
    <!-- 부트스트랩 -->
    <link href="/Game/css/bootstrap.min.css" rel="stylesheet">
    <!-- 구글아이콘 -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
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
    /* 로그인 */
	.container {
		width: 430px;
		line-height: 50px;
		margin: 40px auto;
	}
	
	h3 {
		text-align: center;
	}
	.login {
		width:390px;
		background-color: #3C5A91;
		color: white;
		border-radius: 10px;
		border: 0;
		padding: 10px 152px;
	}
	.login:hover {
	background-color: #59b1eb;
	border-radius: 10px;
	}

	p {
		text-align: center;
	}
	
	i {
		color: lightgray;
	}
	
	#imail {
		position: absolute;
		top: 175px;
		margin: 0 355px;
	}
	
	#ipw {
		position: absolute;
		top: 235px;
		margin: 0 355px;
	}
	
	input {
		border: 1px solid lightgray;
		border-radius: 3px;
		color:black;
	}
	button {
		width:380px;
		color:white;
		font-weight:bold;
		border:1px solid grey;
		border-radius:5px;
		background-color:#2b2b2b;
	}
	button:hover {
		border:none;
		border:1px solid grey;
		background-color:grey;
	}
</style>
<body onload="document.login_form.m_id.focus();">
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	<div class="col-md-10 wrap"> <!-- Main (※90%)-->
		<div style="margin-top:70px;"></div>
		<div class="container">
		<center>
			<div id="imail">
	            <i class="material-icons">person_outline</i>
	        </div>
	        <div id="ipw">
	            <i class="material-icons">lock_outline</i>
	        </div>
	        	<h3>관리자 로그인</h3>
        	<hr>
			<form name="login_form" method="post" action="/Game/AdLoginProc.do">
          	 		<div class="login_id">
               		<input type="text" name="ad_id" id="ad_id" placeholder="아이디" required style="height:30px; width: 380px" /><p>
               	</div>
           		<div class="login_pw">
               		<input type="password" name="ad_pw" id="ad_pw" placeholder="비밀번호" required style="height:30px; width: 380px" /><p>
           		</div>
           		<div style="margin-top:30px;"></div>
           		<button type="submit">Login</button>
   			</div>
   	 </form>
   	 <hr>
   	 </center>
   	</div>
	</div>
</div>
<div class="col-md-1"></div> <!-- Margin right -->
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="/Game/js/bootstrap.min.js"></script>
</body>
<footer>
<jsp:include page="/1.Main/Footer.jsp" /> <!-- Footer -->
</footer>
</html>