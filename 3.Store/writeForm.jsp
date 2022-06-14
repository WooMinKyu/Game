<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>detail</title>
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
	color:black;
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
	.item img {
	margin-top:10px;
	width:100%;
	height:auto;
	}
	.sideTop {
	vertical-align:top;
	padding-top:40px;
	}
	.ul { list-style:none; font-size:17pt;}

	.ul li{text-align:left;}

	.ul li a{padding-left:20px; font-size:15pt;}
	
	.ul li a:hover{color:white;}

	.sort{
	float:right; width:200px;
			background-color:gray; color:white;
			font-size:15pt;
	}
	.title{
	    font-size:30pt;
	    color:white;
	    text-align:left;
	    }
	.road{
	    font-size:15pt;
	    color:white;
	    text-align:left;
    }
    #Bttn {
    	border:1px solid #5bb85d;
		color:white;
		border-radius:4px;
		padding: 12px 0px;
		margin-top:5px;
		font-size: 18px;
		background-color:#5bb85d;
		width:100%;
    }
    #Bttn:hover {
		background-color:green;
    }
    #Bttn2 {
    	border:1px solid grey;
		color:white;
		border-radius:4px;
		padding: 5px 0px;
		margin-top:5px;
		font-size: 16px;
		background-color:transparent;
		width:300px;
    }
    #Bttn2:hover {
		background-color:grey;
    }
    #Bttn3 {
    	border:1px solid grey;
		color:white;
		border-radius:4px;
		padding: 5px 0px;
		margin-top:5px;
		font-size: 16px;
		background-color:#4D4D4D;
		width:300px;
    }
    #Bttn3:hover {
		background-color:grey;
    }
    textarea{
   		resize: none;
    }
    input{
    color:black;
    }
    .inputTab{
    	text-align:center;
    }
    .inTab td{
    	padding-left:8px;
    }
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>


<body>
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
<div class="col-md-1"></div> <!-- Margin left -->
	
