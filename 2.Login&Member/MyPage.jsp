<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String m_id=(String)session.getAttribute("m_id");
	String m_pw=(String)session.getAttribute("m_pw");
	
	MemberDAO mdao=new MemberDAO();
	MemberDTO mdto=mdao.getMember(m_id);
	
	System.out.println("MyPage.jsp의 id=>"+m_id);
	System.out.println("pw=>"+m_pw);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>마이페이지</title>

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
          padding:5px;
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
    .refund img{
    width:100%;
    height:auto;
    }
   /* 마이페이지 */
   .wrap {
	/* width: 100%; */
	/* display: flex; */
	align-items: center;
	justify-content: center;
	background: #2b2b2b;
	}
    #proinfo li {
    list-style:none;
    }   
    #mygame1,#mygame2,#mygame3,#mygame4,#mygame5,#mygame6 {
    border: 1px solid #404040;
    position:relative;
    float:left;
    margin-right:20px;
    }
    #mygame1 a img:hover {
    filter: brightness(70%);   
    }
    #mygame2 a img:hover {
    filter: brightness(70%);
    }
    #mygame3 a img:hover {
    filter: brightness(70%);
    }
    #mygame4 a img:hover {
    filter: brightness(70%);
    }
    #mygame5 a img:hover {
    filter: brightness(70%);
    }
    #mygame6 a img:hover {
    filter: brightness(70%);
    }
    button {
		padding:5px 10px 5px 10px;
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
	.pBtn {
		padding:5px 10px 5px 10px;
		color:white;
		font-weight:bold;
		border:1px solid grey;
		border-radius:5px;
		background-color:#2b2b2b;
	}
	.pBtn:hover {
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
		<center>
			<h1>MyPage</h1>
			<table style="width:1000px;height:600px;">
				<tr>
					<td style="background:#202020;width:250px;height:300px;">
						<table style="width:250px;height:300px;">
							<tr>
								<td>
									<center><h4>프로필정보</h4><center>
								</td>
							</tr>
							<tr>
							    <td>
							    	<center>
							    	<img src="/Game/img/usericon.png" width="50%" height="auto">
							    	</center>
							    	<br>					 
									<ul id="proinfo">
										<li>이름   : <%=mdto.getM_id()%></li>
									  	<li>닉네임 : <%=mdto.getM_nick()%></li>
									  	<li>가입일 : <%=mdto.getM_join()%></li>
									  	<!-- <li>보유한 게임수 : 123개</li>
									  	<li>총 플레이 타임 : 5,665시간</li>
									  	<li>가장 많이한 게임 : GTA5</li>
									  	<li>최근 구매 게임 : 디아블로4</li>
									  	<li>최근 플레이 게임 : 폴아웃4</li> -->
									</ul>
								</td>
							</tr>
							<tr>
								<td style="vertical-align:top;"><center>
									<button onclick="location.href='/Game/2.Login&Member/MemberUpdateCheck.jsp'">정보수정</button>
									<button onclick="location.href='/Game/2.Login&Member/MemberDeleteCheck.jsp'">회원탈퇴</button>						 
								</center></td>
							</tr>
						</table>			
					</td>
					<td style="background:#202020;width:750px;height:300px;padding-right:30px;">
						<table style="width:750px;height:300px;">
							<tr>
								<td style="vertical-align:top;" colspan="4">
									<h5>구매내역<a href="/Game/MyPurchase.do" style="float:right;color:white;">더보기</a></h5>					
								</td>
							</tr>
							<tr>
								<td>
								<c:if test="${payList.isEmpty()}">
									<center>
									<div>
											<h5>구매한 상품이 없습니다.</h5>
									</div>
									</center>
								</c:if>
								
								<c:forEach var="payList" items="${payList}" begin="0" end="3">
										<div style="width:150px;height:200px;float:left;margin-left:30px;" class="itemlinker">
											<a href="/Game/PurDetail.do?pur_num=${payList.pur_num}"><img src="/Game/img/items/${payList.pur_img}.jpg" style="width:170px;"/></a>
										</div>						
								</c:forEach>
								</td>
							</tr>
						</table>						
					</td>
				</tr>
				<tr><!-- 2열 -->
					<td style="background:#202020;width:750px;height:300px;padding-top:20px;">
						<table style="width:250px;height:300px;">
							<tr><!-- 빠른 문의 -->
								<td style="height:10%;">
									<h5><center>빠른 문의</center></h5>
								</td>
							</tr>
							<tr>
								<td style="height:90%;">
									<center>
										<button class="btn btn-info btn-lg" style="margin-top:-30px;width:200px;height:42px;">상품관련</button>
										<button class="btn btn-info btn-lg" style="margin-top:7px;width:200px;height:42px;">기술관련</button>
										<button class="btn btn-info btn-lg" style="margin-top:10px;width:200px;height:42px;">결제/환불</button>
										<button class="btn btn-info btn-lg" style="margin-top:10px;width:200px;height:42px;">기타문의사항</button>
									</center>
								</td>
							</tr>
						</table>
					</td>
					<td style="background:#202020;width:750px;height:300px;"><!-- 2행 -->
						<table style="width:750px;height:300px;">
							<tr><!-- CS게시판명 --><!-- 2열 2행-1 시작 -->
								<td style="height:30px;padding-top:20px;padding-bottom:15px;">
									<h5>CS Board<button style="float:right;" onClick="location.href='/Game/CsWriteForm.do'">글쓰기</button></h5>
								</td>
							</tr>
							<tr><!-- CS게시판 --><!-- 2열 2행-2 시작 -->
								<td style="height:230px;vertical-align:top;">
									
									<!--데이터의 유무-->
									<c:if test="${pgList.count==0}">
									<table style="border:1;width:750px;cellpadding:0;cellspacing:0;align:center;">
										<tr height="40px" bgcolor="#808080">											
											<td align="center" width="40%">Summary</td> 
											<td align="center" width="25%">Writer</td>
											<td align="center" width="15%">Date</td>  
											<td align="center" width="10%">Status</td>  
									    </tr>
										<tr>
											<td colspan="5" align="center" style="padding:70px">문의한 내용이 없습니다.</td>
										</tr>
									</table>
									</c:if>
									
									<c:if test="${pgList.count > 0}">							
									<table border="1" width="750px" cellpadding="0" cellspacing="0" align="center"> 
									    <tr height="40px" bgcolor="#808080">
											<td align="center" width="40%">Summary</td> 
											<td align="center" width="25%">Writer</td>
											<td align="center" width="15%">Date</td>  
											<td align="center" width="10%">Status</td>  
									    </tr>
									    <!-- 실질적으로 레코드를 출력시켜주는 부분  -->
										<c:set var="number" value="${pgList.number}" />
										<c:forEach var="article" items="${articleList}">
										<tr height="40px">
									    	<td style="padding-left:10px;">
									    		<a href="/Game/CsContent.do?cs_num=${article.cs_num}&pageNum=${pgList.currentPage}" style="color:white;">${article.cs_sub}</a>
									    	</td>
									    	<td align="center" style="color:white;"> 
									    		${article.m_id}
									    	</td>
									    	<td align="center" style="color:white;">
									    		${article.cs_wdate}
									    	</td>
									    	<td align="center" style="color:white;">
									    		접수완료
									    	</td>
									    </tr>
										</c:forEach>
										</table>
										</c:if>
									</td>
								</tr><!-- 2열 2행-2 끝 -->
								<tr>
									<td style="padding-top:-30px;"><!-- 페이징 처리부분 -->
									<center>
										<c:if test="${pgList.startPage > pgList.blockSize}">
											<button onClick="location.href='/Game/CsList.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}'" class="pBtn">&gt</button>
										</c:if>
										<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
											<c:if test="${pgList.currentPage==i}"> <!-- Selected -->
												<button onClick="location.href='/Game/CsList.do?pageNum=${i}&search=${search}&searchtext=${searchtext}'" style="background-color:white; color:black" class="pBtn">${i}</button>
											</c:if>
											<c:if test="${pgList.currentPage!=i}"> <!-- unselected -->
												<button onClick="location.href='/Game/CsList.do?pageNum=${i}&search=${search}&searchtext=${searchtext}'" class="pBtn">${i}</button>
											</c:if>
											</a>
										</c:forEach>
										<c:if test="${endPage < pageCount}">
											<button onClick="location.href='/Game/CsList.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}'" class="pBtn">&lt</button>
										</c:if>
									</center>
									<div style="margin-bottom:10px;"></div>
									</td>
								</tr>
						</table>
					</td>
				</tr>
			</table>		
		</center>
		<div style="margin-bottom:100px"></div>
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