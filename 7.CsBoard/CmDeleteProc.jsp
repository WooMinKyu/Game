<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
  
   
<c:if test="${check==1}">
	<meta http-equiv="Refresh" content="0;url=/Game/CmList.do">
</c:if>
<c:if test="${check==0}">
	<script>
	    alert("다른 사람의 게시글은 삭제할 수 없습니다.");
	    history.go(-1);
	</script> 
</c:if> 
 