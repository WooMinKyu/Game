<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="payment.WishDTO,payment.PaymentDAO" %>
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

WishDTO wdto=new WishDTO();
wdto.setItem_num(item_num);
wdto.setW_name(item_name);
wdto.setW_thum(item_thum);
wdto.setW_price(item_price);
wdto.setM_id(m_id);
wdto.setGrd_num(grd_num);
wdto.setW_img(item_img);
wdto.setW_sale(item_sale);

PaymentDAO pdao=new PaymentDAO();
pdao.insertWishlist(wdto);
%>