package action;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 상세페이지 > 결제
public class PayPayFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int discount=0;//할인금액
		int st_item_price=0;
		
		String item_num=request.getParameter("item_num"); //상품번호
		String item_name=request.getParameter("item_name"); //상품이름
		String item_price=request.getParameter("item_price"); //상품가격
		String item_thum=request.getParameter("item_thum");
		String item_img=request.getParameter("item_img");
		String item_sale=request.getParameter("item_sale");
		
		st_item_price=Integer.parseInt(item_price);
		
		DecimalFormat df=new DecimalFormat("₩###,###,###"); 
		  
		double disrate=0.01*Integer.parseInt(item_sale);//할인율
		discount=(int)((Integer.parseInt(item_price))*disrate);//할인금액
		int total=Integer.parseInt(item_price)-discount;//총 금액
		
		
		request.setAttribute("item_num", item_num);//상품번호
		request.setAttribute("item_name", item_name);//상품이름
		request.setAttribute("item_thum", item_thum);
		request.setAttribute("item_img", item_img);
		
		request.setAttribute("item_price", item_price);//상품가격
		request.setAttribute("st_item_price", st_item_price);//상품가격원표시
		request.setAttribute("item_sale", item_sale);
		request.setAttribute("discount", discount);//할인금액 원표시
		request.setAttribute("st_discount", df.format(discount));//할인금액 원표시
		request.setAttribute("total", total);//총금액
		request.setAttribute("st_total", df.format(total));//총금액 원표시
		
		
		//할인율 적용시(테이블에 %를 빼고 숫자만 넣은다음 불러와서 *0.01을 해서 할인된 가격 만들기)
		//request.setAttribute("discount",discount);
		
		return "/4.Payment/Payment.jsp";
	}

}
