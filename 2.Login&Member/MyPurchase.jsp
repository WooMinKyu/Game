<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    filter: brightness(50%);
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
    #tohome {
        color:#9b9b9b;
    }
    #tohome:hover {
        color:white;
    }
    #Bttn2 {
    	border:1px solid grey;
		color:white;
		border-radius:4px;
		padding: 12px 0px;
		margin-top:5px;
		font-size: 16px;
		background-color:transparent;
		width:150px;
    }
    #Bttn2:hover {
		background-color:grey;
    }
    
</style>
<body>
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	
	<div class="col-md-10"> <!-- Main (※90%)-->
		<center>
		<h1>My Purchase List</h1>
		<div style="margin-top:30px"></div>
		
		<table width="80%" border="0">
			<tr>
				<td style="padding:10px">
					<div style="text-align:left;"><button onClick="location.href='/Game/CsList.do'" id="Bttn2">My Page</button></div>			
				</td>
				<td style="padding:10px">	
					<div style="text-align:right;"><h4>Total Items : ${count}</h4></div>
				</td>
			</tr>
			<tr><td colspan="2" style="border-bottom:1px solid white;"></td></tr>
		</table>
			<c:if test="${payList.isEmpty()}">
			<div style="padding:110px 0;">
					<h1>구매한 상품이 없습니다.</h1>
					<h4 style="margin-top:50px"><a id="tohome" href="/Game/StoreList.do?search=기본&pageNum=1">게임 구매하러가기</a></h4>
			</div>
			</c:if>
			<div style="width:80%">
			<c:forEach var="payList" items="${payList}"> 
				<div style="width:25%; height:auto; padding:10px;float:left" class="itemlinker">
					<a href="/Game/PurDetail.do?pur_num=${payList.pur_num}"><img src="/Game/img/items/${payList.pur_img}.jpg" width="100%" height="auto"/></a>
			</div>
			</c:forEach>
			</div>
			
			<table width="75%" border="0">
				<tr>
					<td style="padding-top:50px; text-align:center;">			
						<c:if test="${pgList.startPage > pgList.blockSize}">
		     				<a href="/Game/MyPurchase.do?pageNum=${pgList.startPage-pgList.blockSize}"><span style="color:white;">Prev</span></a>
						</c:if>
						<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
		     				<a href="/Game/MyPurchase.do?pageNum=${i}">
			           		<c:if test="${pgList.currentPage==i}">
			                   <span style="display:inline-block;background-color:#ccc;border-radius:10px;width:20px;color:black;"><b> ${i}</b></span>
			           		</c:if>
			           		<c:if test="${pgList.currentPage!=i}">
			                   <span style="color:white;">${i}</span>
			           		</c:if>
			     			</a>	
						</c:forEach>
							<c:if test="${pgList.endPage <pgList.pageCount}">
		      				<a href="/Game/MyPurchase.do?pageNum=${pgList.startPage+pgList.blockSize}"><span style="color:white;">Next</span></a>
		 				</c:if>
					</td>
				</tr>
			</table>
	</center>
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