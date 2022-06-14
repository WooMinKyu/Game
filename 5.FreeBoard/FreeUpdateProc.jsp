<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
   
   <c:if test="${check==1}">
   <meta http-equiv="Refresh" 
              content="0;url=/Game/FreeContent.do?com_num=${article.com_num}&pageNum=${pageNum}">
  </c:if>
  <c:if test="${check==0}">
   <script>
        alert("내가 작성하지 않는 게시물은 삭제할 수 없습니다.");
        history.go(-1);
   </script> 
  </c:if> 