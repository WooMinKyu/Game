package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import payment.PayDTO;
import payment.PaymentDAO;

public class PayRefundCheckAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String m_id=(String)session.getAttribute("m_id");
		
		String pur_num=request.getParameter("pur_num");
		
		PaymentDAO pdao=new PaymentDAO();
		PayDTO pdto=pdao.getGame(m_id, pur_num);
		
		
		request.setAttribute("pur_num", pur_num);
		request.setAttribute("pur_name", pdto.getPur_name());
		request.setAttribute("pur_thum", pdto.getPur_thum());
		
		return "/4.Payment/RefundCheck.jsp";
	}

}
