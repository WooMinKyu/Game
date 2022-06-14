package action;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import payment.CartDTO;
import payment.PaymentDAO;
import payment.WishDTO;

public class PayCartAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session=request.getSession();
		String m_id=(String)session.getAttribute("m_id");
		int sum=0; //가격합계
		int discount=0;	//할인금액
		int dissum=0;//할인금액합계
		CartDTO cdto=null;
		
		PaymentDAO pdao=new  PaymentDAO();
		List cartList=pdao.getCartList(m_id);
		DecimalFormat df=new DecimalFormat("₩###,###,###"); //총 가격에 ,를 넣기위한 객체
		
		try {
			for(int i=0;i<cartList.size();i++) {
				cdto=(CartDTO)cartList.get(i);
				double disrate=0.01*cdto.getCt_sale();//할인율
				discount=(int)(Integer.parseInt(cdto.getCt_price())*disrate);
				dissum+=discount;
				sum+=Integer.parseInt(cdto.getCt_price());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//list.getCt_dis(); 할인
		
		 //총 가격에 ,넣은것
		int total=sum-dissum;//총가격
		
		request.setAttribute("cartList", cartList);
		request.setAttribute("sum", sum);
		request.setAttribute("st_sum", df.format(sum));
		request.setAttribute("dissum", dissum);
		request.setAttribute("st_dissum", df.format(dissum));
		request.setAttribute("total", total);
		request.setAttribute("st_total", df.format(total));
		
		return "/4.Payment/Cart.jsp";
	}
}
