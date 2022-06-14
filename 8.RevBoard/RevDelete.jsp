<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="boards.RevBoardDAO"%>
<%
	String rev_num=request.getParameter("rev_num");
	
	RevBoardDAO rbdao=new RevBoardDAO();
	int delete=rbdao.RevDeleteArticle(Integer.parseInt(rev_num));
	System.out.println("delete of RevDelete =>"+delete);
%>