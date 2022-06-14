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
    <script type="text/javascript"
			src="http://code.jquery.com/jquery-3.3.1.min.js"></script>

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
	select {
		padding:5px 20px 5px 20px;
		border:1px solid grey;
		background-color:#2b2b2b;
		color:white;
		font-weight:bold;
	}
	.textbox{
		border:1px solid grey;
		height:30px;
		background-color:#2b2b2b;
		color:white;
	}
	.textbtn{
		border:1px solid grey;
		height:30px;
		width:120px;
		background-color:#2b2b2b;
		color:white;
		font-weight:bold;
	}
	.textbtn:hover{
		background-color:grey;
	}
	#Bttn{
    	border:1px solid grey;
		color:white;
		border-radius:4px;
		padding: 8px;
		margin-top:5px;
		font-size: 12px;
		background-color:#2b2b2b;
		width:100px;
    }
    #Bttn:hover {
		background-color:grey;
    }
	
</style>
<script>

</script>
<body>
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	
	<div class="col-md-10"> <!-- Main (※90%)-->
	<center>
	<!-- Member Searching -->
	<table width="90%" style="text-align:center"> <!-- Top area -->
		<tr>
			<td colspan="2" style="color:white"><h1>Member List Page</h1><br><button onClick="location.href='/Game/AdMemberList.do'" class="textbtn" style="width:300px">View All Member List</button></td>
		</tr>
		<tr>
			<td style="margin-top:10px; padding:2px;">
			<form name="test" action="/Game/AdMemberList.do" style="float:right; color:black">
     			<select name="search">
     				<option value="m_id">ID</option>
     				<option value="m_nick">Nick</option>
     				<option value="m_mobile">Mobile</option>
     				<option value="m_birth">birth</option>
     				<option value="m_status">Status</option>
     			</select>
     			<input type="text" size="15" name="searchtext" class="textbox">&nbsp;
				<input type="submit" value="search" class="textbtn">
    		</form>
    		<td>	
		</tr>
	</table>
	<table border="1" width="90%">
		<tr>
			<td valign="top"> <!-- 센터 게시판 테이블 --> <!-- 한페이지 당 15개 게시글  -->
			<!--데이터의 유무  -->
			<div class="board">
			<table border="1" width="100%" cellpadding="0" cellspacing="0" align="center" style="border:1px solid grey"> 
   				<tr height="40px" bgcolor="grey"> 
				     <td align="center" width="10%"><b>ID</b></td>
				     <td align="center" width="10%"><b>PW</b></td>
				     <td align="center" width="10%"><b>HINT</b></td>
				     <td align="center" width="10%"><b>NAME</b></td>
				     <td align="center" width="10%"><b>NICK</b></td>
				     <td align="center" width="10%"><b>MOBILE</b></td>
				     <td align="center" width="10%"><b>BIRTH</b></td>
				     <td align="center" width="10%"><b>STATUS</b></td>
				     <td align="center" width="10%"><b>JOIN</b></td>
				     <td align="center" width="10%"><b>BTN</b></td> <!-- 상태변경 해주는 버튼 (메서드 처리) -->				       
   				</tr>
   			<c:if test="${pgList.count==0}"> <!-- 회원 정보 없을 때 -->
   				<tr>
			       <td colspan="10" align="center" style="height:500px">조회할 회원을 검색하세요.</td>
			   </tr>
   			</c:if>
   			<c:if test="${pgList.count > 0}"> <!-- 회원 정보 있을 때 -->
				<!-- 실질적으로 레코드를 출력시켜주는 부분  -->
				<c:set var="number" value="${pgList.number}" />
				<c:forEach var="article" items="${articleList}">
					<tr height="40px">
                   		<!-- 변경하는 폼 -->
                   		<form name="test" action="/Game/AdMemberUpdate.do">
	                   		<td align="center" style="background-color:#5E5E5E">${article.m_id}</td>                  
	                       	<td align="center" style="background-color:#5E5E5E">${article.m_pw}</td>                       
	                       	<td align="center" style="background-color:#5E5E5E">${article.m_hint}</td>                       
	                       	<td align="center" style="background-color: #5E5E5E">${article.m_name}</td>
	                       	<td align="center" style="background-color: #5E5E5E">${article.m_nick}</td>
	                       	<td align="center" style="background-color: #5E5E5E">${article.m_mobile}</td>
	                       	<td align="center" style="background-color: #5E5E5E">${article.m_birth}</td>
	                       	<td align="center" style="background-color: #5E5E5E; color:black">
	                       		<select name="m_status">
	                       		<c:if test="${article.m_status=='active'}">
	                       			<option value="active" selected>Active</option>
	                       			<option value="unactive">UnActive</option>
	                       			<option value="ban">Banned</option>
	                       		</select>
	                       		</c:if>
	                       		<c:if test="${article.m_status=='unactive'}">
	                       			<option value="active">Active</option>
	                       			<option value="unactive" selected>UnActive</option>
	                       			<option value="ban">Banned</option>
	                       		</select>
	                       		</c:if>
	                       		<c:if test="${article.m_status=='ban'}">
	                       			<option value="active">Active</option>
	                       			<option value="unactive">UnActive</option>
	                       			<option value="ban" selected>Banned</option>
	                       		</select>
	                       		</c:if>
	                       	</td>
	                       	<td align="center" style="background-color: #5E5E5E">${article.m_join}</td>
	                       	<td align="center" style="background-color: #5E5E5E; color:black"><input type="hidden" id="m_id" name="m_id" value="${article.m_id}"><button type="submit" id="Bttn">Submit</button></td>
                       	</form>
                       	<!-- 변경하는 폼 -->
                     </tr>
				</c:forEach>
				</c:if>
				</table>
			</div>
			</td><br><br>
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