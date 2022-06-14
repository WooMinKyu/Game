<%@page contentType="text/html;charset=euc-kr" import="member.*"%>
<jsp:useBean id="memMgr" class="login.LoginDAO"/>

<% 
	request.setCharacterEncoding("utf-8");
	String m_id=(String)session.getAttribute("m_id");
	String m_pw=(String)session.getAttribute("m_pw");
	String m_status=(String)session.getAttribute("m_status");
	System.out.println("m_id=>"+m_id+",m_pw=>"+m_pw+",m_status=>"+m_status);
	
	MemberDAO mdao=new MemberDAO();
	MemberDTO mdto=mdao.getMember(m_id);
	
	System.out.println("memberupdate 체크의 id=>"+mdto.getM_id());
	System.out.println("pw=>"+mdto.getM_pw());
	System.out.println("status=>"+mdto.getM_status());
	
 	boolean rs = memMgr.UpDeCheck(mdto.getM_id(),mdto.getM_pw(),mdto.getM_status());
 	System.out.println("rs="+rs);
%>
<%
    if(rs){ //암호가 맞으면
%>
  <script>
  	location.href="MemberUpdate.jsp";
  </script>
<%
   }else{ //실패
%>
   <script>
    alert("비밀번호가 틀립니다.\n다시 확인해주세요");
    history.go(-1);
   </script>
<%
   }
%>