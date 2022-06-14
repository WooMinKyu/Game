<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>item</title>

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
	filter: brightness(50%);
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
	color:#aeaeae;
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
	.ul { list-style:none;}

	.ul li{text-align:left;}

	.ul li a{padding-left:20px;}
	
	.ul li a:hover{color:white;}

	.sort{
		float:right; 
		width:200px;
		background-color:#2b2b2b;
		color:white;
	}
	.thumtext{
		text-align:center;
		font-size:13pt;
	}
	.thumtext a{
	color:white;
	}
	.write{
		text-align:center;
	}
	.pagetext{
		text-align:center;
	}
	.item a img{
		border:1px black;
	}
	.item a img:hover{
		border:1px white;
	}
	.form-control{
	width:500px;
	border-radius: 20px;
	}
	#search{
	position:relative;
	top:-35px;
	left:220px;
	}
	#search:hover{
	color:#66afe9;
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
	
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>

</script>
<body>
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	
	<div class="col-md-10"> <!-- Main (※90%)-->
		<center>
			<table width="90%" border="0"> <!-- 검색/소팅 영역 -->
				<tr><td><h1>Store Management Page</h1><h4>admin only</h4></td></tr>
				<tr>
					<td><button onClick="location.href='/Game/3.Store/writeForm.jsp'">Write</button></td>
					<td colspan="4">
						<div style="float:right">
	                		<form name="test" action="/Game/AdStoreList.do">
	                			<select name="search">
	                				<option value="item_name">Title</option>
	                				<option value="item_gen">Genre</option>
	                			</select>
	                			<input type="text" size="15" name="searchtext" class="textbox">&nbsp;
     							<input type="submit" value="search" class="textbtn">
				    		</form>	
	                	</div>
					</td>
				</tr>
				<tr><td style="margin-top:50px">&nbsp;</td></tr>
			</table>
			
			
			<table width="90%" border="0"> <!-- 사이드바/상품영역 -->
				<tr>
					<td width="80%"> 	<!-- 상품영역 -->
					<c:if test="${pgList.count==0}">
					<table border="1" width="700" cellpadding="0" cellspacing="0" align="center">
					   <tr>
					       <td align="center">게시판에 저장된 글이 없습니다.</td>
					   </tr>
					</table>
					</c:if>
					<c:if test="${pgList.count > 0}">
					<c:set var="number"  value="${pgList.number}" />
					<c:forEach var="article"  items="${articleList}">
					<div class="col-md-3">
						<div class="itemlinker">
							<a href="/Game/AdStoreContent.do?item_num=${article.item_num}&pageNum=${pgList.currentPage}"><img src="/Game/img/items/${article.item_img}.jpg"></img></a>
						</div>
						<div class="thumtext">
							<a href="/Game/AdStoreContent.do?item_num=${article.item_num}&pageNum=${pgList.currentPage}">
							<span>
								${article.item_name}<br><!-- 상품이름 7글자찾아야함 -->
								${article.item_price}<!-- 가격 -->
							</span>
							</a>
						</div>
					</div>
					</c:forEach>
					</c:if>
					<!-- /////////////////////상품최대줄 8줄/////////////////// -->
					<div class="col-md-12" style="margin-top:50px;">
					
					<!-- 페이징 처리 -->
					<div class="pagetext">
					<c:if test="${pgList.startPage > pgList.blockSize}">
					     <a href="/Game/AdStoreList.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}">[이전]</a>
					</c:if>
					
					<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
					     <a href="/Game/AdStoreList.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
					           <c:if test="${pgList.currentPage==i}">
					                   <font color="red"><b> [${i}]</b></font>
					           </c:if>
					           <c:if test="${pgList.currentPage!=i}">
					                   ${i}
					           </c:if>
					     </a>
					</c:forEach>
					<c:if test="${pgList.endPage <pgList.pageCount}">
					      <a href="/Game/AdStoreList.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}">[다음]</a>
					 </c:if>  
					<p>
					</div>
					</div>
					</td>
				</tr>
			</table>
	        </center>
		<div style="margin-top:100px"></div> <!-- 하단여백설정 -->
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