package action;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import payment.PayDTO;
import payment.PaymentDAO;

public class PayPurchaseDetailAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String m_id=(String)session.getAttribute("m_id");
		int discount=0;//할인금액
		
		DecimalFormat df=new DecimalFormat("₩###,###,###"); //총 가격에 ,를 넣기위한 객체
		
		String pur_num=request.getParameter("pur_num");
		System.out.println(m_id+", "+pur_num);
		
		PaymentDAO rfPro=new PaymentDAO();
		PayDTO pdto=rfPro.getGame(m_id, pur_num);
		
		double disrate=0.01*pdto.getPur_sale();//할인율
		discount=(int)(Integer.parseInt(pdto.getPur_price())*disrate);//할인금액
		int total=Integer.parseInt(pdto.getPur_price())-discount;//총 금액
		
		request.setAttribute("pur_num", pur_num);
		request.setAttribute("pur_name", pdto.getPur_name());
		request.setAttribute("pur_thum", pdto.getPur_thum());
		request.setAttribute("pur_price", pdto.getPur_price());
		request.setAttribute("st_pur_price", df.format(Integer.parseInt(pdto.getPur_price())));//가격에 , 넣기
		request.setAttribute("pur_sale", pdto.getPur_sale());
		request.setAttribute("discount", df.format(discount));//할인금액 전달
		request.setAttribute("pur_stat", pdto.getPur_stat());
		request.setAttribute("pur_date", pdto.getPur_date());
		request.setAttribute("item_number", pdto.getItem_num());
		request.setAttribute("total", total);
		request.setAttribute("st_total", df.format(total));//총금액에 ,넣기
		
		
		return "/4.Payment/PurDetails.jsp";
		
	}

}
