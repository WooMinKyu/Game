package action;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import payment.CartDTO;
import payment.PaymentDAO;

public class PayWishlistAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
	    String m_id=(String)session.getAttribute("m_id");
		
		PaymentDAO pdao=new  PaymentDAO();
		List wishList=pdao.getWishlistList(m_id);

		
		request.setAttribute("m_id", m_id);
		request.setAttribute("wishList", wishList);
		
		return "/4.Payment/Wishlist.jsp";
	}
}
