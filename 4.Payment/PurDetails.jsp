<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="payment.PaymentDAO, payment.PayDTO" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
  		/* border:1px solid red;
  		padding:10px; */
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
	.tb table{
	text-align:center;
	}
	.tb2 table td{
	padding-left:15px;
	vertical-align:top;
	}
	.tb2 table h6{
	color:#353535;
	}
	button {
		padding:7px 15px 7px 15px;
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
<body>
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	
	<div class="col-md-10"> <!-- Main (※90%)-->
		<div class="details" style="margin-top:50px">
			<center>
			<table width=1000px style="border-bottom:1px solid white;"> <!-- Top -->
				<tr>
					<th colspan="6"><h3>결제상세정보</h3></th>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<th>결제일자</th>
					<th>${pur_date}</th>
					<%-- <%=regBean.getM_id()%> --%>
					<th>결제번호</th>
					<th>${pur_num}</th>
				</tr>
			</table>
			<br>
			<div class="tb">
				<table width=1000px border="1" bordercolor="#353535"> <!-- Mid -->
					<tr> <!-- 정보영역 -->
						<td colspan="2">상품정보</td>
						<td>상품금액</td>
						<td>판매자</td>
						<td>결제상태</td>
						<td>환불</td>
					</tr>
					<tr> <!-- 정보영역 -->
						<td width="200px" height="140px"><img src="/Game/img/items/${pur_thum}.jpg" width="199px" height="139px"></td>
						<td width="200px">${pur_name}</td>
						<td>${st_pur_price}</td>
						<td>Five games studio</td>
						<td>구매완료</td>
						<td><button onClick="location.href='/Game/RefundForm.do?pur_num=${pur_num}'">Refund</button></td>
					</tr>
				</table>
			</div>
			<table width=1000px>
				<tr>
					<td>결제상세 정보 100글자 정도 들어갑니다.</td>
				</tr>
				<tr>
					<td>&nbsp;</td> <!-- 여백 -->
				</tr>
				<tr>
					<td>&nbsp;</td> <!-- 여백 -->
				</tr>			
			</table>
			<div class="tb2">
			<table width=1000px> <!-- Bottom -->
				<tr>
					<td colspan="3" style="border-bottom:1px solid white;"><h4>결제금액정보</h4></td>
				</tr>
				<tr>
					<td width="700px" height="160px" bgcolor="#404040"><h4>Credit card : ${st_pur_price}</h4>Discount : - ${discount}</td>
					<td width="200px" bgcolor="#5CB85C"><h4>주문금액</h4><h6>간편카드결제</h6></td>
					<td width="100px" bgcolor="#5CB85C"><h4>${st_total}</h4><h6>${st_total}</h6></td>
				</tr>
			</table>
			</div>
			</center>
		</div> <!-- details -->
	<div style="margin-top:500px"></div> <!-- 하단여백설정 -->
</div> <!-- col-md-10 -->
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