package action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import payment.CartDTO;
import payment.PayDTO;
import payment.PaymentDAO;

public class PayCartPayAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String m_id=(String)session.getAttribute("m_id");

		
		PayDTO pd=new PayDTO();
		PaymentDAO pdao=new PaymentDAO();
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(now);
		
		CartDTO cd=null;
		List cartList=pdao.getCartList(m_id);
		System.out.println("cartList=>"+cartList);
		
		
		try { 
			for(int i=0;i<cartList.size();i++) { 
				cd=(CartDTO)cartList.get(i);
				
				pd.setM_id(m_id);
				pd.setItem_num(cd.getItem_num()); 
				pd.setPur_name(cd.getCt_name());
				pd.setPur_price(cd.getCt_price()); 
				pd.setPur_date(today);
				pd.setPur_thum(cd.getCt_thum());
				pd.setPur_img(cd.getCt_img());

			  
				pdao.insertGame(pd);
				pdao.updateCount(cd.getItem_num());
				pdao.deleteCart(m_id, cd.getItem_num());
				pdao.deleteWish(m_id, cd.getItem_num());
			  }
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		return "/4.Payment/PaymentPro.jsp";
	}

}
