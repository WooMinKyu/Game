<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<%
     //int num=(Integer)request.getAttribute("num");//${num}
%>
<title>게시판</title>

<script language="JavaScript" src="writescript.js"></script>
</head>
  
<body bgcolor="#e0ffff">  
<br><!-- onsubmit 이벤트="return 호출할 함수명"  -->
<form method="post" name="writeform" action="/Game/StoreWritePro.do"
          onsubmit="return writeSave()">
               
<table width="400" border="1" cellspacing="0" cellpadding="0"  bgcolor="#e0ffff" align="center">
   <tr>
    <td align="center" colspan="2" bgcolor="#b0e0e6">
    <font size="5"><b>글쓰기폼</b></font>
   </td>
   </tr>  
   <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center">게임이름</td>
    <td  width="330">
       <input type="text"  size="10" maxlength="10" name="item_name"></td>
  </tr>
  <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center">아이디</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="ad_id"></td><!-- 관리자아이디 받아오기 -->
  </tr>
  <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center">상품가격</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="item_price"></td>
  </tr>
  <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center">장르번호</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="gen_num"></td>
  </tr>
  <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center">등급번호</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="grd_num"></td>
  </tr>
  <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center">상품이미지</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="item_img"></td>
  </tr>
  <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center">상품썸네일</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="item_thum"></td>
  </tr>
  <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center">상품출시일</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="item_rels"></td>
  </tr>
  <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center">상품장르</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="item_gen"></td>
  </tr>
  <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center">상품등급</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="item_grd"></td>
  </tr>
  <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center">개발사</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="item_dev"></td>
  </tr>
  <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center">퍼블리셔</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="item_pub"></td>
  </tr>
  <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center">플랫폼</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="item_pf"></td>
  </tr>
  <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center">상품기능</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="item_ft"></td>
  </tr>
  <tr>
    <td  width="300"  bgcolor="#b0e0e6" align="center" >상품내용</td>
    <td  width="330" >
     <input type="text" size="10" maxlength="10" name="item_content"></td>
  </tr>
  
<tr>      
 <td colspan=2 bgcolor="#b0e0e6" align="center"> 
  <input type="submit" value="글쓰기" >  
  <input type="reset" value="다시작성">
  <input type="button" value="목록보기" 
             OnClick="window.location='/Game/StoreList.do?search=기본'">
</td></tr></table>    
</form>      
</body>
</html>      