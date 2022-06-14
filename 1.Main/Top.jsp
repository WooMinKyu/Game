<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="admin.*,member.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript"
			src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<%
    request.setCharacterEncoding("UTF-8");
    session.getAttribute("m_id");
    session.getAttribute("ad_id");
%>
<script>
  	$(function() {
  		$.ajax({
		url:'/Game/4.Payment/CountCart.jsp', 
		type:'post',   
		data:{m_id:'${sessionScope.m_id}'},
		success:function(args) {
			$('#countcart').load(location.href+' #countcart');
		}
	})
  	})
</script>
<div class="containers">
    	<nav class="navbar navbar-inverse" role="navigation">
    		<div class="navbar-left">
    			<img src="/Game/img/five_logo.png" width="45px" height="45px">
    		</div>
    		<div> <!-- Mobile 태그입력 -->
    			<ul class="nav navbar-nav">
    				<li><a href="/Game/1.Main/index.jsp">HOME</a></li>
    				<li><a href="/Game/StoreList.do">STORE</a></li>
    				<li><a href="/Game/FreeList.do">COMMUNITY</a></li>
    				<li><a href="/Game/9.Support/Support.jsp">SUPPORT</a></li>
    			</ul>
    		</div>
    		
    		<div class="navbar-right"><!-- glyphicon Box -->
    			<ul class="nav navbar-nav">
    				<li>
    					<a href="/Game/Cart.do"><span class="glyphicon glyphicon-shopping-cart"></span>
    						<c:choose>
	    						<c:when test="${sessionScope.countCart != 0}">
				    				<span style="display:inline-block;position:absolute;background-color:white;color:black;border-radius:10px;width:18px;text-align:center;top:0;">
				    				<b id="countcart">
				    					${sessionScope.countCart}
				    				</b>
				    				</span>
			    				</c:when>
			    				<c:otherwise>
			    					<span style="display:inline-block;position:absolute;background-color:white;color:black;border-radius:10px;width:18px;text-align:center;top:0;">
				    				<b id="countcart">
				    				
				    				</b>
				    				</span>
			    				</c:otherwise>
		    				</c:choose>
    					</a></li>
    				<li><a href="/Game/Wishlist.do"><span class="glyphicon glyphicon-heart"></span></a></li>
    				<li class="dropdown">
    					<a data-toggle="dropdown" href="#">
    						<span class="glyphicon glyphicon-user">
    							<ul class="dropdown-menu">
    							<%
									if(session.getAttribute("m_id") == null && session.getAttribute("ad_id") != null) {%>
									<li>Welcome back Master!</a></li>
    								<li><a href="/Game/2.Login&Member/AdminPage.jsp">Admin page</a></li>						
    								<li><a href="/Game/2.Login&Member/AdLogout.jsp">Logout</a></li>
    							<%}else if(session.getAttribute("m_id") != null && session.getAttribute("ad_id") == null){%>
    								<li><a href="/Game/CsList.do">My page</a></li>	
    								<li><a href="/Game/2.Login&Member/Logout.jsp">Logout</a></li>
    							<%}else{ %>	
    								<li><a href="#" onclick="alert('로그인이 필요한 페이지입니다.')">My page</a></li>
    								<li><a href="/Game/2.Login&Member/Login.jsp">Login</a></li>
    							<%}%>
    							</ul> 
    						</span>
    					</a>
    				</li>
    			</ul>
    		</div>
    		<!-- Top Bar -->
    	</nav>
    </div>