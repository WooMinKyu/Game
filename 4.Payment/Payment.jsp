<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript"
			src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>결제페이지</title>

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
	.itemlinker a, a:hover {
	text-decoration:none;
	color:#aeaeae;
	}
	.itemlinker a:hover {
	text-decoration:none;
	color:white;
	}
	.ft{
	clear:both;
	padding-top:50px;
	}
	#line {/* 가운데 분리선 */
		border-right:1px solid #3e3c3c;
		padding-right:20px;
	}
	.paytext {/* 결제수단 이름 설정 */
		margin:10px;
		font-size:16pt;
		color:lightgray;
	}
	#paytype {/* 결제수단 콤보박스 설정 */
		height:auto;
		width:80%;
		border-radius:4px;
		padding-left:10px;
		font-size:18px;
		font-weight:bold;
	}
	#returnck + span {/* 환불정책 동의 구문 글자색 */
		color:gray;
	}
	#btg {/* 버튼그룹 */
		margin-top:40px;
	}
	
	#rightside {/* 오른쪽 영역 */
		padding-left:10px;
		
		margin-bottom:50px;
	}
	#rightside h3 {/* 게임 이름 */
		margin-top:30px;
		width:250px;
		color:white;
		font-size:21px;
	}
	#paytb {/* 금액테이블 설정 */
		color:gray;
		margin-top:10px;
		width:100%;
	}
	#Bttn {
    	border:1px solid #5bb85d;
		color:white;
		border-radius:4px;
		padding: 12px 0px;
		margin-top:5px;
		font-size: 16px;
		background-color:#5bb85d;
		width:40%;
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
		width:40%;
    }
    #Bttn2:hover {
		background-color:grey;
    }
</style>
<script>
$(function() {
	$('#Bttn').click(function() {
		if(confirm('구매하시겠습니까?')) {
			if($('#returnck').is(':checked')) {
				$('#payform').submit();
			}else {
				alert('환불정책에 동의해주세요.');
				return false;
			}
		}else{
			return false;
		}
	})	
	
	$('#Bttn2').click(function() {
		history.back();
	})	
})

</script>
<body>
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
    	<div class="col-md-1"></div> <!-- Margin left -->
    	<div class="col-md-10"> <!-- Main -->
    		
    		<div class="col-md-3"></div>
    		<div class="col-md-4" style="margin-bottom:300px">
    		<h2 style="margin-bottom:70px">구매하기</h2>
    		<div id="line">
    		<h1>구매 정보</h1>
    		<h5 style="color:gray">결제수단을 선택해주세요.</h5>
    		<label class="radio-inline">
    			<input type="radio" disabled /><span class="paytext">일반결제</span>
    		</label><br><br>
    		<label class="radio-inline">
    			<input type="radio" disabled /><span class="paytext">상품권류</span>
    		</label><br><br>
    		<select name="paytype" id="paytype" class="form-control">
    			<option value="">신용카드</option>
    			<option value="">휴대폰결제</option>
    			<option value="">카카오페이</option>
    			<option value="">토스</option>
    			<option value="">페이코</option>
    			<option value="">무통장입금</option>
    			<option value="">티머니</option>
    			<option value="">계좌이체</option>
    		</select>
    		<hr style="border-top:1px solid #3e3c3c;">
    		<label class="checkbox-inline">
    			<input type="checkbox" name="returnck" id="returnck" /> <span>본거래와 관련된 환불정책에 동의합니다.</span>
    		</label><br><br>
	    		<p id="btg">
	    		<form id="payform" action="/Game/PayPro.do" method="post">
	    			<input type="hidden" name="item_num" value="${item_num}" />
	    			<input type="hidden" name="item_name" value="${item_name}" />
	    			<input type="hidden" name="item_price" value="${item_price}" />
	    			<input type="hidden" name="item_thum" value="${item_thum}" />
	    			<input type="hidden" name="item_img" value="${item_img}" />
	    			<input type="hidden" name="item_sale" value="${item_sale}" />
	    			<button type="submit" id="Bttn">구매하기</button>
	    			<button type="button" id="Bttn2">취소</button>
	    			</form>
	    		</p>
	    		</div>
    		</div>
    		<div class="col-md-2" style="margin-top:130px;padding:0px">
    			<div id="rightside">
    				<div >
    					<img src="/Game/img/items/${item_thum}.jpg" width="100%" height="auto" /> <!-- item_thum -->
    				</div>
    				<h3>${item_name}</h3><br>	<%-- ${pur_name } --%>
    				<table id="paytb">
    					<tr>
    						<td >정가</td>
    						<td align="right">${st_item_price}</td>	<!-- ${pur_price} -->
    					</tr>
    					<tr>
    						<td>할인금액</td>
    						<td align="right">- ${st_discount}</td>		<!-- ${discount} -->
    					</tr>
    				</table>
    				<hr style="border-top:1px solid #3e3c3c;">
    				<span>총액</span><br>
    				<span style="font-size:18pt"><b>${st_total}</b></span>	<!-- ${price} -->
    			</div>
    		</div>
    		<div class="col-md-2"></div>
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