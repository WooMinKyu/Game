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
		width:300px;
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
    #ReBttn{
    	border:1px solid grey;
		color:white;
		border-radius:4px;
		padding: 8px 0px;
		margin-top:5px;
		font-size: 12px;
		background-color:transparent;
		width:100px;
    }
    #ReBttn:hover {
		background-color:grey;
    }
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>


	
   $(function(){
	   //$(".small a").mouseover(function(){
		  $(".small a img").hover(function(){
			  var imgname=$(this).attr("src")
			  //alert(imgname); fadeIn,fadeOut->fadeTo(유지시간,투명도(0 or 1),효과후처리할 함수)
		  		$('.large').attr('src',imgname);//$(.large).attr(''href'));
		  },function(){})
	   })
	function pay() {
		 if('${sessionScope.m_id}'==''){
			   if(confirm("로그인 하셔야 이용 가능합니다. \n로그인 하시겠습니까?")) {
				   location=href="/Game/2.Login&Member/Login.jsp"
			   }else {
					return false;   
			   } 
		  }else {
				$("#storepay").submit()
		  }
		 
	}
 
   
	function putInCart() {
	   if('${sessionScope.m_id}'==''){
		   if(confirm("로그인 하셔야 이용 가능합니다. \n로그인 하시겠습니까?")) {
			   location=href="/Game/2.Login&Member/Login.jsp"
		   }else {
				return false;   
		   } 
	   }
	   $.ajax({
			url:'/Game/4.Payment/PayPutInCart.jsp', 
			type:'post',   
			data:{item_num:'${article.item_num}',item_thum:'${article.item_thum}',item_name:'${article.item_name}',item_price:'${article.item_price}',grd_num:'${article.grd_num}',item_img:'${article.item_img}',item_sale:'${article.item_sale}'},
			success:function(args) {
				 location.reload();
			}
		})
   }
   function putInWishlist() {
	   if('${sessionScope.m_id}'==''){
		   if(confirm("로그인 하셔야 이용 가능합니다. \n로그인 하시겠습니까?")) {
			   location=href="/Game/2.Login&Member/Login.jsp"
		   }else {
				return false;   
		   } 
	   }
	   $.ajax({
			url:'/Game/4.Payment/PayPutInWishlist.jsp', 
			type:'post',   
			data:{item_num:'${article.item_num}',item_thum:'${article.item_thum}',item_name:'${article.item_name}',item_price:'${article.item_price}',grd_num:'${article.grd_num}',item_img:'${article.item_img}',item_sale:'${article.item_sale}'},
			success:function(args) {
				location.reload();
			}
		})
   }
   function deleteWishlist() {
	   $.ajax({
			url:'/Game/4.Payment/PayDeleteWishlist.jsp', 
			type:'post',   
			data:{item_num:'${article.item_num}'},
			success:function(args) {
				location.reload();
			}
		})
   }
   
   function RevWrite() {
	   if('${sessionScope.m_id}'==''){
		   if(confirm("로그인 하셔야 이용 가능합니다. \n로그인 하시겠습니까?")==true) {
			   location=href="/Game/2.Login&Member/Login.jsp"
		   }else {
				return false;   
		   } 
	   }else {
		   
		  
		   if(${pur_item_num!=item_num}){
				if(confirm('구매하셔야 리뷰를 쓰실 수 있습니다. \n구매하시겠습니까?')){
					$('#storepay').submit();
				}else {
					return false;
				}
		   }else {
			   if($('#rev_content').val()=='') {
					  alert('글을 입력해주세요.');
					  return false;
				  }
			   $.ajax({
					url:'/Game/8.RevBoard/RevWriteProc.jsp', 
					type:'post',   
					data:{item_num:'${item_num}', rev_nick:'${m_nick}', rev_content:$('#rev_content').val()},
					success:function(args) {
						location.reload();
					}
				})//ajax
	   		}
	  
   		}//'${sessionScope.m_id}'==''의 else
   }//RevWrite()
   
 /*   <input type="hidden" name="com_m_id" value="${sessionScope.m_id}" ><!-- 이거를 받아와야함 세션처리 -->
	<input type="hidden" name="com_num" value="${rev_num}" >
   <input type="hidden" name="com_nick" value="${rev_nick}" >
   <input type="hidden" name="com_nick" value="${rev_wdate}" >
   <input type="hidden" name="com_nick" value="${rev_content}" >
   <input type="hidden" name="item_num" value="${article.item_num}" > */
