<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Index</title>

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
  		border:0px solid red;
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
	.form-control{
	border-radius:0;
	}
	textarea {
	resize:none;
	}
	
</style>
<script language="JavaScript" src="writescript.js"></script>
<body>
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	
	<div class="col-md-10"> <!-- Main (※90%)-->
	<center>

	<!-- Board -->
	<table width="90%" border="0"> <!-- Middle Area -->
		<tr>
			<td>&nbsp;</td>
			<td style="border-bottom:1px solid grey;">
				<table width="1000px"> <!-- Top area -->
					<tr>
						<td colspan="2"><h3>Comment Board</h3></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- 상단영역 -->
		<tr>
		
			<td width="180px"></td> <!-- 왼쪽 여백 -->
		
			<td valign="top"> <!-- 센터 게시판 테이블 --> <!-- 한페이지 당 15개 게시글  -->
			<!--데이터의 유무  -->
			<div class="board">
				<form method="post" name="writeform" action="/Game/CmWriteProc.do" onsubmit="return writeSave()">
				   
				      <!-- 입력하지 않고 매개변수로 전달해서 테이블에 저장(hidden) 5개 --> 
				      <%-- <input type="hidden" name="com_num" value="${com_num}" ><!-- ?? --> --%>
				     <input type="hidden" name="ad_id" value="${sessionScope.ad_id}" >
				     <input type="hidden" name="cs_num" value="${cs_num}" >

				      
				<table width="1000px" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<td style="padding:10px" colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td style="padding:10px" colspan="2">
							<textarea rows="30" cols="137" style="color:black;" name="cm_content"></textarea>
						</td>
					</tr>
					<tr>
						<td width="10%" style="padding:10px; margin-bottom:2;">
							<input type="button" class="btn btn-default" value="&nbsp;Browse&nbsp;">
						</td>
						<td width="90%" style="padding:10px">
							<input type="text" class="form-control" style="height:40px; margin-bottom:5px" placeholder="선택된 파일이 없습니다.">						
						</td>
					</tr>
				<tr>      
				<!-- a링크, action의 속성값, 이벤트처리를 통해서 이동하는 경우 전부 jsp > do로 변경해야함(모델2) -->
				 <td colspan=2 align="center">
				  <input type="submit" class="btn btn-default" value="Write" style="float:right; margin-top:5px; margin-right:5px;">
				</td></tr></table>    
				</form>      
			</div>
			<br><br>
				</td>
				<td width="180px" align="center" valign="top"><!-- AD area -->
					<a href="#"><img src="/Game/img/ad.png"></a>
				</td>
			</tr>
		</table> <!-- /Board -->
	</center>
	<div style="margin-top:200px"></div> <!-- 하단여백설정 -->
	</div> <!-- col-md-10 -->
	
	<div class="col-md-1"></div> <!-- Margin right -->
</div>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="/Game/js/bootstrap.min.js"></script>
</body>
<footer>
<jsp:include page="/1.Main/Footer.jsp" /> <!-- Footer -->
</footer>
</html>