<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	.wish table{
	text-align:center;
	}
	.title{
	margin-bottom:25px;
	margin-left:65px;
	}
	.thum img{
	margin-right:25px;
	width:120%;
	height:auto;
	}
	.odd {
	background-color:#1A1A1A;
	}
	#tohome {/* 게임 구매하러가기 글씨색 */
		color:#9b9b9b;
	}
	#tohome:hover {/* 게임 구매하러가기 마우스올렸을때 */
		color:white;
	}
	#Bttn {
		border:none;
		color:white;
		padding-top:5px;
		font-size: 16px;
		background-color:#3A3A3A;
    }
    #Bttn:hover {
		border:none;
    	color:white;
		background-color:red;
    }
	
	
</style>
<script>
function deleteWish(item_num) {
			
			$.ajax({
				url:'/Game/4.Payment/DeleteWish.jsp', 
				type:'post',   
				data:{item_num:item_num},
				success:function(args) {
					location.reload();
				}
			})
} 
</script>
<body>
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	
	<div class="col-md-10"> <!-- Main (※90%)-->
		<div class="wish" style="margin-top:20px">			
		<center>
			<table width="90%">
				<tr>
					<td><h1 style="float:left">나의 위시리스트</h1</td>
				</tr>
				<tr><td style="border-bottom:1px solid white;">&nbsp;</td></tr>
			</table>
		</center>
			<center>
				<c:if test="${wishList==null }">
					<div style="margin-top:200px"></div>
					<h1>위시리스트에 상품을</h1>
					<h1>추가해주세요.</h1>
					<h4 style="margin-top:50px"><a id="tohome" href="/Game/StoreList.do?search=기본&pageNum=1">게임 구매하러가기</a></h4>
				</c:if>
				
				<div id="cart">
					<c:forEach var="wishList" items="${wishList}">
						<table class="odd" width=70% style="margin-top:20px;">
							<tr >
								<td width="20%" style="padding:20px"><div class="thum"><img src="/Game/img/items/${wishList.w_thum}.jpg"></div></td>
								<td width="25%">${wishList.w_name}</td>
								<td width="10%">${wishList.w_price}</td>
								<td width="20%">Discount : ${wishList.w_sale}%<br></td>
								<td width="10%"><img src="/Game/img/${wishList.grd_num}.png" width="50px"></td>
								<td width="15%">
								<a href="#" class="btn btn-warning" onclick="deleteWish('${wishList.item_num}','${sessionScope.m_id}')"><span class="glyphicon glyphicon-trash"></span></a></td>
							</tr>
						</table>
					</c:forEach>
				</div>
				</center>
		</div> <!-- div.wish -->
		<div style="margin-top:500px"></div> <!-- 하단여백설정 -->
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