</script>

<body>
<jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
<div class="rows">
	<div class="col-md-1"></div> <!-- Margin left -->
	
	<div class="col-md-10"> <!-- Main (※90%)-->
	<div style="margin-top:50px;"></div>
		<center>
			<table width="90%" border="0"> <!-- Top area -->
				<tr>
					<td colspan="2" style="padding:10px; border-bottom:1px solid grey;">
						<div class="road "><a href="/Game/StoreList.do?search=기본">Store</a> / <a href="/Game/StoreList.do?search=${article.item_gen}">${article.item_gen}</a></div>
					</td>
				</tr>
				<tr>
					<td style="padding:20px; width:50%;">
						<div class="title">${article.item_name}</div>
					</td>
				</tr>
			</table>
			<table width="90%" border="0"> <!-- 75% 상품 영역 / 25% 사이드 바 -->
				<tr>
					<td width="75%"> <!-- 상품영역 -->
						<div><img src="/Game/img/items/${article.item_img}_m1.jpg" width=100% height=auto; class="large"></div>
						<div>
			    			<div class="col-md-3 small" style="background-color:black;"><a href="#"><img src="/Game/img/items/${article.item_img}_m1.jpg" width=100% height=auto;></a></div>
			    			<div class="col-md-3 small" style="background-color:black;"><a href="#"><img src="/Game/img/items/${article.item_img}_m2.jpg" width=100% height=auto;></a></div>
			    			<div class="col-md-3 small" style="background-color:black;"><a href="#"><img src="/Game/img/items/${article.item_img}_m3.jpg" width=100% height=auto;></a></div>
			    			<div class="col-md-3 small" style="background-color:black;"><a href="#"><img src="/Game/img/items/${article.item_img}_m4.jpg" width=100% height=auto;></a></div>
			    		</div>
    		<table width="100%" height="500px" border="0">
    			<tr>
    				<td style="width:50%; height:30%; text-align:center; background-color:#181818; border:1px solid grey;">
    					Genre<br><a href="/Game/StoreList.do?search=${article.item_gen}">${article.item_gen}</a>
    				</td>
    				<td style="width:50%; text-align:center; background-color:#181818; border:1px solid grey;">
    					Function<br><a href="/Game/StoreList.do?search=${article.item_ft}">${article.item_ft}</a>
    				</td>
    			</tr>
    			<tr>
    				<td colspan="2" style="height:70%; vertical-align:top; padding:80px 150px 200px 20px; border-bottom:1px solid grey;">
			    		${article.item_content}					
    				</td>
    			</tr>
    		</table>
    		
    		<!-- Review -->
    		<table width="100%" border="0">
    			<tr>
    				<td>
    					<div class="col-md-12" style="margin-top:30px;"><h2>Review Board</h2></div>
    				</td>
    			</tr>
    			<tr>
	    			<td>
	    				<table width="90%" style="margin-left:10px">
	    					<tr>
				    			<td>
					    				
					    				<!-- form -->
										<table width="100%" cellspacing="0" cellpadding="0" align="center" border="0">
											<tr colspan="2" style="float:right;">
												<td>
													<button type="button" onclick="RevWrite()" id="ReBttn">Write</button>
													<!-- <input type="submit" class="btn btn-default" value="글쓰기" style="align:right; margin-top:5px; margin-right:5px;"><br>&nbsp; -->
												</td>
											</tr>
											<tr><td>&nbsp;</td></tr>
											<tr>
												<td colspan="2">
													<textarea rows="6" style="background-color:light-grey; color:black; width:100%" id="rev_content" name="rev_content"></textarea>				
												</td>
											</tr>
											
										</table>
					    				<!-- form -->
					    				
					    			<%-- 	<form action="/Game/PayForm.do" method="post" id="storepay">
								    		<input type="hidden" name="item_num" value="${article.item_num}" />
								    		<input type="hidden" name="item_name" value="${article.item_name}" />
								    		<input type="hidden" name="item_thum" value="${article.item_thum}" />
								    		<input type="hidden" name="item_img" value="${article.item_img}" />
								    		<input type="hidden" name="item_price" value="${article.item_price}" />
								    		<input type="hidden" name="" value="" />
							    			<button type="button" id="Bttn" onclick="pay()">Purchase</button><br>
							       		</form> --%>
					    				
					    				
					    				
					    		</td>
					    	</tr>
					    	<tr>
					    		<td style="padding:10px; border-top:1px solid grey; border-bottom:1px solid grey">
									<!-- foreach -->
									<c:if test="${pgList.count==0}">
										<div style="margin-top:50px; text-align:center">게시판에 저장된 글이 없습니다.</div>
										<div style="margin-bottom:50px"></div>
									</c:if>
					    			
					    			<c:if test="${pgList.count > 0}">
					    			<c:set var="number" value="${pgList.number}" />
					    			<c:forEach var="revArticleList" items="${articleList}">
									<div style="margin-top:15px;"></div>
									<div style="padding:10px; background-color:#181818; color:#AFAFAF;"><div style="padding:10px 200px 10px 0px;"><h5 style="color:white;">${revArticleList.rev_content}<h5></div>
										<br><br>WRITER : ${revArticleList.rev_nick}<span style="float:right">DATE : ${revArticleList.rev_wdate}</span></div>
									<div style="margin-top:8px;"></div>
									</c:forEach>
									</c:if>
									<!-- foreach -->
								</td>
	    					</tr>
	    					<tr><td>&nbsp;</td></tr>
			    			<tr><td>&nbsp;</td></tr>
							<%-- <tr style="text-align:center;">
								<td>
									<c:if test="${pgList.startPage > pgList.blockSize}">
										<a href="/Game/StoreContent.do?item_num=${article.item_num}&pageNum=${pageNum}&RevPageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}">&lt&nbsp;&nbsp;&nbsp;</a>
									</c:if>
									<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
										<a href="/Game/StoreContent.do?item_num=${article.item_num}&pageNum=${pageNum}&RevPageNum=${i}&search=${search}&searchtext=${searchtext}">
										<c:if test="${pgList.currentPage==i}">
											<font color="red"><b>[${i}]</b></font>
										</c:if>
										<c:if test="${pgList.currentPage!=i}">
											${i}
										</c:if>
										</a>
									</c:forEach>
									<c:if test="${endPage < pageCount}">
										<a href="/Game/StoreContent.do?item_num=${article.item_num}&pageNum=${pageNum}&RevPageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}">&nbsp;&nbsp;&nbsp;></a>
									</c:if>
								</td>
							</tr> --%>
			    			<tr><td>&nbsp;</td></tr>
			    			<tr><td>&nbsp;</td></tr>
	    				</table>
	    			</td>
	    				
    			</tr>
    			
    			<tr><td style="border-bottom:1px solid grey;">&nbsp;</td></tr>
    		</table>

    		<!-- Review -->
    		
    		
    		
    		
			</td>
				<td width="25%" style="vertical-align:top;"> <!-- 구매영역 -->
					<div> 
		                <div style="padding-left:30px; padding-right:30px;">
		                    <img src="/Game/img/items/${article.item_thum}.jpg" width="100%" height="auto" style="margin-top:17px;"/>
			                    <h3 style="margin-top:30px;width:100%">${article.item_name}</h3><br>
			                    <table width="100%">
			                        <tr>
			                            <td width="100%">정가</td>
			                            <td style="text-align:right">${article.item_price}</td>
			                        </tr>
			                        <tr>
			                            <td>할인금액</td>
			                            <td style="text-align:right">0</td>
			                        </tr>
			                        <tr>
			                        	<td><h3>총액</h3></td>
			                        	<td style="text-align:right"><h3>${article.item_price}</h3></td>
			                        </tr>
			                    </table>
		                </div>  
			<center>
			<table width=90% height="350px" border="0">
				<tr>
					<td colspan="2">
