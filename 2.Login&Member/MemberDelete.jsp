<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("utf-8");
	
  String m_id=(String)session.getAttribute("m_id");
  String m_pw=(String)session.getAttribute("m_pw");
  
  MemberDAO mDao=new MemberDAO();
  MemberDTO regBean=mDao.getMember(m_id);
  
  regBean.setM_id(m_id);
  regBean.setM_pw(m_pw);
%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>회원탈퇴</title>
	<script language="JavaScript" src="script.js?ver=1"></script>
    <!-- 부트스트랩 -->
    <link href="/Game/css/bootstrap.min.css" rel="stylesheet">

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
    /* Member(회원가입) */
    .wrap {
	width: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
	background: #2b2b2b;
	}
	.member {
	width: 500px;
	background: rgba(97, 97, 97, 1);
	border-radius: 10px;
	padding:0px 40px;
	}
	.mem_join {
	margin:50px 0px;
	}
	.form-group input {
	width: 100%;
	height: 30px;
	border-radius: 10px;
	margin-top: 10px;
	padding: 0px 20px;
	border: 1px solid lightgray;
	outline: none;
	}
	.member_table {
	width: 100%;
	}
	.form-group select {
	width: 100%;
	height: 30px;
	border-radius: 10px;
	padding: 0px 20px;
	border: 1px solid lightgray;
	outline: none;
	}
	.form-control:focus {
  	border-width: 5px;
  	}
	.member_button button {
	margin:20px 0px;
	width: 200px;
	height: 50px;
	border: 0;  
	outline: none;
	border-radius: 10px;
	background: #2b4478;
	letter-spacing: 2px;
	}
  </style>
  <body>
    <jsp:include page="/1.Main/Top.jsp" />
   	<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	<div class="col-md-10 wrap"> <!-- Main (※90%)-->
	<div style="margin-top:500px"></div> <!-- 하단여백설정 -->
		<form id="join" role="form" method="post" action="/Game/MemberDeleteProc.do">
    			<hr>
    			<center>
	    			<h2 class="mem_join">회원탈퇴</h2>
					<h3 style="color:red;">정말로 탈퇴하시겠습니까?</h3>
				</center>
				<input name="m_id" type="hidden" value="<%=regBean.getM_id()%>">

				<input name="m_pw" type="hidden" value="<%=regBean.getM_pw()%>" >
	
				<input name="m_nick" type="hidden" value="<%=regBean.getM_nick() %>" >
								
				<input name="m_status" type="hidden" value="<%=regBean.getM_status() %>" >
				
				<div class="member_button"><center>
					<button type="submit" class="btn btn-primary btn-lg" >확인</button>
					<button type="reset" class="btn btn-primary btn-lg" onclick="history.go(-2)">취소</button>
	    		</center></div>
	    		<hr>
			</form>
    	</div>		
		</div>
	<div class="col-md-1"></div> <!-- Margin right -->
</div>
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="/Game/js/bootstrap.min.js"></script>
  </body>
  <footer>
  	<jsp:include page="/1.Main/Footer.jsp" />
  </footer>
</html>