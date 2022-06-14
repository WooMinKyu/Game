package action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import payment.PayDTO;
import payment.PaymentDAO;

// 결제버튼 >> 결제진행
public class PayPayProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session=request.getSession();
		String m_id=(String)session.getAttribute("m_id");
		
		PayDTO pd=new PayDTO();
		
		// 구매번호,결제상태, 결제일 >> 생성
		// 구매번호 
		
		// 결제상태
		
		// 결제일 sysdate
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(now);
		System.out.println(today);
		
		// 상품이름,금액,아이디,상품번호 >> 요청
		pd.setItem_num(request.getParameter("item_num"));
		pd.setPur_name(request.getParameter("item_name"));
		pd.setPur_price(request.getParameter("item_price"));
		pd.setPur_date(today);
		pd.setM_id(m_id);
		pd.setPur_thum(request.getParameter("item_thum"));
		pd.setPur_img(request.getParameter("item_img"));
		pd.setPur_sale(Integer.parseInt(request.getParameter("item_sale")));
		
		PaymentDAO dbPro=new PaymentDAO();
	    dbPro.insertGame(pd); //DTO
	    dbPro.updateCount(request.getParameter("item_num"));
	    dbPro.deleteCart(m_id, request.getParameter("item_num"));
		dbPro.deleteWish(m_id, request.getParameter("item_num"));
	
		
		return "/4.Payment/PaymentPro.jsp";
	}

}
/*
	 		article.setEmail(request.getParameter("email"));
		    BoardDAO dbPro=new BoardDAO();
		    dbPro.insertArticle(article);
		    //response.sendRedirect("http://localhost:8090/JspBoard2/list.do"); 반환값이 없다면
		return "/writePro.jsp";
*/