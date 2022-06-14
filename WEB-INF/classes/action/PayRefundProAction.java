package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import payment.PayDTO;
import payment.PaymentDAO;

public class PayRefundProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String m_id=(String)session.getAttribute("m_id");
		
		String pur_num=request.getParameter("pur_num");
		
		PaymentDAO pdao=new PaymentDAO();
		pdao.refundGame(m_id, pur_num);
		
		return "/MyPurchase.do";
	}

}
