<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	a, a:hover {
	padding-top:10px;
	text-decoration:none;
	color:#aeaeae;
	}
	a:hover {
	padding-top:10px;
	text-decoration:none;
	color:white;
	}
	.ft{
	clear:both;
	padding-top:50px;
	}
	.bord {
		color:white;
		border:1px solid white;
		background-color:#222222;
	}
	.bord:hover{
		background-color:black;
	}
	.botTb td{
		border:1px solid white;
	}
	.botTb a{
		color:white;
	}
	.botTb a:hover{
		color:black;
	}
	.form-control{
		width:500px;
		border-radius: 20px;
	}
	.search {
		position:relative;
		top:-35px;
		left:220px;
	}
	.search:hover{
		color:#66afe9;
	}
	.form-control{
		height:50px;
	}
	.quickBtn {
		color:white;
		background-color:#4A4A4A;
		border:1px solid #4A4A4A;
		border-radius: 20px;
	}
	.quickBtn:hover{
		background-color:#696868;
		border:1px solid white;
		border-radius: 20px;
	}
	button { /*top, right, bottom, left*/
		padding:12px 70px 12px 70px;
		color:white;
		font-weight:bold;
		border:1px solid grey;
		border-radius:5px;
		background-color:black;
	}
	button:hover {
		border:1px solid grey;
		background-color:grey;
	}
</style>
<body>
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
	<table width="100%" height="400px" style="background-color:black">
			<tr>
				<td style="width:200px">
					<center>
						<h1>5Games Support</h1>
						<h4>How can I help You sir?</h4><br>
							<form name="test" action="/Game/HelpList.do">
								<input type="hidden" name="search" value="help_sub">
								<input type="text" class="form-control" placeholder="Search 5Games.com" name="searchtext"><br>
								<button type="submit">Search</button>
				    		</form>		
					</center>
				</td>
			</tr>
	</table>
	<div class="col-md-1"></div> <!-- Margin left -->
	
	<div class="col-md-10"> <!-- Main (※90%)-->
	<div style="margin-top:50px"></div>
	<center>
	<h3>Recommendation</h3>
	<div style="margin-top:30px"></div>
	<table class="botTb" width="70%" style="background-color:grey">
		<tr style="height:45px">
			<td><a href="/Game/HelpContent.do?help_num=2&pageNum=1">&nbsp;<span class="glyphicon glyphicon-question-sign">&nbsp;</span>FAQ : Purchase List wrong Game added in My page.</a></td>
		</tr>
		<tr style="height:45px">
			<td><a href="/Game/HelpContent.do?help_num=5&pageNum=1">&nbsp;<span class="glyphicon glyphicon-question-sign">&nbsp;</span>FAQ : How Can I get a refund?</a></td>
		</tr>
		<tr style="height:45px">
			<td><a href="/Game/HelpContent.do?help_num=1&pageNum=1">&nbsp;<span class="glyphicon glyphicon-bell">&nbsp;</span>Notification : We need to change Brand new Web Site soon.</a></td>
		</tr>
		<tr style="height:45px">
			<td><a href="/Game/HelpContent.do?help_num=3&pageNum=1">&nbsp;<span class="glyphicon glyphicon-bell">&nbsp;</span>Notification : Some problem resolved on April 4th.</a></td>
		</tr>
		<tr style="height:45px">
			<td><a href="/Game/HelpContent.do?help_num=4&pageNum=1">&nbsp;<span class="glyphicon glyphicon-question-sign">&nbsp;</span>FAQ : Quick Start to 5Games Platform.</a></td>
		</tr>
	</table>
	</center>
	<div style="margin-top:20px"></div>
	<center>
	<div style="margin-top:50px"></div>
	<h3>Searching by Catagory</h3>
	<div style="margin-top:30px"></div>
	<div>
		<table class="midTb" style="width:700px">
			<tr>
				<td>
					<a href="/Game/HelpList.do?search=help_content&searchtext=0001"><div class="quickBtn" style="padding:50px"><img src="../img/qicon1.png" width="120px" height="120px"></div></a>
				</td>
				<td width="20px">&nbsp;</td>
				<td>
					<a href="/Game/HelpList.do?search=help_content&searchtext=0002"><div class="quickBtn" style="padding:50px"><img src="../img/qicon2.png" width="120px" height="120px"></div></a>								
				</td>
				<td width="20px">&nbsp;</td>
				<td style="margin-right:50px">
					<a href="/Game/HelpList.do?search=help_content&searchtext=0003"><div class="quickBtn" style="padding:50px"><img src="../img/qicon3.png" width="120px" height="120px"></div></a>							
				</td>
			</tr>
			<tr><td style="margin-top:50px">&nbsp;</td></tr>
			<tr>
				<td>
					<a href="/Game/HelpList.do?search=help_content&searchtext=0004"><div class="quickBtn" style="padding:50px"><img src="../img/qicon4.png" width="120px" height="120px"></div></a>
				</td>
				<td width="20px">&nbsp;</td>
				<td>
					<a href="/Game/HelpList.do?search=help_content&searchtext=0005"><div class="quickBtn" style="padding:50px"><img src="../img/qicon5.png" width="120px" height="120px"></div></a>								
				</td>
				<td width="20px">&nbsp;</td>
				<td>
					<a href="/Game/HelpList.do?search=help_content&searchtext=0006"><div class="quickBtn" style="padding:50px"><img src="../img/qicon6.png" width="120px" height="120px"></div></a>								
				</td>
			</tr>
		</table>
		<div style="margin-top:50px"></div>
		<h3>Searching by Boards</h3>
		<div style="margin-top:30px"></div>
		<table width="700px" class="topTb">
			<tr>
				<td style="width:335px; text-align:center">
					<a href="/Game/HelpList.do?search=help_sub&searchtext=Noti"><div class="bord" style="padding:80px; font-size:16px;">Notification Board</div></a>
				</td>
				<td style="width:30px;">&nbsp;</td>
				<td style="width:335px; text-align:center">
					<a href="/Game/HelpList.do?search=help_sub&searchtext=FAQ"><div class="bord" style="padding:80px; font-size:16px;">FAQ Board</div></a>
				</td>
			</tr>
		</table>
	</div>
	</center>
	<div style="margin-bottom:100px"></div>
	</div>
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