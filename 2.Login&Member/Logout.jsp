<%@page contentType="text/html;charset=euc-kr"%>
<%
  session.invalidate();//세션무효화
%>
<script>
  alert('로그아웃되었습니다.');
  location.href="/Game/2.Login&Member/Login.jsp";
</script>