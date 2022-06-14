<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="payment.PaymentDAO"%>
<%
	
	request.setCharacterEncoding("utf-8");
	String m_id=(String)session.getAttribute("m_id");
    String item_num=request.getParameter("item_num");
   
    
    PaymentDAO pdao=new PaymentDAO();
    pdao.deleteCart(m_id, item_num);
    
    
    %>