<!-- Purchase Button -->
						<div style="padding:0px 5px 0px 5px;">
						<c:choose> 
				    		<c:when test="${pur_item_num!=item_num}">
				    		<form action="/Game/PayForm.do" method="post" id="storepay">
					    		<input type="hidden" name="item_num" value="${article.item_num}" />
					    		<input type="hidden" name="item_name" value="${article.item_name}" />
					    		<input type="hidden" name="item_thum" value="${article.item_thum}" />
					    		<input type="hidden" name="item_img" value="${article.item_img}" />
					    		<input type="hidden" name="item_price" value="${article.item_price}" />
					    		<input type="hidden" name="item_sale" value="${article.item_sale}" />
				    			<button type="button" id="Bttn" onclick="pay()">Purchase</button><br>
				       		</form>
				       		</c:when>
				       		<c:when test="${pur_item_num==item_num}" >
				       			<button id="Bttn" disabled>You have a list.</button>
				       		</c:when>
				       		<%-- <c:when test="${pur_item_num==item_num && pur_stat==0}">
				    		<form action="/Game/PayForm.do" method="post" id="storepay">
					    		<input type="hidden" name="item_num" value="${article.item_num}" />
					    		<input type="hidden" name="item_name" value="${article.item_name}" />
					    		<input type="hidden" name="item_thum" value="${article.item_thum}" />
					    		<input type="hidden" name="item_img" value="${article.item_img}" />
					    		<input type="hidden" name="item_price" value="${article.item_price}" />
					    		<input type="hidden" name="item_sale" value="${article.item_sale}" />
				    			<button type="button" id="Bttn" onclick="pay()">Purchase</button><br>
				       		</form>
				       		</c:when> --%>
				       		
				       	</c:choose>	
				       	</div>
