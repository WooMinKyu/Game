<%@page contentType="text/html;charset=euc-kr"%>
<%
  session.invalidate();//���ǹ�ȿȭ
%>
<script>
  alert('�α׾ƿ��Ǿ����ϴ�.');
  location.href="/Game/2.Login&Member/AdLogin.jsp";
</script>