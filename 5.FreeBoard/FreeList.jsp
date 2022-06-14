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
	.pagetext span{
		margin-right:15px;
	}
	.pBtn {
		padding:5px 10px 5px 10px;
		color:white;
		font-weight:bold;
		border:1px solid grey;
		border-radius:5px;
		background-color:#2b2b2b;
	}
	.pBtn:hover {
		border:none;
		border:1px solid grey;
		background-color:grey;
	}
</style>
<script>
$(function() {
	$('#writebtn').click(function() {
		location.href="/Game/FreeWriteForm.do";
	})
})
</script>
<body>
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	
	<div class="col-md-10"> <!-- Main (※90%)-->
	<center>
	
	<!-- Board -->
	<table border="0" width="90%">
		<tr>
			<td>&nbsp;</td>
			<td>
				<table width="100%"> <!-- Top area -->
					<tr>
						<td colspan="2"><h3>Community Board</h3></td>
					</tr>
					<tr>
						<% if(session.getAttribute("m_id") != null || session.getAttribute("ad_id") != null) {%>
						<td style="float:right; margin-right:10px"><button onclick="location.href='/Game/FreeWriteForm.do'">Write</button></td>
						<%}%>
					</tr>
					<tr><td style="border-bottom:1px solid grey;">&nbsp;</td></tr>
				</table>
				<div style="margin-bottom:20px"></div> <!-- 게시판을 상단에 고정 시켜주는 여백 -->
			</td>
			<td>&nbsp;</td>
		</tr>
		<!-- 상단영역 -->
		<tr>
			<td width="180px">&nbsp;</td> <!-- 왼쪽 여백 -->
		
			<td valign="top"> <!-- 센터 게시판 테이블 --> <!-- 한페이지 당 15개 게시글  -->
			<!--데이터의 유무  -->
		<div class="board">
		<c:if test="${pgList.count==0}">
			<table border="1" width="700" cellpadding="0" cellspacing="0" align="center">
			   <tr>
			       <td align="center">게시판에 저장된 글이 없습니다.</td>
			   </tr>
			</table>
		</c:if>
		<c:if test="${pgList.count > 0}"> 
			<table border="1" width="1000px" cellpadding="0" cellspacing="0" align="center" style="border:1px solid grey"> 
   				<tr height="40px" bgcolor="#181818"> 
				     <td align="center" width="9%"><b>No</b></td> 
				     <td align="center" width="50%"><b>Summary</b></td> 
				     <td align="center" width="15%"><b>Writer</b></td>
				     <td align="center" width="16%"><b>Date</b></td>  
				     <td align="center" width="10%"><b>View</b></td>  
   				</tr>

				<!-- 실질적으로 레코드를 출력시켜주는 부분  -->
				<c:set var="number" value="${pgList.number}" />
				<c:forEach var="article" items="${articleList}">
					<tr height="40px">
                       <td align="center" style="background-color:#706F6F">
                           ${article.com_num}
                       </td>
                       <td style="padding-left:10px; background-color:#706F6F;">
                           <a href="/Game/FreeContent.do?com_num=${article.com_num}&pageNum=${pgList.currentPage}" style="color:white">${article.com_sub}</a>
                       </td>
                       <td align="center" style="background-color:#706F6F"> 
                              ${article.com_nick}
                          </td>
                       <td align="center" style="background-color:#706F6F">
                           ${article.com_wdate}
                       </td>
                       <td align="center" style="background-color: #706F6F">${article.com_view}</td>
                     </tr>
				</c:forEach>
				</table>
				</c:if>
			</div><br><br>
		
			<div> <!-- Bottom -->
				<div style="margin-top:20px"></div> <!-- 게시판과의 거리 -->
				<center>
				<!-- 페이징 처리 -->
					<div class="pagetext">
					<c:if test="${pgList.startPage > pgList.blockSize}">
						<button onClick="location.href='/Game/FreeList.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}'" class="pBtn">&gt</button>
						
					</c:if>
					<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
						<a href="/Game/FreeList.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
						<c:if test="${pgList.currentPage==i}">
							<button onClick="location.href='/Game/FreeList.do?pageNum=${i}&search=${search}&searchtext=${searchtext}'" style="background-color:white; color:black" class="pBtn">${i}</button>		
						</c:if>
						<c:if test="${pgList.currentPage!=i}">
							<span style="color:white;">${i}</span>
							<button onClick="location.href='/Game/FreeList.do?pageNum=${i}&search=${search}&searchtext=${searchtext}'" class="pBtn">${i}</button>
							
						</c:if>
						</a>
					</c:forEach>
					<c:if test="${endPage < pageCount}">
						<button onClick="location.href='/Game/FreeList.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}'" class="pBtn">&lt</button>
					</c:if>
					</div>
				</center>
			</div> <!-- /bottom -->

				<%--</c:if> --%>
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