<!-- Cart Button -->
				    	<div style="padding:0px 5px 0px 5px;">
				    	<c:choose>
				    		<c:when test="${pur_item_num==item_num && pur_item_num==item_num}" >
						    	<button type="button" id="Bttn2" disabled >Cart</button>
						    </c:when>
				    		<c:when test="${ct_item_num!=item_num}">
						    	<button type="button" id="Bttn2" onClick="putInCart()">Cart</button>
						    </c:when>
						    <c:otherwise> <!-- test="${ct_item_num==item_num} -->
						    	<button type="button" id="Bttn3" onclick="javascript:location.href='/Game/Cart.do'">Go to Cart.</button>
						    </c:otherwise>
						</c:choose>
					    </div>
<!-- Wish Button -->       									
    	<div style="padding:0px 5px 0px 5px;"> <!-- wishlist button -->
	    	<c:choose>
	    		<c:when test="${pur_item_num==item_num && pur_item_num==item_num}" >
		    		<button type="button" id="Bttn2" disabled><span class="glyphicon glyphicon-heart"></span></button>
		    	</c:when>
	    		<c:when test="${w_item_num!=item_num}">
		    		<button type="button" id="Bttn2" onClick="putInWishlist()">
		    			<span class="glyphicon glyphicon-heart"></span>
		    		</button>
		    	</c:when>
		    	<c:otherwise> <!--  test="${w_item_num==item_num}" -->
		    		<button type="button" id="Bttn3" onclick="deleteWishlist()"><span style="color:red" class="glyphicon glyphicon-heart"></span></button>
		    	</c:otherwise>
		    </c:choose>
    	</div>
    	
    	
    			</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td><img src="/Game/img/${article.grd_num}.png" width="60px" height="60px"></td>
	  			<td><h5 style="text-align:right">${article.item_grd}</h5></td>
	  		</tr>
	  		<tr>
	  			<td><h5>Developer</h5></td>
	  			<td><h5 style="text-align:right">${article.item_dev}</h5></td>
	  		</tr>
	  		<tr>
	  			<td><h5>Publisher</h5></td>
	  			<td><h5 style="text-align:right">${article.item_pub}</h5></td>
	  		</tr>
	  		<tr>
	  			<td><h5>Release</h5></td>
	  			<td><h5 style="text-align:right">${article.item_rels}</h5></td>
	  		</tr>
	  		<tr>
	  			<td><h5>Platform</h5></td>
	  			<td><h5 style="text-align:right">${article.item_pf}</h5></td>
	  		</tr>
		</table>
	  	</div><!-- 오른쪽부분 -->
		</td>
		</tr>
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