package action;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import payment.PayDTO;
import payment.PaymentDAO;

public class MemMyPurchaseAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		PayDTO pd=new PayDTO();
		PaymentDAO pdao=new PaymentDAO();
		List payList=null;
		
		HttpSession session=request.getSession();
		String m_id=(String)session.getAttribute("m_id");
		
		String pageNum=request.getParameter("pageNum");
		int count=0;
		count=pdao.getMyPurCount(m_id);
		Hashtable<String,Integer> pgList=pdao.pageList(pageNum, count);
		
		if (count > 0){
			  payList=pdao.getMyPurchase(m_id,pgList.get("startRow"),pgList.get("endRow"));
			  System.out.println("MemMyPurchaseAction articleList=>"+payList);
		  }else {
			  payList=Collections.EMPTY_LIST;
		  }
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("payList", payList);
		request.setAttribute("pgList", pgList);
		request.setAttribute("count",count);
		
		return "/2.Login&Member/MyPurchase.jsp";
	}

}