<div class="col-md-10"> <!-- Main (※90%)-->
	<!-- form -->
	<form method="post" name="writeform" action="/Game/StoreWritePro.do" onsubmit="return writeSave()">
		<input type="hidden" name="ad_id" value="${article.ad_id}">
	
	<div style="margin-top:50px;"></div>
	<center>
		<table width="90%" border="1"> <!-- Top area -->
			<tr>
				<td colspan="2" style="padding:10px; border-bottom:1px solid grey;">
					<div class="road ">Store Item Write Form</div>
				</td>
			</tr>
			<tr>
				<td style="padding:10px">
					<input type="text" size="100%" maxlength="10" placeholder="Game Title" name="item_name" style="font-size:15pt;">
				</td>
			</tr>
		</table>
		<table width="90%" border="0"> <!-- 75% 상품 영역 / 25% 사이드 바 -->
			<tr>
				<td width="75%"> <!-- 상품영역 -->
					<div style="padding:200px; border:1px solid white; text-align:center"><input type="text" size="30" maxlength="10" name="item_img" placeholder="img(nubmers)"></div>
						<div>
			    			<div class="col-md-3 small" style="background-color:black;"><div style="padding:20px; border:1px solid white; text-align:center">img(x)_m(x)</div></div>
			    			<div class="col-md-3 small" style="background-color:black;"><div style="padding:20px; border:1px solid white; text-align:center">img(x)_m(x)</div></div>
			    			<div class="col-md-3 small" style="background-color:black;"><div style="padding:20px; border:1px solid white; text-align:center">img(x)_m(x)</div></div>
			    			<div class="col-md-3 small" style="background-color:black;"><div style="padding:20px; border:1px solid white; text-align:center">img(x)_m(x)</div></div>
			    		</div>
			    		<table width="100%" height="500px" border="0">
			    			<tr>
			    				<td style="width:50%; height:30%; text-align:center; background-color:#181818; border:1px solid grey;">
			    					Genre<br><input type="text" size="20" maxlength="10" name="item_gen" placeholder="Genre ex. Action, RPG ,,,">
			    				</td>
			    				<td style="width:50%; text-align:center; background-color:#181818; border:1px solid grey;">
			    					Function<br><input type="text" size="20" maxlength="10" name="item_ft" placeholder="Function ex. SinglePlay, MultiPlay ,,,">
			    				</td>
			    			</tr>
			    			<tr>
			    				<td colspan="2" style="height:70%; vertical-align:top; padding:20px; border-bottom:1px solid grey;">
						    		<h3>Game content</h3><br><textarea rows="10" cols="120" name="item_content" style="color:black"></textarea>				
			    				</td>
			    			</tr>
			    		</table>
				</td>
			<td width="25%" style="vertical-align:top;"> <!-- 구매영역 -->
				<div> 
		            <div style="padding-left:30px; padding:30px; border:1px solid white">
                    	<div style="padding:30px; border:1px solid white; text-align:center"><input type="text" size="30" maxlength="10" name="item_thum" placeholder="thum(nubmers)"></div>
			                <table width="100%">
			    	            <tr>
			        	            <td width="100%">Price</td>
			                    </tr>
			                    <tr>
			                        <td><input type="text" size="40" maxlength="10" name="item_price" placeholder="Price ex. 10000"></td>			                    
			                    </tr>
			                    <tr>
		                            <td>Discount</td>
		                        </tr>
		                        <tr>
		                            <td><input type="text" size="40" maxlength="10" name="item_sale" placeholder="Discount ex.0, 0.1, 0.3, 0.5"></td>		                        
		                        </tr>
		                     </table>
		            </div>  
			<center>
			<table width=100% height="350px" border="1" class="inTab">
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td style="padding-top:50px"><h5>이용등급</h5></td>
			</tr>
	  		<tr>
	  			<td class="inputTab"><input type="text" size="40" maxlength="10" name="item_grd" placeholder="전체이용가, 18세이용가"></td>
	  		</tr>
	  		<tr>
	  			<td><h5>장르번호</h5></td>
	  		</tr>
	  		<tr>
	  			<td class="inputTab"><input type="text" size="40" maxlength="10" name="gen_num" placeholder="0001 ~ 0006"></td>
	  		</tr>
	  		<tr>
	  			<td><h5>등급번호</h5></td>
	  		</tr>
	  		<tr>
	  			<td class="inputTab"><input type="text" size="40" maxlength="10" name="grd_num" placeholder="000, 015, 018"></td>
	  		</tr>
	  		<tr>
	  			<td><h5>Developer</h5></td>
	  		</tr>
	  		<tr>
	  			<td class="inputTab"><input type="text" size="40" maxlength="10" name="item_dev" placeholder="5games.com"></td>
	  		</tr>
	  		<tr>
	  			<td><h5>Publisher</h5></td>
	  		</tr>
	  		<tr>
	  			<td class="inputTab"><input type="text" size="40" maxlength="10" name="item_pub" placeholder="5games.com"></td>	  		
	  		</tr>
	  		<tr>
	  			<td><h5>Release</h5></td>
	  		</tr>
	  		<tr>
	  			<td class="inputTab"><input type="text" size="40" maxlength="10" name="item_rels" placeholder="2002-03-21"></td>	  		
	  		</tr>
	  		<tr>
	  			<td><h5>Platform</h5></td>
	  		</tr>
	  		<tr>
	  			<td class="inputTab"><input type="text" size="40" maxlength="10" name="item_pf" placeholder="Window or Mac"></td>	  		
	  		</tr>
	  		<tr><td style="padding-top:50px;">&nbsp;</td></tr>
	  		<tr><td><button type="submit" id="Bttn">Post</button></td></tr>
		</table>
		
		</form>
	  	</div><!-- 오른쪽부분 -->
			</table>
	        </center>
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