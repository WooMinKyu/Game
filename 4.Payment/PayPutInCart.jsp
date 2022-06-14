<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="payment.CartDTO,payment.PaymentDAO" %>
<%
request.setCharacterEncoding("utf-8");
String m_id=(String)session.getAttribute("m_id");

String item_num=request.getParameter("item_num");
String item_thum=request.getParameter("item_thum");
String item_name=request.getParameter("item_name");
String item_price=request.getParameter("item_price");
String grd_num=request.getParameter("grd_num");
String item_img=request.getParameter("item_img");
int item_sale=Integer.parseInt(request.getParameter("item_sale"));

CartDTO cdto=new CartDTO();
cdto.setItem_num(item_num);
cdto.setCt_name(item_name);
cdto.setCt_thum(item_thum);
cdto.setCt_price(item_price);
cdto.setM_id(m_id);
cdto.setGrd_num(grd_num);
cdto.setCt_img(item_img);
cdto.setCt_sale(item_sale);

PaymentDAO pdao=new PaymentDAO();
pdao.insertCart(cdto);
%>