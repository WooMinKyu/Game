<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cart</title>
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
	#cartbuy {
		border:1px solid #5bb85d;
		color:#5bb85d;
		border-radius:4px;
		padding: 12px 0px;
		font-size: 18px;
		background-color:transparent;
		width:100%;
	}
	#cartbuy:hover {/* 구매,취소 버튼에 마우스 올릴때 */
		color:#2b4e2c;
		border:1px solid #2b4e2c;
	}
	#tohome {/* 게임 구매하러가기 글씨색 */
		color:#9b9b9b;
	}
	#tohome:hover {/* 게임 구매하러가기 마우스올렸을때 */
		color:white;
	}
	
</style>
<script>
$(function() {
	$('#cartbuy').click(function() {
		if(confirm('구매하시겠습니까?')) {
			location.href="/Game/CartPay.do"	
		}else {
			return false;
		}
	})	
}) 

function deleteCart(item_num) {
	

			$.ajax({
				url:'/Game/4.Payment/DeleteCart.jsp', 
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
						<td><h1 style="float:left">나의 장바구니</h1</td>
					</tr>
					<tr><td style="border-bottom:1px solid white;">&nbsp;</td></tr>
				</table>
			</center>
				<center>
				<div style="width:90%; margin-top:50px;">
					<div style="width:25%; float:right">
					<c:if test="${cartList!=null }">
						<table> <!-- 위시리스트 추가 시, 1개씩 추가 발생 -->
							<tr>
								<td colspan="2"><h2 style="text-align:left;margin-bottom:50px;">게임가격 합계</h2></td>
							</tr>
							<tr>
								<td align="left">가격</td>
								<td>${st_sum}</td>
							</tr>
							<tr><td><br></td></tr>
							<tr>
								<td align="left">할인</td>
								<td style="color:gray">- ${st_dissum}</td>
							</tr>
							<tr><td colspan="2"><hr style="border-color:gray"></td></tr>
							<tr>
								<td align="left">소계</td>
								<td>${st_total}</td>
							</tr>
							<tr>
								<td colspan="2" style="padding-top:30px;">
								<button id="cartbuy" onclick="cartBuy(${cartList==null })">구매하기</button>
								</td>
							</tr>
						</table>
					</c:if>
					</div>
				<c:if test="${cartList==null }">
					<div style="margin-top:200px"></div>
					<h1>장바구니에 상품을</h1>
					<h1>추가해주세요.</h1>
					<h4 style="margin-top:50px"><a id="tohome" href="/Game/StoreList.do?search=기본&pageNum=1">게임 구매하러가기</a></h4>
				</c:if>
				
				<div id="cart">
					<c:forEach var="cartList" items="${cartList}">
						<table class="odd" width=70% style="margin-top:20px;float:left">
							<tr >
								<td width="20%" style="padding:25px"><div class="thum"><img id="thum" src="/Game/img/items/${cartList.ct_thum}.jpg"></div></td>
								<td width="25%">${cartList.ct_name}</td>
								<td width="10%">${cartList.ct_price}</td>
								<td width="20%">Discount : ${cartList.ct_sale}%</td>
								<td width="10%"><img src="/Game/img/${cartList.grd_num}.png" width="50px"></td>
								<td width="15%" style="padding:5px">
								<button onClick="deleteCart('${cartList.item_num}')" class="btn btn-warning"><span class="glyphicon glyphicon-trash"></span></button>
								</td>
								<%-- <a href="#" onclick="deleteCart('${cartList.item_num}')">삭제</a> --%>
							</tr>
						</table>
					</c:forEach>
				</div>
				<!-- <div style="margin-left:40px;">
				<table width=65% style="float:left"> 위시리스트 추가 시, 1개씩 추가 발생
					<tr class="odd"> 정보영역
						<td width="20%"><div class="thum"><img src="../img/thum3.jpg"></div></td>
						<td width="25%">Diablo 3 : MAxtypingletter</td>
						<td width="10%">50,000</td>
						<td width="20%">Discount : 0%<br>2022/01/01 - 2023/01/01</td>
						<td width="10%"><img src="../img/g_18.png" width="50px"></td>
						<td width="15%">
							<a href="Refund.jsp" class="btn btn-success">위시리스트로 이동</a>
							<a href="Refund.jsp" class="btn btn-warning">삭제</a>							
						</td>
					</tr>
				</table> -->
				
				
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