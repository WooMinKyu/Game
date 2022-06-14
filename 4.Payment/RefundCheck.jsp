<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	.refbt {
		border:1px solid #5bb85d;
		color:#5bb85d;
		border-radius:4px;
		padding: 4px 0px;
		font-size: 15px;
		background-color:transparent;
		width:150px;
		height:30px;
	}
	.refbt:hover {/* 구매,취소 버튼에 마우스 올릴때 */
		color:#2b4e2c;
		border:1px solid #2b4e2c;
	}
	#refckbox {
	color:black;
	text-align:center;
	}
	#Bttn {
    	border:1px solid #5bb85d;
		color:white;
		border-radius:4px;
		padding: 12px 0px;
		margin-top:5px;
		font-size: 18px;
		background-color:#5bb85d;
		width:200px;
    }
    #Bttn:hover {
		background-color:green;
    }
    #Bttn2 {
    	border:1px solid grey;
		color:white;
		border-radius:4px;
		padding: 12px 0px;
		margin-top:5px;
		font-size: 16px;
		background-color:transparent;
		width:200px;
    }
    #Bttn2:hover {
		background-color:grey;
    }
	
</style>
<script>
$(function() {
	
	$('#Bttn').click(function() {
		if($('#refckbox').val()=="환불하기") {
			if(confirm('환불하시겠습니까?')) {
				alert('환불이 완료되었습니다.')
				$('#refundform').submit();
			}else {
				return false;
			}
		}else {
			alert('환불하기를 입력해주세요.');
			return false;
		}
	})
})
</script>
<body>
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	
	<div class="col-md-10"> <!-- Main (※90%)-->
		<form method="post" id="refundform" action="/Game/RefundPro.do?pur_num=${pur_num}"> <!-- 이부분 설정 필요 -->
		
		<table width="90%">
		<center>
		<div style="margin-top:100px">
			<img src="/Game/img/items/${pur_thum}.jpg" width="400px" height="auto"><br>
			<h3>${pur_name}</h3><br>
			<h5>위 상품을 정말 환불을 원하는 경우 하단에 "환불하기"를 입력해주세요.</h5><br>
			<input type="text" class="rfcheck" placeholder="환불하기" id="refckbox"><br><br>
			<input type="hidden" name="pur_num" value="${pur_num}"/>
			
			<!-- <span class="msg" id="refckbox_error">ID값은 (필수정보) 입력하세요</span><br> -->
			<!-- 이부분에 j-query 설정 (입력 안하면 이동 불가 기능 JqueryTest/3.event/10.input.html ) -->
			
			<div style="margin-top:50px"></div> <!-- 하단여백설정 -->
				<button type="submit" id="Bttn">Refund</button>
				<button type="button" id="Bttn2" onClick="javascript:history.back()">Cancel</button>
				<!-- <input type="button" value="Send" class="refbt" id="Bttn">
				<input type="button" value="Cancel" class="refbt" id="Bttn2" OnClick="javascript:history.back()"> -->
			</div>		
		</center>
		</table>		
	<div style="margin-top:500px"></div> <!-- 하단여백설정 -->
	</form>
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