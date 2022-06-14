<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="store.StoreDAO"%>
<%
	String item_num=request.getParameter("item_num");
	
	StoreDAO sdao=new StoreDAO();
	int delete=sdao.deleteArticle(item_num);
	System.out.println("delete of soreItemDelete =>"+delete);
%>