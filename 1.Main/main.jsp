<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Main</title>
    <script type="text/javascript"
			src="http://code.jquery.com/jquery-3.3.1.min.js"></script>

    <!-- 부트스트랩 -->
    <link href="/Game/css/bootstrap.min.css" rel="stylesheet">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
	<link href='https://fonts.googleapis.com/css?family=Anton' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Neucha' rel='stylesheet' type='text/css'>

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
    .at1 {
	    width:246vw;
	    transition: transform 1s; 
    }
    .at1 .inner {
    	width:82vw;
    	float:left;
    }
    .at1 .inner img {
    	width:90%;
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
    .refund img{
    width:100%;
    height:auto;
    }
  </style>
  <script>
  var interval=setInterval( function(){             //setInterval(); 함수 반복 실행
	  $(".at1").delay("3000");        
	  $(".at1").animate({marginLeft:  "-82vw" },1000);    //0.7초동안 애니메이션
	  $(".at1").delay("3000");
	  $(".at1").animate({marginLeft:  "-164vw" },1000);
	  $(".at1").delay("3000");
	  $(".at1").animate({marginLeft:  "0" },1000);
});

  </script>
<body>
	<jsp:include page="/1.Main/Top.jsp" />
	<div class="rows">
    	<div class="col-md-1"></div> <!-- Margin left -->
    	<div class="col-md-10"> <!-- Main -->
    		<center>
   			<!-- Slide area -->
   			<div style="overflow:hidden;">
				<div class="at1"> <!-- Animation Image (Slide TAG) -->
	   				<div class="inner"><img src="/Game/img/slide1.jpg"></div>
	   				<div class="inner"><img src="/Game/img/slide2.jpg"></div>
	   				<div class="inner"><img src="/Game/img/slide3.jpg"></div>
	   			</div>
   			</div>
   			<!-- Slide area -->

    		<article class="itemlinker" style=margin-top:50px;> <!-- Best Seller -->
				<table width=90%>
					<tr>
						<th>Best Seller<a href="#" style="float:right"><span class="more">More >></span></a></th>
					</tr>
					<tr>
						<td>
						
							<c:forEach var="bestGames"  items="${bestGameList}">
								<div class="col-md-2">
									<div class="itemlinker">
										<a href="/Game/StoreContent.do?item_num=${bestGames.item_num}">
											<img src="/Game/img/items/${bestGames.item_img}.jpg" />
										</a>
									</div>
								</div>
							</c:forEach>

						</td>
					</tr>
    			</table>
    		</article>
    		<article class="itemlinker" style=margin-top:50px;> <!-- Discount -->
				<table width=90%>
					<tr>
						<th>Discounts<a href="#" style="float:right"><span class="more">More >></span></a></th>
					</tr>
					<tr>
						<td>
							<c:forEach var="discountGames"  items="${discountGameList}">
								<div class="col-md-2">
									<div class="itemlinker">
										<a href="/Game/StoreContent.do?item_num=${discountGames.item_num}">
											<img src="/Game/img/items/${discountGames.item_img}.jpg" />
										</a>
									</div>
								</div>
							</c:forEach>
						</td>
					</tr>
    		</table>
    		</article>
    		<article class="itemlinker" style=margin-top:50px;> <!-- Discount -->
				<table width=90%>
					<tr>
						<th>New<a href="#" style="float:right"><span class="more">More >></span></a></th>
					</tr>
					<tr>
						<td>
							<c:forEach var="newGames"  items="${newGameList}">
								<div class="col-md-2">
									<div class="itemlinker">
										<a href="/Game/StoreContent.do?item_num=${newGames.item_num}">
											<img src="/Game/img/items/${newGames.item_img}.jpg" />
										</a>
									</div>
								</div>
							</c:forEach>
						</td>
					</tr>
    		</table>
    		</article>
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
  	<jsp:include page="/1.Main/Footer.jsp" />
  </footer>
</html>