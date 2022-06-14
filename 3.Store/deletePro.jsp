<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
   
   <c:if test="${check==1}">
   <meta http-equiv="Refresh" 
              content="0;url=/Game/StoreList.do?pageNum=${pageNum}&search=기본">
  </c:if>
  <c:if test="${check==0}">
   <script>
        alert("삭제실패");
        history.go(-1);
   </script> 
  </c:if> 