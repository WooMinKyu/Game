<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="payment.PaymentDAO" %>
<%
request.setCharacterEncoding("utf-8");
int countCart=0;
String m_id=(String)session.getAttribute("m_id");

PaymentDAO pdao=new PaymentDAO();
countCart=pdao.getCountCart(m_id);

session.setAttribute("countCart", countCart);
%>