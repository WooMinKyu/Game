<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>

<script language="JavaScript">           
function removeCheck() {
	 if (confirm("정말 삭제하시겠습니까??") == true){    //확인
	     document.delForm.submit();
	 }else{   //취소
	     return false;
	 }
	}
function deleteSave(){	
	alert("삭제하겠습니다");
	return true;
 }
</script>
</head>

<body bgcolor="#e0ffff">
<center><b>글삭제</b>
<br>
<form method="POST" name="delForm"  
        action="/Game/StoreDeletePro?pageNum=${pageNum}&item_num=${item_num}" 
   onsubmit="return deleteSave()"> 
 <table border="1" align="center" cellspacing="0" cellpadding="0" width="360">
 <tr height="30">
    <td align=center bgcolor="#b0e0e6">
      <input type="button" value="글삭제" onclick="removeCheck()">
      <input type="button" value="글목록" 
       onclick="document.location.href='/Game/StoreList.do?pageNum=${pageNum}&search=기본'">     
   </td>
 </tr>  
</table> 
</form>
</body>
</html> 
