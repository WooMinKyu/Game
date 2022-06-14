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
	button {
		padding:7px 30px 7px 30px;
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
<script language="JavaScript">           
function removeCheck() {
	 if (confirm("정말 삭제하시겠습니까??") == true){    //확인
	     return "/Game/HelpDeleteProc.do";
	 }else{   //취소
	     return false;
	 }
	}
function deleteSave(){	
	alert("삭제하겠습니다");
	return true;
 }
</script>
<body>
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	
	<div class="col-md-10"> <!-- Main (※90%)-->
	<center>
	
	<!-- Board -->
	<table width="90%" border="0">
		<tr>
			<td>&nbsp;</td>
			<td>
				<table width="100%"> <!-- Top area -->
					<tr>
						<td colspan="2"><h3>Help Board</h3><h5>Notification & FAQ</h5></td>
					</tr>
						<tr><td colspan="2">&nbsp;</td></tr>
					<tr>
						<td style="float:left; margin-left:10px"><button onclick="history.back()">List</button></td>
						<td style="float:right; margin-right:10px"><button onclick="location.href='/Game/HelpWriteForm.do'">Write</button></td>
					</tr>
					<tr><td style="border-bottom:1px solid grey;">&nbsp;</td></tr>
				</table>
			</td>
			<td>&nbsp;</td>
		</tr>
		<!-- 상단영역 -->
		<tr>
			<td width="180px">&nbsp;</td>
			<td>
				<table width="100%" cellspacing="0" cellpadding="0"  style="background-color:#181818; color:white;">
					<tr><!-- 글제목 -->
						<td colspan="2" style="width:900px; padding-left:10px;">
							<h2>${article.help_sub}</h2>
						</td>
					</tr>
					<tr><!-- 닉네임 -->
						<td style="padding:10px;">
							<h5>Writer : ${article.help_nick}</h5>
						</td>
						<td align="right" style="padding-right:30px;">
							Date : ${article.help_wdate} &nbsp;&nbsp;조회수 : ${article.help_view}
						</td>
					</tr>
					
					<tr><!-- 글내용 -->
						<td colspan="2" style="padding:10px; border-top:1px solid grey; border-bottom:1px solid grey;">
							<pre style="background-color:#E6E6E6; color:black; font-size:16px; height:400px; border:0px">${article.help_content}</pre>
						</td>
					</tr>
					<tr><td colspan="2" style="background-color:#2b2b2b;">&nbsp;</td></tr>
					<tr>
						<td colspan="2" style="background-color:#2b2b2b;">
							<span style="float:left; margin-left:10px"><button onclick="history.back()">List</button></span>
							<span style="float:right; margin-right:10px"><button onclick="location.href='/Game/HelpWriteForm.do'">Write</button></span>
							<span style="float:right; margin-right:5px"><button onclick="location.href='/Game/HelpDeleteProc.do?help_num=${help_num}'">Delete</button></span>
							<span style="float:right; margin-right:5px"><button onclick="location.href='/Game/HelpUpdateForm.do?help_num=${help_num}'">Edit</button></span>
						</td>
					</tr>
					<tr><td colspan="2" style="background-color:#2b2b2b;">&nbsp;</td></tr>	
					<tr><td colspan="2" style="border-bottom:1px solid grey; background-color:#2b2b2b;">&nbsp;</td></tr>
				
				</table>
			</td>
			<td width="180px" align="center" valign="top"><!-- AD area -->
					<a href="#"><img src="/Game/img/ad.png"></a>
			</td>
		</tr>
		
		
		
		
	</table> <!-- /Board -->
	</center>
	<div style="margin-top:50px"></div> <!-- 하단여백설정 -->
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