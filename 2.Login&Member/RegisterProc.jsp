<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.*"%>
<%
request.setCharacterEncoding("utf-8");
	MemberDTO regBean=(MemberDTO)request.getAttribute("regBean");
	System.out.println("m_id=>"+regBean.getM_id());
%>
<!DOCTYPE html>
<html lang="ko">
<jsp:useBean id="mem" class="member.MemberDTO" />
<jsp:setProperty name="mem" property="*" />
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Main</title>
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
    /* 회원정보 확인 */
    .container {
    width: 470px;
    margin: 40px auto;
    line-height: 16px;
    }
	#signup,#reset {
    background-color: #3C5A91;
    color: white;
    border: 0;
    border-radius: 5px;
    padding: 20px 0px;
    width:430px;
    }
    #join > div {
    background:#303030;
    width:530x;
    height:50px;
    padding-top:15px;
    font-size:20px;
    }
    #join > div > div {
    background-color: #404040;
    width:120px;
    border-radius: 10px;
    float:left;
    height:50px;
    margin-top:-15px;
    padding-top:15px;
    font-size:15px;
    }
  </style>
  <body>
  <jsp:include page="/1.Main/Top.jsp" />
   	<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	<div class="col-md-10 wrap"> <!-- Main (※90%)-->
    <div class="container"><center>
        <h3>회원 가입 확인</h3>
        <hr /><br />
        <form id="join" name="join" method="post" action="MemberInsert.do">
        	<div><div>아이디</div>
        		<jsp:getProperty name="mem" property="m_id" /></div><br /><br />
            <div><div>비밀번호</div>
            	<jsp:getProperty name="mem" property="m_pw" /></div><br /><br />
            <div><div>비밀번호 힌트</div>
            	<jsp:getProperty name="mem" property="m_hint" /></div><br /><br />
            <div><div>이름</div>
            	<jsp:getProperty name="mem" property="m_name" /></div><br /><br />
            <div><div>닉네임</div>
            	<jsp:getProperty name="mem" property="m_nick" /></div><br /><br />
            <div><div>전화번호</div>
            	<jsp:getProperty name="mem" property="m_mobile" /></div><br /><br />
            <div><div>생년월일</div>
            	<jsp:getProperty name="mem" property="m_birth1" />년
				<jsp:getProperty name="mem" property="m_birth2" />월
				<jsp:getProperty name="mem" property="m_birth3" />일</div><br /><br />
            <p>
            <input type="submit" value="확인완료" id="signup" /><br /><br />
            <input type="reset" value="다시입력" id="reset" onclick="history.back()"/><br /><br />
            </p>
        </form>
        <hr /></center>
    </div>
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