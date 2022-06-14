<%@page contentType="text/html;charset=euc-kr"%>
<%@page import="member.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<%
	request.setCharacterEncoding("utf-8");
	String m_id=(String)session.getAttribute("m_id");
	
	MemberDAO mDAO=new MemberDAO();
	MemberDTO mDTO=mDAO.getMember(m_id);
	
	mDTO.getM_pw();
	
	System.out.println("MemberUpdateCheck�� m_id=>"+m_id);
%>
<HTML>
 <head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ȸ������ ����</title>
    <!-- ��Ʈ��Ʈ�� -->
    <link href="/Game/css/bootstrap.min.css" rel="stylesheet">
    <!-- ���۾����� -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script language="JavaScript" src="script.js"></script>
<script>
   function updateCheck(){
          if(document.update.passwd.value==""){
              alert("��й�ȣ�� �Է��ϼ���");
              document.update.passwd.focus();
              return;
          }else if(document.update.passwd.value!="<%=mDTO.getM_pw()%>"){
       	   alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
           document.del.passwd.focus();
           return;
          }
     document.update.submit();//DelCheckProc.jsp 
   }
 </script>
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
          border:0px solid black;
          padding:10px;
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
    border:1px solid #00ffd8;
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
</style>
</head>
 <BODY onLoad="document.update.passwd.focus()"
    bgcolor="#FFFFCC">
    <jsp:include page="/1.Main/Top.jsp" /> <!-- Top menu -->
	<div class="rows" style="margin-top:100px;">
	<div class="col-md-1"></div> <!-- Margin left -->
	<div class="col-md-10"> <!-- Main (��90%)-->
 <br><br><br>
 <center>
 <form name="update" method="post" 
                  action="MemberUpdate.jsp">
 <table style="width:400px;height:200px;">
	 <tr>
		<td align="center" colspan="2">
			<b><%=m_id%>��</b><p>������ �����Ͻ÷��� ��й�ȣ�� �Է����ּ���
		</td>
	 </tr>
 	<tr>
		<td><input type="password" name="passwd" style="float:left;margin-top:-45px;margin-left:100px;
						width:200px;color:black;border-radius: 5px;border: 0;"></td>
 	</tr>
 	<tr>	
		<td><input class="btn btn-primary btn-mg" type="button" value="����"
	    				onclick="updateCheck()" style="float:left;margin-left:100px;margin-top:-30px;width:95px;"></td>
		<td><input class="btn btn-primary btn-mg" type="button" value="���"
						onclick="history.go(-1)"
						style="margin-top:-33px;margin-left:-195px;width:95px;"></td>	
 	</tr>
 <input type="hidden" name="m_id"
        value="<%=m_id%>">
 </table>
 </form>
 </center>
 <div style="margin-top:500px"></div> <!-- �ϴܿ��鼳�� -->
	</div>
	<div class="col-md-1"></div>
	<!-- jQuery (��Ʈ��Ʈ���� �ڹٽ�ũ��Ʈ �÷������� ���� �ʿ��մϴ�) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- ��� �����ϵ� �÷������� �����մϴ� (�Ʒ�), ������ �ʴ´ٸ� �ʿ��� ������ ������ �����ϼ��� -->
    <script src="/Game/js/bootstrap.min.js"></script>
</body>
<footer>
<jsp:include page="/1.Main/Footer.jsp" /> <!-- Footer -->
</footer>
</html>