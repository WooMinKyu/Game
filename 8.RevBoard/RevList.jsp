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
  		border:1px solid red;
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
	
</style>
<body>
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	
	<div class="col-md-10"> <!-- Main (※90%)-->
	<center>
	<div class="board" style="margin-top:50px;"></div>
	
	<!-- Board -->

	<c:if test="${pgList.count==0}">
	<table border="1" width="700" cellpadding="0" cellspacing="0" align="center">
	   <tr>
	       <td align="center">게시판에 저장된 글이 없습니다.</td>
	   </tr>
	</table>
	</c:if>
	
	<c:if test="${pgList.count > 0}">

	
	
					<!-- 실질적으로 레코드를 출력시켜주는 부분  -->
	<c:set var="number" value="${pgList.number}" />
	<c:forEach var="article" items="${articleList}">
		<div style="padding:10px; background-color:#181818; color:#AFAFAF;"><div style="padding:10px 200px 10px 0px;"><h5 style="color:white;">${article.rev_content}</h5></div>
		<br><br>WRITER : ${article.rev_nick}<span style="float:right">DATE : ${article.rev_wdate}</span></div>
		<div style="margin-top:8px;"></div>
		
	</c:forEach>
	</table>
	</c:if>
			</div>
			<br><br>
					
			<div> <!-- Bottom -->
				<div style="margin-top:20px"></div> <!-- 게시판과의 거리 -->
				<center>
				<!-- 페이징 처리 -->
					<c:if test="${pgList.startPage > pgList.blockSize}">
						<a href="/Game/FreeList.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}">&lt&nbsp;&nbsp;&nbsp;</a>
					</c:if>
					<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
						<a href="/Game/FreeList.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
						<c:if test="${pgList.currentPage==i}">
							<font color="red"><b>[${i}]</b></font>
						</c:if>
						<c:if test="${pgList.currentPage!=i}">
							${i}
						</c:if>
						</a>
					</c:forEach>
					<c:if test="${endPage < pageCount}">
						<a href="/Game/FreeList.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}">&nbsp;&nbsp;&nbsp;></a>
					</c:if>
				</center>
			</div> <!-- /bottom -->
					
				<%--</c:if> --%>
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