<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
  
   
<c:if test="${check==1}">
	<meta http-equiv="Refresh" content="0;url=/Game/HelpList.do?">
</c:if>
<c:if test="${check==0}">
	<script>
	    alert("관리자만 게시글을 삭제할 수 있습니다.");
	    history.go(-1);
	</script> 
</c:if> 
 