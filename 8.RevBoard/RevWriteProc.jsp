<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="boards.RevBoardDAO, boards.RevBoardDTO "%>
<% 
    //response.sendRedirect("http://~/list.do");//입력한후 다시 DB접속->새로 고침해서 화면에 새로운 글 반영
%>
  <meta http-equiv="Refresh" content="0;url=/Game/FreeList.do">

<%
	request.setCharacterEncoding("utf-8");
	String m_id=(String)session.getAttribute("m_id");
	
	String rev_nick=request.getParameter("rev_nick");
	/* String rev_num=request.getParameter("rev_num"); */
	String rev_content=request.getParameter("rev_content");
	/* String rev_wdate=request.getParameter("rev_wdate"); */
	String item_num=request.getParameter("item_num");
	
	RevBoardDTO rbdto=new RevBoardDTO();
	rbdto.setM_id(m_id);
	rbdto.setRev_nick(rev_nick);
	/* rbdto.getRev_num(); */
	rbdto.setRev_content(rev_content);
	/* rbdto.getRev_wdate(); */
	rbdto.setItem_num(item_num);
	
	RevBoardDAO rbdao=new RevBoardDAO();
	rbdao.RevInsertArticle(rbdto, item_num);
	
%>